/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.notifications_handler.service.EventHandler.FlightEventHandler.FeignClients;

import com.subscription_redis.controller.SubscriptionController.API_SubscriptionController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${Aerotelcel.subscription-redis.controller.SubscriptionController.name}", url = "${Aerotelcel.subscription-redis.address}:${Aerotelcel.subscription-redis.port}${Aerotelcel.subscription-redis.controller.SubscriptionController.path}")
public interface Client_SubscriptionRedis extends API_SubscriptionController { }