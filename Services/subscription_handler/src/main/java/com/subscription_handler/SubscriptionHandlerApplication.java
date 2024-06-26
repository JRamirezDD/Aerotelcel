/*
 *    Title: SubscriptionHandlerApplication Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.subscription_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class SubscriptionHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionHandlerApplication.class, args);
		log.info("SubscriptionHandlerApplication Successfully Started");
	}

}
