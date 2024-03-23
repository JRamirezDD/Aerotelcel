/*
 *    Title: SubscriptionService Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_redis.service;

import com.subscription_redis.dto.AviationDataSubscriptionsResponse;
import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.SubscriptionResponse;
import com.subscription_redis.model.AviationDataSubscriptions;
import com.subscription_redis.model.Subscription;
import com.subscription_redis.model.EmailAlreadySubscribedException;
import com.subscription_redis.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional // Ensures Integrity and Completeness of all operations
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;


    // Read Operations - Fetch All Subscriptions
    public List<AviationDataSubscriptionsResponse> fetchAllSubscriptions() {
        List<AviationDataSubscriptionsResponse> subscriptionsResponses = new ArrayList<>();
        subscriptionRepository.findAll().forEach(subscription -> {
            subscriptionsResponses.add(new AviationDataSubscriptionsResponse(subscription));
        });
        if (subscriptionsResponses.isEmpty()) {
            throw new NullPointerException("No Subscriptions found");
        }
        return subscriptionsResponses;
    }

    // Read Operation - Fetch Subscriptions
    public AviationDataSubscriptionsResponse fetchSubscriptions(String aviationDataID) {
        return new AviationDataSubscriptionsResponse(getAviationDataSubscriptions(aviationDataID));
    }

    // Read Operation - Find Subscription
    public SubscriptionResponse findSubscription(String aviationDataID, String email) {
        return new SubscriptionResponse(getSubscription(getAviationDataSubscriptions(aviationDataID), email));
    }

    // Read Operation - Get Subscriptions
    private AviationDataSubscriptions getAviationDataSubscriptions(String aviationDataID) {
        return subscriptionRepository.findById(aviationDataID).orElseThrow(() ->
                new NullPointerException("Subscriptions not found for ID: " + aviationDataID));
    }

    // Read Operation - Find Subscriptions
    private Subscription getSubscription(AviationDataSubscriptions aviationDataSubscriptions, String email){
        return aviationDataSubscriptions.getSubscriptions()
                .stream()
                .filter(subscription -> subscription.getEmail().equals(email))
                .findFirst().orElseThrow(()
                        -> new NullPointerException("Subscription not found for Email: " + email + " for ID: " + aviationDataSubscriptions.getAviationDataID()));
    }

    // Write Operation - Save Subscription
    public void saveSubscription(SubscriptionRequest subscriptionRequest) throws EmailAlreadySubscribedException {
        String aviationDataID = subscriptionRequest.getAviationDataID();

        Subscription subscription = Subscription.builder()
                .name(subscriptionRequest.getName())
                .email(subscriptionRequest.getEmail())
                .build();

        Optional<AviationDataSubscriptions> aviationDataSubscriptionsOptional = subscriptionRepository.findById(aviationDataID);
        AviationDataSubscriptions aviationDataSubscriptions = aviationDataSubscriptionsOptional.orElseGet(() ->
                AviationDataSubscriptions.builder()
                        .aviationDataID(aviationDataID)
                        .subscriptions(new ArrayList<>())
                        .build());

        try {
            aviationDataSubscriptions.addSubscription(subscription);
            subscriptionRepository.save(aviationDataSubscriptions);
            log.info("Subscription email: {} to aviationDataID: {} is saved", subscription.getEmail(), aviationDataID);
        } catch (EmailAlreadySubscribedException e) {
            log.error("Error saving subscription: {}", e.getMessage());
            throw e;
        }
    }

    // Write Operation - UnsubscribeEmail
    public void unsubscribe(String aviationDataID, String email) {
        AviationDataSubscriptions aviationDataSubscriptions = getAviationDataSubscriptions(aviationDataID);
        aviationDataSubscriptions.removeEmail(email);
        subscriptionRepository.save(aviationDataSubscriptions);
    }

    // Write Operation - Unsubscribe Email From All aviationDataSubscriptions
    public void unsubscribeFromAll(String email) {
        // Retrieve all aviationDataSubscriptions
        Iterable<AviationDataSubscriptions> allSubscriptions = subscriptionRepository.findAll();

        // Iterate through each aviationDataSubscriptions
        for (AviationDataSubscriptions aviationDataSubscriptions : allSubscriptions) {
            // Remove subscriptions with the specified email
            aviationDataSubscriptions.removeEmail(email);
            // Save the updated aviationDataSubscriptions
            subscriptionRepository.save(aviationDataSubscriptions);
        }
    }

    // Write Operation - Wipe aviationDataSubscriptions Object
    public void wipeSubscriptionsList(String aviationDataID) {
        subscriptionRepository.deleteById(aviationDataID);
    }

    // Write Operation - Wipe Repository
    public void wipeRepository() {
        subscriptionRepository.deleteAll();
    }
}
