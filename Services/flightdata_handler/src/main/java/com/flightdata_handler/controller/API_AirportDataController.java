package com.flightdata_handler.controller;

import com.flightdata_handler.dto.AirportResponse;
import com.flightdata_handler.model.Airport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface API_AirportDataController {
    @GetMapping("/")
    String home();

    @PostMapping("/updateAllAirports")
    void updateAllAirports() throws Exception;

    @PostMapping("/updateSelectedAirport/{icao}")
    void updateSelectedAirport(String icao) throws Exception;

    @GetMapping("/getAllAirports")
    List<AirportResponse> getAllAirports();

    @GetMapping("/getAirportByCode/{iata}")
    AirportResponse getAirportByCode(String iata);

    @DeleteMapping
    void deleteAllAirports();
}
