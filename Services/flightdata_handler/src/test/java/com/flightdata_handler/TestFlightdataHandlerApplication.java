package com.flightdata_handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

//@TestConfiguration(proxyBeanMethods = false)
public class TestFlightdataHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.from(FlightdataHandlerApplication::main).with(FlightdataHandlerApplicationTests.class).run(args);
    }

}