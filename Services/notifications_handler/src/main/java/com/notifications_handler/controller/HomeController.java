/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.notifications_handler.controller;

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
        return "Notifications Handler Home";
    }

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String health() {
        return "Healthy";
    }
}
