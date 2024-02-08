package com.subscription_redis.dto;

import com.subscription_redis.model.AviationDataSubscriptions;
import lombok.*;

import java.util.stream.Collectors;


@Data
@EqualsAndHashCode(callSuper = true)
public class AviationDataSubscriptionsResponse extends AviationDataSubscriptions {

    public AviationDataSubscriptionsResponse(AviationDataSubscriptions subscriptions) {
        // Directly set the fields inherited from the parent class
        this.setAviationDataID(subscriptions.getAviationDataID());
        this.setSubscriptions(subscriptions.getSubscriptions().stream()
                .map(subscription -> new SubscriptionResponse(subscription))
                .collect(Collectors.toList()));
    }
}
