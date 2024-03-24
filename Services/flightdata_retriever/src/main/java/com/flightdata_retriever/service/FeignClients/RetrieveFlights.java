/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_retriever.service.FeignClients;

import com.flightdata_handler.controller.API_FlightDataController;
import org.springframework.cloud.openfeign.FeignClient;

// "${Aerotelcel.flightdata_handler.address}:${Aerotelcel.flightdata_handler.port}"

@FeignClient(name = "flightretrieve", url = "http://localhost:8181/api/flightController")
public interface RetrieveFlights extends API_FlightDataController { }
