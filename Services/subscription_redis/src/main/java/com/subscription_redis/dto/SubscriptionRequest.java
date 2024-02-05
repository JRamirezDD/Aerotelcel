package com.subscription_redis.dto;

import com.subscription_redis.model.Subscription;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest extends Subscription {
    protected String aviationDataID;
}
