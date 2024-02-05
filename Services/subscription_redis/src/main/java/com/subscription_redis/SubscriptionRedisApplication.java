package com.subscription_redis;

import com.subscription_redis.controller.SubscriptionController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
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
