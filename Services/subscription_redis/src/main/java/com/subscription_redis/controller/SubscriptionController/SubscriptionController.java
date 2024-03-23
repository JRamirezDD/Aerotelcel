/*
 *    Title: SubscriptionController Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_redis.controller.SubscriptionController;

import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.*;
import com.subscription_redis.model.EmailAlreadySubscribedException;
import com.subscription_redis.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/subscription-redis")
public class SubscriptionController implements API_SubscriptionController {
    private final SubscriptionService subscriptionService;

    public String home() {
        return "Hello, World";
    }

    // Subscribe
    public void subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        try {
            subscriptionService.saveSubscription(subscriptionRequest);
        } catch (EmailAlreadySubscribedException e) {
            log.info("Email ({}) Already Subscribed to ({}) -> EmailAlreadySubscribedException ({})", subscriptionRequest.getEmail(), subscriptionRequest.getAviationDataID() ,e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email (" + subscriptionRequest.getEmail() + ") is already subscribed", e);
        }
    }

    // Fetch All Subscriptions
    public List<AviationDataSubscriptionsResponse> fetchAllSubscriptions() {
        try {
            return subscriptionService.fetchAllSubscriptions();
        } catch (NullPointerException e) {
            log.info("FetchAll -> NullPointerException ({})", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "FetchAll Returned No Results");
        }

    }

    // Fetch subscriptions to aviationDataID
    public AviationDataSubscriptionsResponse fetchSubscriptions(@PathVariable String aviationDataID) {
        try {
            return subscriptionService.fetchSubscriptions(aviationDataID);
        } catch (NullPointerException e) {
            log.info("Fetch AviationDataID ({}) -> NullPointerException: ({})", aviationDataID, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubscriptionList (" + aviationDataID + ") Returned No Results", e);
        }
    }

    // Determine whether email is subscribed or not
    public boolean determineSusbcription(@RequestBody FindSubscriptionRequest findSubscriptionRequest) {
        try {
            findSubscription(findSubscriptionRequest);
            log.info("Determine Subscription aviationDataID {}, Email {} -> TRUE", findSubscriptionRequest.aviationDataID(), findSubscriptionRequest.email());
            return true;
        } catch (ResponseStatusException e) {
            log.info("Determine Subscription aviationDataID {}, Email {} -> FALSE", findSubscriptionRequest.aviationDataID(), findSubscriptionRequest.email());
            return false;
        }
    }

    // Find subscription based on email and aviationDataID
    public SubscriptionResponse findSubscription(@RequestBody FindSubscriptionRequest findSubscriptionRequest) {
        try {
            return subscriptionService.findSubscription(findSubscriptionRequest.aviationDataID(), findSubscriptionRequest.email());
        } catch (NullPointerException e) {
            log.info("Fetch aviationDataID {}, Email {} -> NullPointerException: {}", findSubscriptionRequest.aviationDataID(), findSubscriptionRequest.email(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription from (" + findSubscriptionRequest.email() + ") to (" + findSubscriptionRequest.aviationDataID() + ") Returned No Results", e);
        }
    }

    // Clear aviationDataSubscriptions
    public void clear(@PathVariable String aviationDataID) {
        subscriptionService.wipeSubscriptionsList(aviationDataID);
    }

    // Unsubscribe
    public void unsubscribe(@RequestBody UnsubscriptionRequest unsubscriptionRequest) {
        subscriptionService.unsubscribe(unsubscriptionRequest.aviationDataID(), unsubscriptionRequest.email());
    }

    // Unsubscribe from all
    public void unsubscribeAll(@RequestBody UnsubscriptionFromAllRequest unsubscriptionFromAllRequest) {
        subscriptionService.unsubscribeFromAll(unsubscriptionFromAllRequest.email());
    }

    public void wipe() {
        subscriptionService.wipeRepository();
    }
}
