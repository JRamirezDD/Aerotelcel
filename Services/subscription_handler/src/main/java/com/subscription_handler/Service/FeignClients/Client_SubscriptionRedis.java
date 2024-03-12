package com.subscription_handler.Service.FeignClients;

import com.subscription_redis.controller.SubscriptionController.API_SubscriptionController;
import com.subscription_redis.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${Aerotelcel.subscription-redis.controller.SubscriptionController.name}", url = "${Aerotelcel.subscription-redis.address}:${Aerotelcel.subscription-redis.port}")
public interface Client_SubscriptionRedis {

    @GetMapping("/api/subscription-redis/")
    String home();

    @PostMapping("/api/subscription-redis/subscriptions")
    void subscribe(@RequestBody SubscriptionRequest subscriptionRequest);

    @GetMapping("/api/subscription-redis/subscriptions")
    List<AviationDataSubscriptionsResponse> fetchAllSubscriptions();

    @GetMapping("/api/subscription-redis/subscriptions/{aviationDataID}")
    AviationDataSubscriptionsResponse fetchSubscriptions(@PathVariable String aviationDataID);

    @PostMapping("/api/subscription-redis/subscriptions/determine-subscription")
    boolean determineSubscription(@RequestBody FindSubscriptionRequest findSubscriptionRequest);

    @PostMapping("/api/subscription-redis/subscriptions/find-subscription")
    SubscriptionResponse findSubscription(@RequestBody FindSubscriptionRequest findSubscriptionRequest);

    @DeleteMapping("/api/subscription-redis/subscriptions/{aviationDataID}")
    void clear(@PathVariable String aviationDataID);

    @DeleteMapping("/api/subscription-redis/subscriptions/unsubscribe")
    void unsubscribe(@RequestBody UnsubscriptionRequest unsubscriptionRequest);

    @DeleteMapping("/api/subscription-redis/subscriptions/unsubscribe-from-all")
    void unsubscribeAll(@RequestBody UnsubscriptionFromAllRequest unsubscriptionFromAllRequest);

    @DeleteMapping("/api/subscription-redis/subscriptions")
    void wipe();

}