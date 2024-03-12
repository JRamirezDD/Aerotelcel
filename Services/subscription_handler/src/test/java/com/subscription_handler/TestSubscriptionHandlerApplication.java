package com.subscription_handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSubscriptionHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.from(SubscriptionHandlerApplication::main).with(TestSubscriptionHandlerApplication.class).run(args);
	}

}
