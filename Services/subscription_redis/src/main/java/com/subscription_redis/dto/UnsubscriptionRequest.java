package com.subscription_redis.dto;

public record UnsubscriptionRequest(String email, String aviationDataID) {
}
