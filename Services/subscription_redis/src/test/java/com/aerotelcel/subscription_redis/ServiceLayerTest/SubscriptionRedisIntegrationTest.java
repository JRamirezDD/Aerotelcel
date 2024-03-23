/*
 *    Title: SubscriptionRedisIntegrationTest Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.aerotelcel.subscription_redis.ServiceLayerTest;

import com.subscription_redis.SubscriptionRedisApplication;
import com.subscription_redis.config.RedisConfig;
import com.subscription_redis.dto.AviationDataSubscriptionsResponse;
import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.model.EmailAlreadySubscribedException;
import com.subscription_redis.service.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest(classes = SubscriptionRedisApplication.class)
@Testcontainers
@Import({RedisConfig.class, SubscriptionService.class})
public class SubscriptionRedisIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionRedisIntegrationTest.class);

    @Container
    public static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:latest"))
            .withExposedPorts(6379)
            .waitingFor(Wait.forListeningPort());       // Makes Test Execution to wait for Redis Container to be available

    @Autowired
    private SubscriptionService subscriptionService;

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        String redisUrl = "redis://" + redis.getHost() + ":" + redis.getFirstMappedPort();
        log.info("Configuring Redis URL for test: {}", redisUrl);
        registry.add("spring.data.redis.host", () -> redis.getHost());              // Replaces attribute in application.properties
        registry.add("spring.data.redis.port", () -> redis.getFirstMappedPort());   // Replaces attribute in application.properties
    }

    @Test
    void testSubscriptionLifecycle() throws EmailAlreadySubscribedException {
        log.info("Starting testSubscriptionLifecycle test");

        // Given a new subscription request
        SubscriptionRequest request = new SubscriptionRequest("aviationDataID1", "John Doe", "johndoe@example.com");
        log.info("Created a new subscription request for {}", request.getEmail());
        log.info(String.valueOf(request));

        // When the subscription is saved
        log.info("Attempting to save the subscription");
        subscriptionService.saveSubscription(request);

        // Then the subscription should be retrievable
        log.info("Fetching subscriptions for ID: {}", request.getAviationDataID());
        AviationDataSubscriptionsResponse response = subscriptionService.fetchSubscriptions(request.getAviationDataID());
        log.info(String.valueOf(response));
        assertThat(response.getSubscriptions()).hasSize(1);
        log.info("Assertion passed: Subscription is retrievable");

        // When unsubscribing
        log.info("Attempting to unsubscribe email: {}", request.getEmail());
        subscriptionService.unsubscribe(request.getAviationDataID(), request.getEmail());

        // Then the subscription should no longer exist
        log.info("Verifying the subscription for email {} no longer exists", request.getEmail());
        Throwable thrown = catchThrowable(() -> subscriptionService.fetchSubscriptions(request.getAviationDataID()));
        assertThat(thrown).isInstanceOf(NullPointerException.class); // Or your custom exception
        log.info("Assertion passed: Subscription is no longer retrievable");

        log.info("Completed testSubscriptionLifecycle test");
    }
}
