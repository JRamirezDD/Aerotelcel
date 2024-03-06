package com.flightdata_handler.controller;

import com.flightdata_handler.dto.AirportResponse;
import com.flightdata_handler.repository.AirportRepository;
import com.flightdata_handler.model.Airport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/airportDataController")
@Slf4j
public class AirportDataController implements API_AirportDataController {
    @Autowired
    private AirportRepository airportRepository;

    public String home(){
        return "This is AirportDataController";
    }

    @ResponseStatus(HttpStatus.OK)
    public void updateAllAirports() throws Exception {
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

    @ResponseStatus(HttpStatus.OK)
    public void updateSelectedAirport(String icao) throws Exception {
        Airport airport = airportRepository.getReferenceById(icao);

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

        log.info("Airport updated");
        airportRepository.save(airport);
    }

    @ResponseStatus(HttpStatus.OK)
    public List<AirportResponse> getAllAirports(){
        return turnIntoResponse(airportRepository.findAll());
    }

    public List<AirportResponse> turnIntoResponse(List<Airport> airports){
        List<AirportResponse> response = new ArrayList<>();

        for(Airport airport : airports){
            response.add(new AirportResponse(airport));
        }

        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    public AirportResponse getAirportByCode(String iata){
        return new AirportResponse(airportRepository.getReferenceById(iata));
    }

    @ResponseStatus(HttpStatus.OK)
    public void deleteAllAirports(){
        airportRepository.deleteAll();
    }
}
