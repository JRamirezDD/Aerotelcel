package com.flightdata_handler.controller;

import com.flightdata_handler.dto.FlightResponse;
import com.flightdata_handler.model.Flight;
import com.flightdata_handler.service.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flightController")
@Slf4j
public class FlightDataController implements API_FlightDataController {

    private final ReadAllStates readAllStates;

    @Autowired
    public FlightDataController(ReadAllStates readAllStates){
        log.info("FlightDataController Started");
        this.readAllStates = readAllStates;
    }

    public String home(){
        return "This is FlightDataController";
    }

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

            /*log.info("Just ran readPython: " + resultFromPython + "\n");

            if (!resultFromPython) {
                log.error("Result from python is null");
                throw new Exception("Result from python is null");
            }

            log.info("Checked second if...\n");

            log.info("All states updated");*/

        } catch (Exception e){
            log.info("There was a problem reading the python file, Exception:" + e);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    public List<FlightResponse> getAllFlights(){
        return readAllStates.getFlights();
    }

    @ResponseStatus(HttpStatus.OK)
    public FlightResponse getFlightByCallsign(String callsign){
        // return flight from DB
        return new FlightResponse(readAllStates.getUniqueFlight(callsign));
    }

    @ResponseStatus(HttpStatus.OK)
    public void deleteAllStates(){
        readAllStates.cleanDB();
    }
}
