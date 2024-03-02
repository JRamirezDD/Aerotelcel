package com.subscription_handler.Service;

import com.subscription_handler.Service.FeignClients.Client_SubscriptionRedis;
import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionHandlerService {
    final Client_SubscriptionRedis  subscriptionRedisClient;

    // Home
    public String home() {
        return subscriptionRedisClient.home();
    }


    // Get Subscription
    public SubscriptionResponse findSubscription(String aviationDataID, String email) {
        return subscriptionRedisClient.findSubscription(aviationDataID, email);
    }


    // Subscribe
    public void subscribe(SubscriptionRequest subscriptionRequest) {
        subscriptionRedisClient.subscribe(subscriptionRequest);
    }

    // Unsubscribe
    public void unsubscribe(String aviationDataID, String email) {
        subscriptionRedisClient.unsubscribe(aviationDataID, email);
    }

    // Unsubscribe from all
    public void unsubscribeAll(String email) {
        subscriptionRedisClient.unsubscribeAll(email);
    }
}


