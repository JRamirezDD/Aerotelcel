package com.subscription_handler.service;

import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionHandlerService {
    private final RestClient.Builder restClientBuilder;

    @Value("${Aerotelcel.subscriptionredis.uri}")
    private String subscriptionRedisURI;

    // Get Subscription
    public ResponseEntity<SubscriptionResponse> findSubscription(String aviationDataID, String email) {

        RestClient.ResponseSpec responseSpec =
                this.restClientBuilder.build().get()
                        .uri(UriComponentsBuilder
                                .fromUriString(subscriptionRedisURI)
                                .pathSegment(aviationDataID, email) // QueryParameters can be added with .queryParam
                                .toUriString())
                        .retrieve();

        return responseSpec.toEntity(SubscriptionResponse.class);

        // Call the service method to find the subscription
//        try {
//            return subscriptionHandlerService.findSubscription(aviationDataID, email);
//        } catch (SubscriptionNotFoundException ex) {
//            // Handle the exception and return appropriate HTTP status code
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
//        }
    }


    // Subscribe
    public ResponseEntity<Void> subscribe(SubscriptionRequest subscriptionRequest) {
        ResponseEntity<Void> responseEntity =
                this.restClientBuilder.build().post()
                    .uri(UriComponentsBuilder
                                .fromUriString(subscriptionRedisURI)
                                .pathSegment(subscriptionRequest.getAviationDataID())
                                .toUriString())
                .body(subscriptionRequest)
                .retrieve()
                .toBodilessEntity();

        return responseEntity;
    }

    // Unsubscribe
    public ResponseEntity<Void> unsubscribe(String aviationDataID, String email) {
        ResponseEntity<Void> responseEntity =
                this.restClientBuilder.build().delete()
                        .uri(UriComponentsBuilder
                                .fromUriString(subscriptionRedisURI)
                                .pathSegment(aviationDataID, email)
                                .toUriString())
                        .retrieve()
                        .toBodilessEntity();
        return responseEntity;
    }

    // Unsubscribe from all
    public ResponseEntity<Void> unsubscribeAll(String email) {
        ResponseEntity<Void> responseEntity =
                this.restClientBuilder.build().delete()
                        .uri(UriComponentsBuilder
                                .fromUriString(subscriptionRedisURI)
                                .pathSegment(email)
                                .toUriString())
                        .retrieve()
                        .toBodilessEntity();

        return responseEntity;
    }
}
