package com.subscription_handler.Service.FeignClients;

import com.subscription_redis.controller.SubscriptionController.API_SubscriptionController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${Aerotelcel.subscription-redis.controller.SubscriptionController.name}", url = "${Aerotelcel.subscription-redis.address}:${Aerotelcel.subscription-redis.port}")
public interface Client_SubscriptionRedis extends API_SubscriptionController { }