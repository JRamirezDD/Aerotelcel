/*
 *    Title: SubscriptionRedisApplication Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
@Slf4j
public class SubscriptionRedisApplication {

	public static void main(String[] args) {

		SpringApplication.run(SubscriptionRedisApplication.class, args);
		log.info("SubscriptionRedisApplication Successfully Started");
	}
}
