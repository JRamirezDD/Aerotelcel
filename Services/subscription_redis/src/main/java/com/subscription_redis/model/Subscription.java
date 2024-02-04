package com.subscription_redis.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("subscription")
public class Subscription {
    @Id
    private String id;
    private String aviationDataID;
    private String name;
    @Indexed                        // Index'd for querying in case of unsubscription
    private String email;
}