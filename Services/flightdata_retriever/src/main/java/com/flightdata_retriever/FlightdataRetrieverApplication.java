package com.flightdata_retriever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.flightdata_retriever", "com.flightdata_handler"})
public class FlightdataRetrieverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightdataRetrieverApplication.class, args);
	}

}
