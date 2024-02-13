package com.flightdata_handler.model;

import com.flightdata_handler.service.*;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.IOException;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Airport {
    // Attributes
    @Id
    private int AirportId;
    private String IATAcode;
    private String ICAOcode;        // to send to python script
    private String airportName;
    private String city;
    private String country;

    // Lists of arrivals and departures
    private List<Flight> arrivals;
    private List<Flight> departures;

    // Dependencies
    private ReadAirportArrivals readAirportArrivals;
    private ReadAirportDepartures readAirportDepartures;

    // Constructor
    public Airport(String IATA, String ICAO, String airportName, String city, String country) {
        this.IATAcode = IATA;
        this.ICAOcode = ICAO;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
    }

    public void updateArrivalsAndDepartures(){
        try {
            this.arrivals = readAirportArrivals.readPython(ICAOcode);
            this.departures = readAirportDepartures.readPython(ICAOcode);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Airport getAirport(){
        return this;
    }
}
