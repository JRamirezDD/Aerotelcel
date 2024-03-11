package com.flightdata_handler.service;

import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.Arrivals;
import com.flightdata_handler.model.Departures;
import com.flightdata_handler.model.Flight;
import com.flightdata_handler.model.FlightData;
import com.flightdata_handler.repository.AirportRepository;
import com.flightdata_handler.repository.ArrivalsRepository;
import com.flightdata_handler.repository.DeparturesRepository;
import com.flightdata_handler.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PackageFlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final ArrivalsRepository arrivalsRepository;
    private final DeparturesRepository departuresRepository;

    @Autowired
    public PackageFlightService(FlightRepository flightRepository, AirportRepository airportRepository, ArrivalsRepository arrivalsRepository, DeparturesRepository departuresRepository){
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.arrivalsRepository = arrivalsRepository;
        this.departuresRepository = departuresRepository;
    }

    public void deleteEverything(){
        log.info("Deleting all states\n");
        arrivalsRepository.deleteAll();
        departuresRepository.deleteAll();
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
        // Optional<Flight> toPackage = flightRepository.findById(callsign);

        Flight flightToUse = flightRepository.findByFlightCode(callsign);

        if(flightToUse == null){
            log.error("Flight with callsign: " + callsign + " not found");
            return null;
        }

        /*if(toPackage.isEmpty()){
            log.error("Flight with callsign: " + callsign + " not found");
            return null;
        }

        Flight flightToUse = toPackage.get();*/

        log.info("Flight found!: " + flightToUse.getCallsign() + ". Getting arrival and departure now: ");

        String flightCallsign = flightToUse.getCallsign();
        String flightAirline = flightToUse.getAirline();
        float flightLatitude = flightToUse.getLatitude();
        float flightLongitude = flightToUse.getLongitude();

        // get Arrival and Departure objects
        Arrivals arrival = arrivalsRepository.getReferenceById(flightCallsign);
        Departures departure = departuresRepository.getReferenceById(flightCallsign);

        if(arrival == null || departure == null){
            log.error("Arrival or Departure is null");
            return null;
        }

        // Now get Airports and their features
        // Arrival
        String arrivalIcao = arrival.getEstArrivalAirport();
        Timestamp arrTime = arrival.getLastSeen();

        // Departure
        String departureIcao = departure.getEstDepartureAirport();
        Timestamp depTime = departure.getFirstSeen();

        log.info("Arrival and Departure found: " + arrival.getCallsign() + " and " + departure.getCallsign() + ". Getting Airports now. Arrival airport: " + arrivalIcao + " Departure airport: " + departureIcao);

        if(arrivalIcao == null || departureIcao == null){
            log.error("Arrival or Departure Iata is null");
            return null;
        }

        Optional<Airport> gettingArrival = airportRepository.findByIcao(arrivalIcao);
        Optional<Airport> gettingDeparture = airportRepository.findByIcao(departureIcao);

        if(gettingArrival.isEmpty() || gettingDeparture.isEmpty()){
            log.error("Arrival or Departure Airport is null");
            return null;
        }

        Airport arrivalAirport = gettingArrival.get();
        Airport departureAirport = gettingDeparture.get();

        String arrivalAirportName = arrivalAirport.getAirportName();
        String arrivalIata = arrivalAirport.getIata();
        String arrLatitude = arrivalAirport.getLatitude();
        String arrLongitude = arrivalAirport.getLongitude();

        String departureAirportName = departureAirport.getAirportName();
        String departureIata = departureAirport.getIata();
        String depLatitude = departureAirport.getLatitude();
        String depLongitude = departureAirport.getLongitude();

        if(arrivalAirport == null || departureAirport == null){
            log.error("Arrival or Departure Airport is null");
            return null;
        }

        log.info("Arrival and Departure Airports found: " + arrivalAirport.getIata() + " and " + departureAirport.getIata() + ". Creating final object now...");


        // Create final object and package
        FlightData response = new FlightData(flightCallsign, flightAirline, arrivalIata, departureIata, arrivalAirportName, departureAirportName, depTime, arrTime, flightLatitude, flightLongitude, depLatitude, depLongitude, arrLatitude, arrLongitude);

        log.info("Final object created: " + response.getFlightCode() + ". Returning now");

        return response;
    }
}
