package com.flightdata_handler.controller;

import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.Flight;
import com.flightdata_handler.service.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/flightController")
@Slf4j
public class FlightDataController {
    private final ReadAirportArrivals ReadAirportArrivals = new ReadAirportArrivals();
    private final ReadAirportDepartures ReadAirportDepartures = new ReadAirportDepartures();
    private final ReadAllStates ReadAllStates = new ReadAllStates();

    @Autowired
    public FlightDataController(){
        log.info("FlightDataController Started");
    }

    @GetMapping("/")
    public String home(){
        return "This is FlightDataController";
    }

    @PostMapping("/updateAllStates")
    public void updateAllStates(){
        try {
            if(ReadAllStates.readPython().equals("Done")){
                log.info("All states updated");
            } else {
                throw new IOException("There was a problem updating all states");
            }
        } catch (IOException ioException){
            log.info("There was a problem reading the python file, Exception:" + ioException);
        }
    }

    @GetMapping("/getAllStates")
    public List<Flight> getAllStates(){
        // return states from DB
        return null;
    }

    @PostMapping("/updateAirport")
    public void updateAirport(String airportCode){
        /*
        * Set airport code, retrieve from DB and update airport info
        * */
    }

    @GetMapping("/getAirport")
    public Airport getAirport(String airportCode){
        /*
        * Get airport info from DB
        * */
        return null;
    }
}
