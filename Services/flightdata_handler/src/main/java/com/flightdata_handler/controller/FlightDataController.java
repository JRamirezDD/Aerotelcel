package com.flightdata_handler.controller;

//import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.Flight;
import com.flightdata_handler.service.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/flightController")
@Slf4j
public class FlightDataController {
    //private final ReadAirportArrivals ReadAirportArrivals = new ReadAirportArrivals();
    //private final ReadAirportDepartures ReadAirportDepartures = new ReadAirportDepartures();

    private final ReadAllStates readAllStates;

    @Autowired
    public FlightDataController(ReadAllStates readAllStates){
        log.info("FlightDataController Started");
        this.readAllStates = readAllStates;
    }

    @GetMapping("/")
    public String home(){
        return "This is FlightDataController";
    }

    @PutMapping("/updateAllStates")
    public void updateAllStates() throws Exception {

        try {
            if (readAllStates == null) {
                log.error("ReadAllStates is null");
                throw new NullPointerException("ReadAllStates is null");
            }

            boolean resultFromPython = readAllStates.readPython();

            if (!resultFromPython) {
                log.error("Result from python is null");
                throw new Exception("Result from python is null");
            }

            log.info("All states updated");

        } catch (Exception e){
            log.info("There was a problem reading the python file, Exception:" + e);
        }
    }

    @GetMapping("/getAllStates")
    public List<Flight> getAllStates(){
        // return states from DB
        return null;
    }



    /*@PutMapping("/updateAirport")
    public void updateAirport(String airportCode){
        /*
        * Set airport code, retrieve from DB and update airport info

    }*/

    /*@GetMapping("/getAirport")
    public Airport getAirport(String airportCode){
        /*
        * Get airport info from DB
        *
        return null;
    }*/
}
