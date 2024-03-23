/*
 *    Title: EmailAlreadySubscribedException Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_redis.model;

public class EmailAlreadySubscribedException extends Exception {
    public EmailAlreadySubscribedException(String aviationDataID, String email) {
        super(String.format("Email {} already subscribed to {}", email, aviationDataID));
    }
}
