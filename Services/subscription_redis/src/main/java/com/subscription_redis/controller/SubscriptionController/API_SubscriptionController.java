package com.subscription_redis.controller.SubscriptionController;

import com.subscription_redis.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/subscription-redis")
public interface API_SubscriptionController {

    @GetMapping("/")
    String home();

    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    void subscribe(@RequestBody SubscriptionRequest subscriptionRequest);

    @GetMapping("/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    List<AviationDataSubscriptionsResponse> fetchAllSubscriptions();

    @GetMapping("/subscriptions/{aviationDataID}")
    @ResponseStatus(HttpStatus.OK)
    AviationDataSubscriptionsResponse fetchSubscriptions(@PathVariable String aviationDataID);

    @PostMapping("/subscriptions/determine-subscription")
    @ResponseStatus(HttpStatus.OK)
    boolean determineSusbcription(@RequestBody FindSubscriptionRequest findSubscriptionRequest);

    @PostMapping("/subscriptions/find-subscription")
    @ResponseStatus(HttpStatus.OK)
    SubscriptionResponse findSubscription(@RequestBody FindSubscriptionRequest findSubscriptionRequest);

    @DeleteMapping("/subscriptions/{aviationDataID}")
    @ResponseStatus(HttpStatus.OK)
    void clear(@PathVariable String aviationDataID);

    @DeleteMapping("/subscriptions/unsubscribe")
    @ResponseStatus(HttpStatus.OK)
    void unsubscribe(@RequestBody UnsubscriptionRequest unsubscriptionRequest);

    // Adjusted to use @RequestBody for unsubscribeAll to align with the controller's method.
    @DeleteMapping("/subscriptions/unsubscribe")
    @ResponseStatus(HttpStatus.OK)
    void unsubscribeAll(@RequestBody UnsubscriptionFromAllRequest unsubscriptionFromAllRequest);

    @DeleteMapping("/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    void wipe();
}
