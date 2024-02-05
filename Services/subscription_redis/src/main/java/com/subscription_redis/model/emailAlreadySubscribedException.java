package com.subscription_redis.model;

import lombok.extern.slf4j.Slf4j;

public class emailAlreadySubscribedException extends Exception {
    public emailAlreadySubscribedException(String aviationDataID, String email) {
        super(String.format("Email {} already subscribed to {}", email, aviationDataID));
    }
}
