package com.subscription_redis.dto;

import com.subscription_redis.model.AviationDataSubscriptions;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AviationDataSubscriptionsResponse extends AviationDataSubscriptions { }
