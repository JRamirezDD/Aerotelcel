package com.subscription_redis.controller;

import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.*;
import com.subscription_redis.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    @Autowired
    private final SubscriptionService subscriptionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.saveSubscription(subscriptionRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubscriptionResponse> getAllSubscriptions() {
        return subscriptionService.fetchSubscriptionList();
    }

    @GetMapping("/{id}")
    public SubscriptionResponse test() {
        return null;
    }

}