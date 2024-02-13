package com.flightdata_handler.model;

import com.flightdata_handler.service.*;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import java.io.IOException;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "airports")
public class Airport {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "IATA_code")
    private String IATA_code;
    @Column(name = "ICAO_code")
    private String ICAO_code;        // to send to python script
    @Column(name = "airport_name")
    private String airportName;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;

    // Lists of arrivals and departures
    @Column(name = "arrivals")
    private List<Flight> arrivals;
    @Column(name = "departures")
    private List<Flight> departures;

    // Dependencies
    private ReadAirportArrivals readAirportArrivals;
    private ReadAirportDepartures readAirportDepartures;

    // Constructor
    public Airport(String IATA, String ICAO, String airportName, String city, String country) {
        this.IATA_code = IATA;
        this.ICAO_code = ICAO;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
    }

    public void updateArrivalsAndDepartures(){
        try {
            this.arrivals = readAirportArrivals.readPython(ICAO_code);
            this.departures = readAirportDepartures.readPython(ICAO_code);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Airport getAirport(){
        return this;
    }
}
