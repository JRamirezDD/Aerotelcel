/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_handler.controller;

import com.flightdata_handler.dto.FlightDataResponse;
import com.flightdata_handler.dto.FlightResponse;
import com.flightdata_handler.service.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/flightController")
@Slf4j

public class FlightDataController implements API_FlightDataController {
    private final ReadAllStates readAllStates;
    private final PackageFlightService packageFlightService;

    @Autowired
    public FlightDataController(ReadAllStates readAllStates, PackageFlightService packageFlightService){
        log.info("FlightDataController Started");
        this.readAllStates = readAllStates;
        this.packageFlightService = packageFlightService;
    }

    public String home(){
        return "This is FlightDataController";
    }

    //@Scheduled(fixedRate = 7200000)     // 2 hours update
    @ResponseStatus(HttpStatus.OK)
    public void updateAllStates() throws Exception {
        log.info("Updating all states, we're at flightDataController\n");

        try {
            log.info("Were at the try...\n");
            if (readAllStates == null) {
                log.error("ReadAllStates is null");
                throw new NullPointerException("ReadAllStates is null");
            }

            log.info("First if passed, checking method\n");

            readAllStates.doSearch();

        } catch (Exception e){
            log.info("There was a problem updating all states, Exception:" + e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    public List<FlightResponse> getAllFlights(){
        return readAllStates.getFlights();
    }

    @ResponseStatus(HttpStatus.OK)
    public FlightDataResponse getFlightByCallsign(String callsign){
        // return flight from DB
        return new FlightDataResponse(packageFlightService.getFlightByCallsign(callsign));
    }

    @ResponseStatus(HttpStatus.OK)
    public void deleteAllStates(){
        readAllStates.cleanDB();
    }
}
