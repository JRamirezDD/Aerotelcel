package com.subscription_redis.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;



@Data
@Builder
@AllArgsConstructor
@RedisHash("subscription")
public class Subscription {
    protected final String aviationDataID;
    protected final String name;
    @Id
    protected final String email;
}