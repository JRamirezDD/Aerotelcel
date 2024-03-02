package com.subscription_redis.controller.SubscriptionController;

import com.subscription_redis.dto.AviationDataSubscriptionsResponse;
import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.SubscriptionResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface API_SubscriptionController {

    @GetMapping("/")
    String home();

    @PostMapping("/subscriptions")
    void subscribe(@RequestBody SubscriptionRequest subscriptionRequest);

    @GetMapping("/subscriptions")
    List<AviationDataSubscriptionsResponse> fetchAllSubscriptions();

    @GetMapping("/subscriptions/{aviationDataID}")
    AviationDataSubscriptionsResponse fetchSubscriptions(@PathVariable String aviationDataID);

    @GetMapping("/subscriptions/{aviationDataID}/{email}")
    SubscriptionResponse findSubscription(@PathVariable String aviationDataID, @PathVariable String email);

    @DeleteMapping("/subscriptions/{aviationDataID}")
    void clear(@PathVariable String aviationDataID);

    @DeleteMapping("/subscriptions/{aviationDataID}/{email}")
    void unsubscribe(@PathVariable String aviationDataID, @PathVariable String email);

    @DeleteMapping("/subscriptions/email/{email}")
    void unsubscribeAll(@PathVariable String email);

    @DeleteMapping
    void wipe();

}
