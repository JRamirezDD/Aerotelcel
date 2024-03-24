/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_retriever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients
@ComponentScan({"com.flightdata_retriever", "com.flightdata_handler"})
@SpringBootApplication
public class FlightdataRetrieverApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightdataRetrieverApplication.class, args);
	}

}
