package com.flightdata_handler.controller;

import com.flightdata_handler.repository.AirportRepository;
import com.flightdata_handler.model.Airport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airportDataController")
@Slf4j
public class AirportDataController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/")
    public String home(){
        return "This is AirportDataController";
    }

    @PostMapping("/updateAllAirports")
    public void createAirports() throws Exception {
        List<Airport> airports = airportRepository.findAll();

        for(Airport airport : airports){
            log.info("Updating airport: " + airport.getAirportName() + "\n");

            try {
                airport.updateArrivals();
            } catch (Exception e){
                log.error("Error while updating arrivals at " + airport.getAirportName() + ": " + e);
            }

            try {
                airport.updateDepartures();
            } catch (Exception e){
                log.error("Error while updating departures at " + airport.getAirportName() + ": " + e);
            }
        }

        log.info("All airports updated");
        airportRepository.saveAll(airports);
    }

    @PutMapping("/updateAirportArrivals")
    public void updateAirportArrivals(String icao) throws Exception {

    }
}
