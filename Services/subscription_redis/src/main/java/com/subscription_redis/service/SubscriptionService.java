package com.subscription_redis.service;

import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.SubscriptionResponse;

import com.subscription_redis.model.Subscription;
import com.subscription_redis.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    public void saveSubscription(SubscriptionRequest subscriptionRequest) {
        Subscription subscription = Subscription.builder()
                .id("1234")
                .aviationDataID(subscriptionRequest.getAviationDataID())
                .name(subscriptionRequest.getName())
                .email(subscriptionRequest.getEmail())
                .build();
        subscriptionRepository.save(subscription);
        log.info("Subscription {} is saved", subscription.getId());
    }

    // Read Operation
    public List<SubscriptionResponse> fetchSubscriptionList() {

        Iterable<Subscription> subscriptions = subscriptionRepository.findAll();

        List<SubscriptionResponse> subscriptionResponseList = new ArrayList<>();
        return StreamSupport.stream(subscriptions.spliterator(), false)
                .map(this::mapToSubscriptionResponse)
                .collect(Collectors.toList());
    }

    private SubscriptionResponse mapToSubscriptionResponse(Subscription subscription) {
        return SubscriptionResponse.builder()
                .id(subscription.getId())
                .aviationDataID(subscription.getAviationDataID())
                .name(subscription.getName())
                .email(subscription.getEmail())
                .build();
    }
}