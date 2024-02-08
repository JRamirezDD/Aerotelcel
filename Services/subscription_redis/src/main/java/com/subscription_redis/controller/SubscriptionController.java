package com.subscription_redis.controller;

import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.*;
import com.subscription_redis.model.AviationDataSubscriptions;
import com.subscription_redis.model.emailAlreadySubscribedException;
import com.subscription_redis.service.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/subscription-redis")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    @Autowired
    private final SubscriptionService subscriptionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        log.info("Call");
        try {
            subscriptionService.saveSubscription(subscriptionRequest);
        } catch (emailAlreadySubscribedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already subscribed", e);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AviationDataSubscriptionsResponse> fetchAllSubscriptions() {
        return subscriptionService.fetchSubscriptionsList().collectList().block();
    }


    @GetMapping("/{AviationDataID}")
    @ResponseStatus(HttpStatus.OK)
    public AviationDataSubscriptionsResponse fetchSubscriptions(@PathVariable String AviationDataID) {
        return subscriptionService.fetchSubscriptions(AviationDataID).block();
    }

    @GetMapping("/{AviationDataID}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionResponse findSubscription(@PathVariable String AviationDataID, @PathVariable String email) {
        return null;
    }

    @DeleteMapping("/{AviationDataID}")
    public void clear(@PathVariable String AviationDataID) {
        return;
    }

    @DeleteMapping("/{AviationDataID}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribe(@PathVariable String AviationDataID, @PathVariable String email) {
        return;
    }
}