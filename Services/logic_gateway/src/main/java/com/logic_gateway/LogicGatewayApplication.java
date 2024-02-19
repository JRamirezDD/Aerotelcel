package com.logic_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LogicGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(LogicGatewayApplication.class, args);
	}


	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("subscription-handler", r -> r.path("/api/subscription-handler/**")
						.uri("http://localhost:10002"))
				.route("subscription-redis", r -> r.path("/api/subscription-redis/**")
						.uri("http://localhost:10000"))
				.build();
	}
}