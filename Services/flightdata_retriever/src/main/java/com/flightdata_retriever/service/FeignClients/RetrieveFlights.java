package com.flightdata_retriever.service.FeignClients;

import com.flightdata_handler.controller.API_FlightDataController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${Aerotelcel.flightdata_handler.FlightDataController.name}", url = "${Aerotelcel.flightdata_handler.address}:${Aerotelcel.flightdata_handler.port}/${Aerotelcel.flightdata_handler.FlightDataController.path}")
public interface RetrieveFlights extends API_FlightDataController { }
