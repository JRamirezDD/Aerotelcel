package com.flightdata_handler.model;

import com.flightdata_handler.service.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import jakarta.persistence.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "airports")
@Slf4j
public class Airport {
    // Attributes

    @Column(name = "IATA_code")
    private String IATA_code;
    @Id
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
    @OneToMany
    private List<Flight> arrivals;
    @Column(name = "departures")
    @OneToMany
    private List<Flight> departures;

    // Dependencies
//    private ReadAirportArrivals readAirportArrivals;
//    private ReadAirportDepartures readAirportDepartures;

    // Constructor
    public Airport(String IATA, String ICAO, String airportName, String city, String country) {
        this.IATA_code = IATA;
        this.ICAO_code = ICAO;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
    }


    // Service layer should be in charge of this type of operations, not the model itself.
//    public void updateArrivalsAndDepartures(){
//        try {
//            log.info("Updating arrivals and departures for " + this.ICAO_code);
//
//            readAirportArrivals.setAirportCode(this.ICAO_code);
//            readAirportDepartures.setAirportCode(this.ICAO_code);
//
//            if(readAirportArrivals.readPython().equals("Done")){
//                this.arrivals = readAirportArrivals.getArrivals();
//            } else {
//                throw new IOException("Error reading arrivals from python");
//            }
//
//            if(readAirportDepartures.readPython().equals("Done")){
//                this.departures = readAirportDepartures.getDepartures();
//            } else {
//                throw new IOException("Error reading departures from python");
//            }
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    public Airport getAirport(){
        return this;
    }
}
