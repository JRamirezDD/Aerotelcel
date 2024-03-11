package com.flightdata_retriever.service.FeignClients;

import com.flightdata_handler.controller.API_FlightDataController;
import org.springframework.cloud.openfeign.FeignClient;

// "${Aerotelcel.flightdata_handler.address}:${Aerotelcel.flightdata_handler.port}"

@FeignClient(name = "verga", url = "http://localhost:8181/api/flightController")
public interface RetrieveFlights extends API_FlightDataController { }
