/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_handler.controller;

import com.flightdata_handler.dto.AirportResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;



public interface API_AirportDataController {
    @GetMapping("/")
    String home();

    @GetMapping("/updateAllAirports")
    void updateAllAirports() throws Exception;

    @GetMapping("/updateSelectedAirport/{iata}")
    void updateSelectedAirport(@PathVariable String iata) throws Exception;

    @GetMapping("/getAllAirports")
    List<AirportResponse> getAllAirports();

    @GetMapping("/getAirportByCode/{iata}")
    AirportResponse getAirportByCode(@PathVariable String iata);

    @DeleteMapping
    void deleteAllAirports();
}
