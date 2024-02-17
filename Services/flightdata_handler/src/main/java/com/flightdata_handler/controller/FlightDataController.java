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
    @Autowired
    private ReadAllStates readAllStates;

    @Autowired
    public FlightDataController(){
        log.info("FlightDataController Started");
    }

    @GetMapping("/")
    public String home(){
        return "This is FlightDataController";
    }

    @PutMapping("/updateAllStates")
    public void updateAllStates(){
        try {
            if(readAllStates == null){
                log.info("ReadAllStates is null");
            }

            String resultFromPython = readAllStates.readPython();

            if(resultFromPython == null){
                log.info("Result from python is null");
                return;
            }

            if(resultFromPython.equals("Done")){
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
