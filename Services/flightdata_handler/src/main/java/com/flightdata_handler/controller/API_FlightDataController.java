/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_handler.controller;

import com.flightdata_handler.dto.FlightResponse;
import com.flightdata_handler.dto.FlightDataResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface API_FlightDataController {
    @GetMapping("")
    String home();

    @GetMapping("/updateAllStates")
    void updateAllStates() throws Exception;

    @GetMapping("/getAllStates")
    List<FlightResponse> getAllFlights();

    @GetMapping("/getFlightByCallsign/{callsign}")
    FlightDataResponse getFlightByCallsign(@PathVariable String callsign);

    @DeleteMapping("/deleteAllStates")
    void deleteAllStates();
}
