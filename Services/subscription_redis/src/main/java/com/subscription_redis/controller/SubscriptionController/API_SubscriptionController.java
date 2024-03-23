/*
 *    Title: API_SubscriptionController Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_redis.controller.SubscriptionController;

import com.subscription_redis.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface API_SubscriptionController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    String home();

    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    void subscribe(@RequestBody SubscriptionRequest subscriptionRequest);

    @GetMapping("/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    List<AviationDataSubscriptionsResponse> fetchAllSubscriptions();

    @GetMapping("/subscriptions/{aviationDataID}")
    @ResponseStatus(HttpStatus.OK)
    AviationDataSubscriptionsResponse fetchSubscriptions(@PathVariable(value="aviationDataID") String aviationDataID);

    @PostMapping("/subscriptions/determine-subscription")
    @ResponseStatus(HttpStatus.OK)
    boolean determineSusbcription(@RequestBody FindSubscriptionRequest findSubscriptionRequest);

    @PostMapping("/subscriptions/find-subscription")
    @ResponseStatus(HttpStatus.OK)
    SubscriptionResponse findSubscription(@RequestBody FindSubscriptionRequest findSubscriptionRequest);

    @DeleteMapping("/subscriptions/{aviationDataID}")
    @ResponseStatus(HttpStatus.OK)
    void clear(@PathVariable(value="aviationDataID") String aviationDataID);

    @DeleteMapping("/subscriptions/unsubscribe")
    @ResponseStatus(HttpStatus.OK)
    void unsubscribe(@RequestBody UnsubscriptionRequest unsubscriptionRequest);

    // Adjusted to use @RequestBody for unsubscribeAll to align with the controller's method.
    @DeleteMapping("/subscriptions/unsubscribe-from-all")
    @ResponseStatus(HttpStatus.OK)
    void unsubscribeAll(@RequestBody UnsubscriptionFromAllRequest unsubscriptionFromAllRequest);

    @DeleteMapping("/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    void wipe();
}
