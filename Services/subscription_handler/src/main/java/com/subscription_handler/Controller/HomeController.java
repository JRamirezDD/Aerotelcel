package com.subscription_handler.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "Subscription Handler Home";
    }

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String health() {
        return "Healthy";
    }
}
