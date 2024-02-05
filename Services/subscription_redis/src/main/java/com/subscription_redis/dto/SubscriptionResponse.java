package com.subscription_redis.dto;

import com.subscription_redis.model.Subscription;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponse extends Subscription {
    protected String aviationDataID;
}
