package com.logic_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LogicGatewayApplication {

	@Value("${REPORTS_HANDLER_HOST:http://localhost}")
	private String reportsHandlerHost;
	@Value("${REPORTS_HANDLER_PORT:10010}")
	private String reportsHandlerPort;

	@Value("${SUBSCRIPTIONS_HANDLER_HOST:http://localhost}")
	private String subscriptionsHandlerHost;
	@Value("${SUBSCRIPTIONS_HANDLER_PORT:10002}")
	private String subscriptionsHandlerPort;

	@Value("${SUBSCRIPTIONS_REDIS_HOST:http://localhost}")
	private String subscriptionsRedisHost;
	@Value("${SUBSCRIPTIONS_REDIS_PORT:10000}")
	private String subscriptionsRedisPort;

	public static void main(String[] args) {
		SpringApplication.run(LogicGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("subscription-handler", r -> r.path("/api/subscription-handler/**")
						.uri(String.format("%s:%s", subscriptionsHandlerHost, subscriptionsHandlerPort)))
				.route("subscription-redis", r -> r.path("/api/subscription-redis/**")
						.uri(String.format("%s:%s", subscriptionsRedisHost, subscriptionsRedisPort)))
				.route("reports-handler", r -> r.path("/api/reports-handler/**")
						.uri(String.format("%s:%s", reportsHandlerHost, reportsHandlerPort)))
				.build();
	}
}
