package com.flightdata_handler.service;

import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.Flight;
import com.flightdata_handler.model.FlightData;
import com.flightdata_handler.model.InAirport;
import com.flightdata_handler.repository.AirportRepository;
import com.flightdata_handler.repository.FlightRepository;
import com.flightdata_handler.repository.InAirportRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PackageFlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final InAirportRepository inAirportRepository;

    @Autowired
    public PackageFlightService(FlightRepository flightRepository, AirportRepository airportRepository, InAirportRepository inAirportRepository){
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.inAirportRepository = inAirportRepository;
    }

    public void deleteEverything(){
        log.info("Deleting all states\n");
        inAirportRepository.deleteAll();
        flightRepository.deleteAll();
        airportRepository.deleteAll();
    }

    /*public FlightData getFlightByCallsign(String callsign){
        log.info("Fetching all flights from the database");

        // get all Flight objects
        List<Flight> flights = flightRepository.findAll();

        if(flights.isEmpty()){
            log.error("No flights found in the database");
        } else {
            log.info("Number of flights found: " + flights.size());
        }

        return null;
    }*/

    public FlightData getFlightByCallsign(String callsign) {
        log.info("Getting flight by callsign: " + callsign);

        // get Flight object
        //Optional<Flight> toPackage = flightRepository.getReferenceById(callsign);

        Flight flightToUse = flightRepository.getReferenceById(callsign);

        /*if(toPackage.isEmpty()){
            log.error("Flight with callsign: " + callsign + " not found");
            return null;
        }

        //Flight flightToUse = toPackage.get();*/

        log.info("Flight found!: " + flightToUse.getCallsign() + ". Getting arrival and departure now: ");

        // get InAirport objects
        List<String> callsigns = Collections.singletonList(callsign);
        List<InAirport> arrivalAndDeparture = inAirportRepository.findAllById(callsigns);

        InAirport arrival = null;
        InAirport departure = null;

        if(arrivalAndDeparture.size() == 2){
            for(InAirport inAirport : arrivalAndDeparture){
                if(inAirport.getType().equals("arrival")){
                    arrival = inAirport;
                } else {
                    departure = inAirport;
                }
            }
        } else {
            log.error("Error while getting arrival and departure");
            return null;
        }

        if(arrival == null || departure == null){
            log.error("Arrival or Departure is null");
            return null;
        }

        log.info("Arrival and Departure found: " + arrival.getCallsign() + " and " + departure.getCallsign() + ". Getting Airports now. Arrival airport: " + arrival.getEstArrivalAirport() + " Departure airport: " + departure.getEstDepartureAirport());

        // Now get Airports
        String arrivalIata = arrival.getEstArrivalAirport();
        String departureIata = departure.getEstDepartureAirport();

        if(arrivalIata == null || departureIata == null){
            log.error("Arrival or Departure Iata is null");
            return null;
        }

        Optional<Airport> arrivalArpt = airportRepository.findById(arrivalIata);
        Optional<Airport> departureArpt = airportRepository.findById(departureIata);

        if(arrivalArpt.isEmpty() || departureArpt.isEmpty()){
            log.error("Arrival or Departure Airport is null");
            return null;
        }

        Airport arrivalAirport = arrivalArpt.get();
        Airport departureAirport = departureArpt.get();

        log.info("Arrival and Departure Airports found: " + arrivalAirport.getIata() + " and " + departureAirport.getIata() + ". Creating final object now");

        // Create final object and package
        FlightData response = new FlightData(flightToUse, arrivalAirport, departureAirport, arrival, departure);

        log.info("Final object created: " + response.getFlightCode() + ". Returning now");
        return response;
    }
}
