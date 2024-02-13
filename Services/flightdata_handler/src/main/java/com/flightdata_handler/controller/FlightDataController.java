package com.flightdata_handler.controller;

import com.flightdata_handler.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/flightController")
@Slf4j
public class FlightDataController {
    private final FetchFlights fetchFlights;

    @Autowired
    public FlightDataController(@Qualifier("readAllStates") FetchFlights fetchFlights){
        this.fetchFlights = fetchFlights;
    }


    @GetMapping("/")
    public String home(){
        return "This is FlightDataController";
    }

    @PostMapping("/updateAllStates")
    public void updateAllStates(){
        try {
            fetchFlights.readPython();
        } catch (IOException ioException){
            log.info("There was a problem reading the python file, Exception:" + ioException);
        }
    }
}
