package com.flightdata_handler.service;

import com.flightdata_handler.dto.AirportResponse;
import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.InAirport;
import com.flightdata_handler.repository.AirportRepository;
import com.flightdata_handler.repository.InAirportRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AirportService {
    private final AirportRepository airportRepository;
    private final InAirportRepository inAirportRepository;
    private final ReadAirportArrivals readAirportArrivals = new ReadAirportArrivals();
    private final ReadAirportDepartures readAirportDepartures = new ReadAirportDepartures();

    @Autowired
    public AirportService(AirportRepository airportRepository, InAirportRepository inAirportRepository){
        this.airportRepository = airportRepository;
        this.inAirportRepository = inAirportRepository;
    }

    public void updateAllAirports() throws Exception {
        List<Airport> airports = airportRepository.findAll();

        for(Airport airport : airports){
            log.info("Updating airport: " + airport.getAirportName() + "\n");

            try {
                updateArrivals(airport);
                updateDepartures(airport);
            } catch (Exception e){
                log.error("Error while updating arrivals/departures at " + airport.getAirportName() + ": " + e);
            }

            airportRepository.save(airport);
        }

        log.info("All airports updated");
    }

    public void updateSelectedAirport(String icao) throws Exception {
        Airport airport = airportRepository.getReferenceById(icao);

        log.info("Updating airport: " + airport.getAirportName() + "\n");

        try {
            updateArrivals(airport);
            updateDepartures(airport);
        } catch (Exception e){
            log.error("Error while updating arrivals/departures at " + airport.getAirportName() + ": " + e);
        }

        log.info("Airport updated");
        airportRepository.save(airport);
    }

    public List<AirportResponse> getAllAirports(){
        return turnIntoResponse(airportRepository.findAll());
    }

    private List<AirportResponse> turnIntoResponse(List<Airport> airports){
        List<AirportResponse> response = new ArrayList<AirportResponse>();

        for(Airport airport : airports){
            response.add(new AirportResponse(airport));
        }

        return response;
    }

    public Optional<Airport> getAirportByIata(String iata){
        return airportRepository.findById(iata);
    }

    public void cleanDB(){
        airportRepository.deleteAll();
    }

    public void updateArrivals(Airport airport) throws Exception {

        readAirportArrivals.clearInfo();

        readAirportArrivals.setAirportCode(airport.getIcao());

        try{
            readAirportArrivals.doSearch();

            // Data is valid and added to the airport's list
            if(readAirportArrivals.valid){
                List<InAirport> arrivals = readAirportArrivals.getArrivals();
                arrivals.forEach(arrival -> {
                    arrival.setAirport(airport);
                    inAirportRepository.save(arrival);
                });
                airport.setNewArrivals(arrivals);

                log.info("Arrivals for " + airport.getAirportName() + " airport updated successfully\n");

            } else {
                log.error("Error updating arrivals for " + airport.getAirportName() + " airport\n");
            }

        } catch (Exception e){
            log.error("Error updating arrivals for " + airport.getAirportName() + " airport. Exception: " + e + "\n");
        }

        airportRepository.save(airport);
    }

    public void updateDepartures(Airport airport){

        readAirportDepartures.clearInfo();

        readAirportDepartures.setAirportCode(airport.getIcao());

        try {
            readAirportDepartures.doSearch();

            // Data is valid and added to the airport's list
            if(readAirportDepartures.valid){
                List<InAirport> departures = readAirportDepartures.getDepartures();
                departures.forEach(departure -> {
                    departure.setAirport(airport);
                    inAirportRepository.save(departure);
                });
                airport.setNewDepartures(departures);
                log.info("Departures for " + airport.getAirportName() + " airport updated successfully\n");

            } else {
                log.error("Error updating departures for " + airport.getAirportName() + " airport\n");
            }

        } catch (Exception e){
            log.error("Error updating departures for " + airport.getAirportName() + " airport. Exception: " + e + "\n");
        }
    }
}
