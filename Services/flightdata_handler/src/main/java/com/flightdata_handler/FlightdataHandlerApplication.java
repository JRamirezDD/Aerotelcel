package com.flightdata_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.flightdata_handler.model")
@Slf4j
public class FlightdataHandlerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FlightdataHandlerApplication.class, args);
		log.info("FlightdataHandlerApplication Successfully Started");
	}
}
