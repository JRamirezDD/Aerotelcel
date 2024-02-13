package com.subscription_handler.controller;

import com.subscription_handler.service.SubscriptionHandlerService;
import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/subscription-handler")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionHandlerController {
    private final SubscriptionHandlerService subscriptionService;

    // Get Subscription
    @GetMapping("/subscription/{aviationDataID}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubscriptionResponse> findSubscription(@PathVariable String aviationDataID, @PathVariable String email) {
        return subscriptionService.findSubscription(aviationDataID, email);
    }


    // Subscribe
    @PostMapping("/subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        return subscriptionService.subscribe(subscriptionRequest);
    }

    // Unsubscribe
    @DeleteMapping("/subscription/{aviationDataID}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> unsubscribe(@PathVariable String aviationDataID, @PathVariable String email) {
        return subscriptionService.unsubscribe(aviationDataID, email);
    }

    // Unsubscribe from all
    @DeleteMapping("/subscription/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> unsubscribeAll(@PathVariable String email) {
        return  subscriptionService.unsubscribeAll(email);
    }
}
