package com.subscription_handler.Service;

import com.subscription_handler.Service.FeignClients.Client_SubscriptionRedis;
import com.subscription_redis.dto.*;
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
    public SubscriptionResponse findSubscription(FindSubscriptionRequest findSubscriptionRequest) {
        return subscriptionRedisClient.findSubscription(findSubscriptionRequest);
    }

    // Determine whether email is subscribed to aviationData
    public boolean determineSubscription(FindSubscriptionRequest findSubscriptionRequest) {
        return subscriptionRedisClient.determineSubscription(findSubscriptionRequest);
    }


    // Subscribe
    public void subscribe(SubscriptionRequest subscriptionRequest) {
        subscriptionRedisClient.subscribe(subscriptionRequest);
    }

    // Unsubscribe
    public void unsubscribe(UnsubscriptionRequest unsubscriptionRequest) {
        subscriptionRedisClient.unsubscribe(unsubscriptionRequest);
    }

    // Unsubscribe from all
    public void unsubscribeAll(UnsubscriptionFromAllRequest unsubscriptionFromAllRequest) {
        subscriptionRedisClient.unsubscribeAll(unsubscriptionFromAllRequest);
    }
}


