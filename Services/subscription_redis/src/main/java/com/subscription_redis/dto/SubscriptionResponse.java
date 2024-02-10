package com.subscription_redis.dto;

import com.subscription_redis.model.Subscription;

public class SubscriptionResponse extends Subscription {

    public SubscriptionResponse(String aviationDataID, String name, String email) {
        super(aviationDataID, name, email);
    }

    public SubscriptionResponse(Subscription subscription) {
        super(subscription.getAviationDataID(), subscription.getName(), subscription.getEmail());
    }
}
