package com.reportshandler.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"com.flightdata_handler.model", "com.reportshandler.model"})
public class JpaConfiguration {
}