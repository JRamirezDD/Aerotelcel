package com.flightdata_retriever.service.FeignClients;

import com.flightdata_handler.controller.API_AirportDataController;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${Aerotelcel.flightdata_handler.AirportDataController.name}", url = "${Aerotelcel.flightdata_handler.address}:${Aerotelcel.flightdata_handler.port}/${Aerotelcel.flightdata_handler.AirportDataController.path}")
public interface RetrieveAirport extends API_AirportDataController { }
