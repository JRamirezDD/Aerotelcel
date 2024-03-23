/*
 *    Title: SubscriptionResponse Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

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
