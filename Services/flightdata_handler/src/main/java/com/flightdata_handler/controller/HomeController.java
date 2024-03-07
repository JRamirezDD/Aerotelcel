package com.flightdata_handler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flightdata_handler.service.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class HomeController {

    private final PythonTestClass pythonTestClass;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "Flight_Data Handler Home";
    }

    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public String health() {
        return "Healthy";
    }

    @GetMapping("/pythonTest")
    @ResponseStatus(HttpStatus.OK)
    public String pythonTest() {
        pythonTestClass.testPython();
        return "Python Test Done";
    }
}
