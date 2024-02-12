package com.flightdata_handler.controller;

import com.flightdata_handler.model.*;
import com.flightdata_handler.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("api/flightController")
@RequiredArgsConstructor
public class FlightDataController {

    @Autowired
    private final FetchFlights functionToUse;

    @GetMapping("/")
    public String home(){
        return "This is FlightDataController";
    }

    @PostMapping("/updateAllStates")
    public void updateAllStates(){
        try {
            functionToUse.readPython();
        } catch (IOException ioException){
            log.info("There was a problem reading the python file, Exception:" + ioException);
        }
    }
}
