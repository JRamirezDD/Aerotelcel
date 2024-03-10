package com.flightdata_handler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightdata_handler.model.FlightData;

import java.sql.Timestamp;

public class FlightDataResponse {
    // Full Flight Object = Flight + Airports + InAirport
    @JsonProperty("flightCode")
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

    public FlightDataResponse(FlightData toDeliver){
        this.flightCode = toDeliver.getFlightCode();
        this.airline = toDeliver.getAirline();
        this.flightDepAirportCode = toDeliver.getFlightDepAirportCode();
        this.flightArrAirportCode = toDeliver.getFlightArrAirportCode();
        this.flightDepTime = toDeliver.getFlightDepTime();
        this.flightArrTime = toDeliver.getFlightArrTime();
        this.flightDepAirport = toDeliver.getFlightDepAirport();
        this.flightArrAirport = toDeliver.getFlightArrAirport();
        this.flightDepExpDelay = toDeliver.getFlightDepExpDelay();
        this.flightDepLatitude = toDeliver.getFlightDepLatitude();
        this.flightDepLongitude = toDeliver.getFlightDepLongitude();
        this.flightArrExpDelay = toDeliver.getFlightArrExpDelay();
        this.flightArrLatitude = toDeliver.getFlightArrLatitude();
        this.flightArrLongitude = toDeliver.getFlightArrLongitude();
        this.flightLatitude = toDeliver.getFlightLatitude();
        this.flightLongitude = toDeliver.getFlightLongitude();

    }
}
