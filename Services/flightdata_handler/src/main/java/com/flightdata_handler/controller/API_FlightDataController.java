package com.flightdata_handler.controller;

import com.flightdata_handler.dto.FlightResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface API_FlightDataController {
    @GetMapping("/")
    String home();

    @GetMapping("/updateAllStates")
    void updateAllStates() throws Exception;

    @GetMapping("/getAllStates")
    List<FlightResponse> getAllFlights();

    @GetMapping("/getFlightByCallsign/{Callsign}")
    FlightResponse getFlightByCallsign(String callsign);

    @DeleteMapping("/deleteAllStates")
    void deleteAllStates();
}
