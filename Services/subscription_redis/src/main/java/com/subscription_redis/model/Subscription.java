package com.subscription_redis.model;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("subscription")
public class Subscription {
    protected String name;
    @Indexed
    protected String email;
}