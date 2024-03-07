package com.flightdata_handler.dto;

import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.InAirport;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AirportResponse {
    // Attributes
    private String iata;
    private String icao;        // to send to python script
    private String airportName;
    private String city;
    private String country;
    private String type;
    private String latitude;
    private String longitude;

    // Lists of arrivals and departures
    private List<InAirport> arrivals = new ArrayList<InAirport>();
    private List<InAirport> departures = new ArrayList<InAirport>();

    public AirportResponse(Airport airport){
        this.iata = airport.getIata();
        this.icao = airport.getIcao();
        this.airportName = airport.getAirportName();
        this.city = airport.getCity();
        this.country = airport.getCountry();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();

        this.arrivals = airport.getArrivals().stream()
                .filter(inAirport -> "arrival".equals(inAirport.getType()))
                .collect(Collectors.toList());
        this.departures = airport.getDepartures().stream()
                .filter(inAirport -> "departure".equals(inAirport.getType()))
                .collect(Collectors.toList());
    }
}
