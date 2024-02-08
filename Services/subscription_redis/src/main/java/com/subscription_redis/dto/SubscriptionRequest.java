package com.subscription_redis.dto;

import com.subscription_redis.model.Subscription;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class SubscriptionRequest extends Subscription {
    protected String aviationDataID;

    public SubscriptionRequest(Subscription subscription) {
        // Directly set the fields inherited from the parent class
        this.setName(subscription.getName());
        this.setEmail(subscription.getEmail());
    }
}
