package com.subscription_redis.dto;

import com.subscription_redis.model.Subscription;
import lombok.*;

public class SubscriptionRequest extends Subscription {

    public SubscriptionRequest(String aviationDataID, String name, String email) {
        super(aviationDataID, name, email);
    }

    public SubscriptionRequest(Subscription subscription) {
        super(subscription.getAviationDataID(), subscription.getName(), subscription.getEmail());
    }
}
