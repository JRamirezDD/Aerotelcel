/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_handler.controller;

import com.flightdata_handler.dto.AirportResponse;

import com.flightdata_handler.service.AirportService;
import com.flightdata_handler.model.Airport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/airportDataController")
@Slf4j
public class AirportDataController implements API_AirportDataController {

    @Autowired
    private AirportService airportService;

    public String home(){
        return "This is AirportDataController";
    }

    //@Scheduled(fixedRate = 7200000*2)     // 4 hours update
    @ResponseStatus(HttpStatus.OK)
    public void updateAllAirports() throws Exception {
        try {
            airportService.updateAllAirports();
        } catch (Exception e){
            log.error("Error while updating all airports: " + e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    public void updateSelectedAirport(String iata) throws Exception {
        try {
            airportService.updateSelectedAirport(iata);
        } catch (Exception e){
            log.error("Error while updating airport " + iata + ": " + e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    public List<AirportResponse> getAllAirports(){
        return airportService.getAllAirports();
    }

    @ResponseStatus(HttpStatus.OK)
    public AirportResponse getAirportByCode(String iata){
        Airport airport = airportService.getAirportByIata(iata).orElse(null);
        return airport != null ? new AirportResponse(airport) : null;
    }

    @ResponseStatus(HttpStatus.OK)
    public void deleteAllAirports(){
        airportService.cleanDB();
    }
}
