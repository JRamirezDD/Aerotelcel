package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class FlightData {
    // Full Flight Object = Flight + Airports + InAirport
    @JsonProperty("flightCode")         // - From Flight
    private String flightCode;

    @JsonProperty("airline")            // - From Flight
    private String airline;

    @JsonProperty("flightDepAirportCode")   // estDepartureAirport.iata in InAirport
    private String flightDepAirportCode;

    @JsonProperty("flightArrAirportCode")   // estArrivalAirport.iata in InAirport
    private String flightArrAirportCode;

    @JsonProperty("flightDepTime")          // firstSeen in InAirport
    private Timestamp flightDepTime;

    @JsonProperty("flightArrTime")          // lastSeen in InAirport
    private Timestamp flightArrTime;

    @JsonProperty("flightDepAirport")       // estDepartureAirport in InAirport
    private String flightDepAirport;

    @JsonProperty("flightArrAirport")       // estArrivalAirport in InAirport
    private String flightArrAirport;

    @JsonProperty("flightDepExpDelay")
    private int flightDepExpDelay;

    @JsonProperty("flightDepLatitude")      // - from Airport
    private String flightDepLatitude;

    @JsonProperty("flightDepLongitude")     // - from Airport
    private String flightDepLongitude;

    @JsonProperty("flightArrExpDelay")
    private int flightArrExpDelay;

    @JsonProperty("flightArrLatitude")      // - from Airport
    private String flightArrLatitude;

    @JsonProperty("flightArrLongitude")     // - from Airport
    private String flightArrLongitude;

    @JsonProperty("flightLatitude")         // - from Flight
    private float flightLatitude;

    @JsonProperty("flightLongitude")        // - from Flight
    private float flightLongitude;

    public FlightData() {
    }

    public FlightData(Flight flight, Airport arrivalAirport, Airport departureAirport, InAirport arrival, InAirport departure){
        this.flightCode = flight.getCallsign();
        this.airline = flight.getAirline();

        this.flightDepAirportCode = departureAirport.getIata();
        this.flightArrAirportCode = arrivalAirport.getIata();

        this.flightDepTime = departure.getFirstSeen();
        this.flightArrTime = arrival.getLastSeen();

        this.flightDepAirport = departureAirport.getAirportName();
        this.flightArrAirport = arrivalAirport.getAirportName();

        //this.flightDepExpDelay = ;

        this.flightDepLatitude = departureAirport.getLatitude();
        this.flightDepLongitude = departureAirport.getLongitude();

        //this.flightArrExpDelay = ;

        this.flightArrLatitude = arrivalAirport.getLatitude();
        this.flightArrLongitude = arrivalAirport.getLongitude();

        this.flightLatitude = flight.getLatitude();
        this.flightLongitude = flight.getLongitude();
    }
}
