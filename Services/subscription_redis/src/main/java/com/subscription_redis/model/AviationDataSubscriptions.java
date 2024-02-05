package com.subscription_redis.model;

import com.subscription_redis.dto.AviationDataSubscriptionsResponse;
import com.subscription_redis.dto.SubscriptionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("AviationDataSubscriptions")
public class AviationDataSubscriptions {
    @Id
    private String aviationDataID;
    private List<Subscription> subscriptions;

    public void addSubscription(Subscription subscription) throws emailAlreadySubscribedException {
        for (Subscription subs : subscriptions) {
            if (Objects.equals(subs.email, subscription.email))
                throw new emailAlreadySubscribedException(subscription.email, aviationDataID);
        }
        subscriptions.add(subscription);
    }

    public void removeEmail(String email) {
        subscriptions.removeIf(subscription -> Objects.equals(subscription.email, email));
    }

    public final AviationDataSubscriptionsResponse convertToResponse() {
        return (AviationDataSubscriptionsResponse) AviationDataSubscriptionsResponse.builder()
                .aviationDataID(this.getAviationDataID())
                .subscriptions(this.getSubscriptions()
                        .stream()
                        .map(subscription -> SubscriptionResponse.builder()
                                .name(subscription.getName())
                                .email(subscription.getEmail())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
