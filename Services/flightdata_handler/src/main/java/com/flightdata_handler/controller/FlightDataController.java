package com.flightdata_handler.controller;

import com.flightdata_handler.model.Flight;
import com.flightdata_handler.service.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flightController")
@Slf4j
public class FlightDataController {

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
        log.info("Updating all states, we're at flightDataController\n");

        try {
            log.info("Were at the try...\n");
            if (readAllStates == null) {
                log.error("ReadAllStates is null");
                throw new NullPointerException("ReadAllStates is null");
            }

            log.info("First if passed, checking method\n");

            // !!! QUE VERGAS !!!
            boolean resultFromPython = readAllStates.readPython();

            log.info("Just ran readPython: " + resultFromPython + "\n");

            if (!resultFromPython) {
                log.error("Result from python is null");
                throw new Exception("Result from python is null");
            }

            log.info("Checked second if...\n");

            log.info("All states updated");

        } catch (Exception e){
            log.info("There was a problem reading the python file, Exception:" + e);
        }
    }

    @PutMapping("/updateSpecificFlight")
    public void updateSpecificFlight(String flightNumber){
        /*
        * Set flight number, retrieve from DB and update flight info
        */
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
