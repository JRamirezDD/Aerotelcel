/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_retriever.service.FeignClients;

import com.flightdata_handler.controller.API_AirportDataController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mono", url = "http://localhost:8181/api/airportDataController/")
public interface RetrieveAirport extends API_AirportDataController { }
