package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class FlightData {
    // Full Flight Object = Flight + Airports + Arrivals
    @JsonProperty("flightCode")         // - From Flight
    private String flightCode;

    @JsonProperty("airline")            // - From Flight
    private String airline;

    @JsonProperty("flightDepAirportCode")   // estDepartureAirport.iata in Arrivals
    private String flightDepAirportCode;

    @JsonProperty("flightArrAirportCode")   // estArrivalAirport.iata in Arrivals
    private String flightArrAirportCode;

    @JsonProperty("flightDepTime")          // firstSeen in Arrivals
    private Timestamp flightDepTime;

    @JsonProperty("flightArrTime")          // lastSeen in Arrivals
    private Timestamp flightArrTime;

    @JsonProperty("flightDepAirport")       // estDepartureAirport in Arrivals
    private String flightDepAirport;

    @JsonProperty("flightArrAirport")       // estArrivalAirport in Arrivals
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

    public FlightData(String flightCallsign, String flightAirline, String arrivalIata, String departureIata, String arrivalAirportName, String departureAirportName, Timestamp depTime, Timestamp arrTime, float flightLatitude, float flightLongitude, String depLatitude, String depLongitude, String arrLatitude, String arrLongitude) {
        this.flightCode = flightCallsign;
        this.airline = flightAirline;

        this.flightDepAirportCode = departureIata;
        this.flightArrAirportCode = arrivalIata;

        this.flightDepTime = depTime;
        this.flightArrTime = arrTime;

        this.flightDepAirport = departureAirportName;
        this.flightArrAirport = arrivalAirportName;

        //this.flightDepExpDelay = ;

        this.flightDepLatitude = depLatitude;
        this.flightDepLongitude = depLongitude;

        //this.flightArrExpDelay = ;

        this.flightArrLatitude = arrLatitude;
        this.flightArrLongitude = arrLongitude;

        this.flightLatitude = flightLatitude;
        this.flightLongitude = flightLongitude;
    }
}
