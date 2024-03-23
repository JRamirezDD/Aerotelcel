/*
 *    Title: AviationDataSubscriptions Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_redis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("AviationDataSubscriptions")
public class AviationDataSubscriptions {
    @Id
    private String aviationDataID;
    private List<Subscription> subscriptions;

    public void addSubscription(Subscription subscription) throws EmailAlreadySubscribedException {
        try {
            for (Subscription subs : subscriptions) {
                if (Objects.equals(subs.email, subscription.email))
                    throw new EmailAlreadySubscribedException(subscription.email, aviationDataID);
            }
            subscriptions.add(subscription);
        } catch (NullPointerException e) {
            this.subscriptions = new ArrayList<>();
            this.subscriptions.add(subscription);
        }
    }

    public void removeEmail(String email) {
        subscriptions.removeIf(subscription -> Objects.equals(subscription.email, email));
    }

    public Subscription findSubscription(String email) {
        for (Subscription subscription : subscriptions) {
            if (Objects.equals(subscription.getEmail(), email)) {
                return subscription;
            }
        }

        return null;
    }
}
