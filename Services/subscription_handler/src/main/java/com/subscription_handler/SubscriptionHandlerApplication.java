package com.subscription_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SubscriptionHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionHandlerApplication.class, args);
		log.info("SubscriptionHandlerApplication Successfully Started");
	}

}
