/*
 *    Title: SubscriptionHandlerController Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_handler.Controller;

import com.subscription_handler.Service.SubscriptionHandlerService;
import com.subscription_redis.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription-handler")
@Slf4j
public class SubscriptionHandlerController {
    private final SubscriptionHandlerService subscriptionService;

    // Repository Connectivity Test
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return subscriptionService.home();
    }

    // Determine whether email is subscribed or not.
    @PostMapping("/subscriptions/determine-subscription")
    @ResponseStatus(HttpStatus.OK)
    public boolean determineSubscription(@RequestBody FindSubscriptionRequest findSubscriptionRequest) {
        return subscriptionService.determineSubscription(findSubscriptionRequest);
    }

    // Subscribe
    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.subscribe(subscriptionRequest);
    }

    // Unsubscribe
    @DeleteMapping("/subscriptions/unsubscribe")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribe(@RequestBody UnsubscriptionRequest unsubscriptionRequest) {
        subscriptionService.unsubscribe(unsubscriptionRequest);
    }

    // Unsubscribe from all
    @DeleteMapping("/subscriptions/unsubscribe-from-all")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribeAll(@RequestBody UnsubscriptionFromAllRequest unsubscriptionFromAllRequest) {
        subscriptionService.unsubscribeAll(unsubscriptionFromAllRequest);
    }
}
