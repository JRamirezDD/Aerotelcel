/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_retriever.controller;

import com.flightdata_handler.dto.FlightDataResponse;
import com.flightdata_handler.dto.FlightResponse;
import com.flightdata_retriever.service.RetrieveFlightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flightDataRetriever")
@RequiredArgsConstructor
@Slf4j
public class FlightDataRetriever {
    private final RetrieveFlightsService retrieveFlights;

    @RequestMapping("")
    public String home(){
        return retrieveFlights.home();
    }

    @RequestMapping("/updateFlights")
    public void updateFlights() throws Exception {
        retrieveFlights.updateFlights();
    }

    @RequestMapping("/getFlights")
    public List<FlightResponse> getFlights() {
        return retrieveFlights.getFlights();
    }

    @RequestMapping("/getFlightByCallsign/{callsign}")
    public FlightDataResponse getFlightByCallsign(@PathVariable String callsign) {
        return retrieveFlights.getFlightByCallsign(callsign);
    }
}
