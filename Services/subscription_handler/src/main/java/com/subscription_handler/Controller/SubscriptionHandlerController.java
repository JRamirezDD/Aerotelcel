package com.subscription_handler.Controller;

import com.subscription_handler.Service.SubscriptionHandlerService;
import com.subscription_redis.dto.SubscriptionRequest;
import com.subscription_redis.dto.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/subscription-handler")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionHandlerController {
    private final SubscriptionHandlerService subscriptionService;

    // Test
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return subscriptionService.home();
    }

    // Get Subscription
    @GetMapping("/subscription/{aviationDataID}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionResponse findSubscription(@PathVariable String aviationDataID, @PathVariable String email) {
        return subscriptionService.findSubscription(aviationDataID, email);
    }


    // Subscribe
    @PostMapping("/subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.subscribe(subscriptionRequest);
    }

    // Unsubscribe
    @DeleteMapping("/subscription/{aviationDataID}/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribe(@PathVariable String aviationDataID, @PathVariable String email) {
        subscriptionService.unsubscribe(aviationDataID, email);
    }

    // Unsubscribe from all
    @DeleteMapping("/subscription/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void unsubscribeAll(@PathVariable String email) {
        subscriptionService.unsubscribeAll(email);
    }
}
