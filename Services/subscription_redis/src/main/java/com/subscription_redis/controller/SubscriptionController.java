package com.subscription_redis.controller;

import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.*;
import com.subscription_redis.model.EmailAlreadySubscribedException;
import com.subscription_redis.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/subscription-redis")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/")
    public String home() {
        return "Hello, World";
    }

    @PostMapping("/subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        try {
            subscriptionService.saveSubscription(subscriptionRequest);
        } catch (EmailAlreadySubscribedException e) {
            log.info("Email ({}) Already Subscribed to ({}) -> EmailAlreadySubscribedException ({})", subscriptionRequest.getEmail(), subscriptionRequest.getAviationDataID() ,e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email (" + subscriptionRequest.getEmail() + ") is already subscribed", e);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AviationDataSubscriptionsResponse> fetchAllSubscriptions() {
        try {
            return subscriptionService.fetchAllSubscriptions();
        } catch (NullPointerException e) {
            log.info("FetchAll -> NullPointerException ({})", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "FetchAll Returned No Results");
        }

    }


    @GetMapping("/{aviationDataID}")
    @ResponseStatus(HttpStatus.OK)
    public AviationDataSubscriptionsResponse fetchSubscriptions(@PathVariable String aviationDataID) {
        try {
            return subscriptionService.fetchSubscriptions(aviationDataID);
        } catch (NullPointerException e) {
            log.info("Fetch AviationDataID ({}) -> NullPointerException: ({})", aviationDataID, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubscriptionList (" + aviationDataID + ") Returned No Results", e);
        }
    }

    @GetMapping("/subscription/{aviationDataID}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionResponse findSubscription(@PathVariable String aviationDataID, @PathVariable String email) {
        try {
            return subscriptionService.findSubscription(aviationDataID, email);
        } catch (NullPointerException e) {
            log.info("Fetch aviationDataID {}, Email {} -> NullPointerException: {}", aviationDataID, email, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription from (" + email + ") to (" + aviationDataID + ") Returned No Results", e);
        }
    }

    // Clear aviationDataSubscriptions
    @DeleteMapping("/{aviationDataID}")
    @ResponseStatus(HttpStatus.OK)
    public void clear(@PathVariable String aviationDataID) {
        subscriptionService.wipeSubscriptionsList(aviationDataID);
    }

    // Unsubscribe
    @DeleteMapping("/subscription/{aviationDataID}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribe(@PathVariable String aviationDataID, @PathVariable String email) {
        subscriptionService.unsubscribe(aviationDataID, email);
    }

    // Unsubscribe from all
    @DeleteMapping("/subscription/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribeAll(@PathVariable String email) {
        subscriptionService.unsubscribeFromAll(email);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void wipe() {
        subscriptionService.wipeRepository();
    }
}
