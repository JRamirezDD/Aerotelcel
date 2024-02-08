package com.subscription_redis.service;

import com.subscription_redis.dto.AviationDataSubscriptionsResponse;
import com.subscription_redis.dto.SubscriptionRequest;

import com.subscription_redis.dto.SubscriptionResponse;
import com.subscription_redis.model.AviationDataSubscriptions;
import com.subscription_redis.model.Subscription;
import com.subscription_redis.model.emailAlreadySubscribedException;
import com.subscription_redis.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j // For Logging
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, RedisTemplate<String, Object> redisTemplate) {
        this.subscriptionRepository = subscriptionRepository;
        this. redisTemplate = redisTemplate;
    }

    // Save Operation
    public void saveSubscription(SubscriptionRequest subscriptionRequest) throws emailAlreadySubscribedException {
        String aviationDataID = subscriptionRequest.getAviationDataID();

        Subscription subscription = Subscription.builder()
                .name(subscriptionRequest.getName())
                .email(subscriptionRequest.getEmail())
                .build();

        AviationDataSubscriptions aviationDataSubscriptions = subscriptionRepository.findById(aviationDataID)
                .switchIfEmpty(Mono.just(
                AviationDataSubscriptions.builder()
                .aviationDataID(aviationDataID)
                .subscriptions(new ArrayList<>())
                .build())).block();

        aviationDataSubscriptions.addSubscription(subscription);

        subscriptionRepository.save(aviationDataSubscriptions)
                .subscribe(
                        savedEntity -> log.info("Subscription email: {} to aviationDataID: {} is saved", subscription.getEmail(), aviationDataID)
                );

    }

    // Read Operation
    public Flux<AviationDataSubscriptionsResponse> fetchSubscriptionsList() {
         return subscriptionRepository.findAll()
                         .map(subscription -> new AviationDataSubscriptionsResponse(subscription))
                 .switchIfEmpty(Mono.error(new NullPointerException("No Subscriptions found")));
    }



    // Pending:
        // unsubscripe
        // Wipe AviationDataID

    public Mono<AviationDataSubscriptionsResponse> fetchSubscriptions(String aviationDataID) {
        return subscriptionRepository.findById(aviationDataID)
                .map(subscription -> new AviationDataSubscriptionsResponse(subscription))
                .switchIfEmpty(Mono.error(new NullPointerException("Subscription not found for ID: " + aviationDataID)));
    }

//    public List<SubscriptionResponse> fetchSubscriptionList() {
//
//        Iterable<Subscription> subscriptions = subscriptionRepository.findAll();
//
//        List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();
//        return StreamSupport.stream(subscriptions.spliterator(), false)
//                .map(this::mapToSubscriptionResponse)
//                .collect(Collectors.toList());
//    }
//
//    private SubscriptionResponse mapToSubscriptionResponse(Subscription subscription) {
//        return SubscriptionResponse.builder()
//                .id(subscription.getId())
//                .aviationDataID(subscription.getAviationDataID())
//                .name(subscription.getName())
//                .email(subscription.getEmail())
//                .build();
//    }
}