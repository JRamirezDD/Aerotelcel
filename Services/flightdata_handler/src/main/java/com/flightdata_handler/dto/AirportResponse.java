/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_handler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightdata_handler.model.Airport;
import com.flightdata_handler.model.Arrivals;
import com.flightdata_handler.model.Departures;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AirportResponse {
    // Attributes
    @JsonProperty("iata")
    private String iata;
    @JsonProperty("icao")
    private String icao;
    @JsonProperty("airport_name")
    private String airportName;
    @JsonProperty("region_name")
    private String region_name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;

    // Lists of arrivals and departures
    private List<Arrivals> arrivals = new ArrayList<Arrivals>();
    private List<Departures> departures = new ArrayList<Departures>();

    public AirportResponse() {
    }

    public AirportResponse(Airport airport){
        this.iata = airport.getIata();
        this.icao = airport.getIcao();
        this.airportName = airport.getAirportName();
        this.region_name = airport.getRegion_name();
        this.country = airport.getCountry();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();

        this.arrivals = airport.getArrivals();
        this.departures = airport.getDepartures();
    }
}
