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
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    @Autowired
    private final SubscriptionService subscriptionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        log.info("Call");
        try {
            subscriptionService.saveSubscription(subscriptionRequest);
        } catch (emailAlreadySubscribedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already subscribed", e);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AviationDataSubscriptionsResponse> getAllSubscriptions() {
        return subscriptionService.fetchSubscriptionList();
    }

    @GetMapping("/{id}")
    public SubscriptionResponse test() {
        return null;
    }

}