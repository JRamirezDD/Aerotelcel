package com.flightdata_handler.model;

import com.flightdata_handler.service.ReadAirportArrivals;
import com.flightdata_handler.service.ReadAirportDepartures;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "mainairportdb")
public class Airport {

    // Attributes
    @Id
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
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;

    // Lists of arrivals and departures
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    @JoinColumn(name = "arrival_airport")
    private List<InAirport> arrivals = new ArrayList<InAirport>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    @JoinColumn(name = "departures_airport")
    private List<InAirport> departures = new ArrayList<InAirport>();

    // Dependencies
    public synchronized void updateDepartures(){
        ReadAirportDepartures readAirportDepartures = new ReadAirportDepartures();

        log.info("Updating departures for " + this.airportName + " airport\n");

        readAirportDepartures.setAirportCode(this.ICAO_code);
      
        try{
            readAirportDepartures.doSearch();

            // Data is valid and added to the airport's list
            if(readAirportDepartures.valid){
                this.departures.addAll(readAirportDepartures.getDepartures());
                log.info("Departures for " + this.airportName + " airport updated successfully\n");

            } else {
                log.error("Error updating departures for " + this.airportName + " airport\n");
            }

        } catch (Exception e){
            log.error("Error updating departures for " + this.airportName + " airport. Exception: " + e + "\n");
        }
    }

    // Constructor
    public synchronized void updateArrivals(){
        ReadAirportArrivals readAirportArrivals = new ReadAirportArrivals();

        log.info("Updating arrivals for " + this.airportName + " airport\n");

        readAirportArrivals.setAirportCode(this.ICAO_code);

        try{
            readAirportArrivals.doSearch();

            // Data is valid and added to the airport's list
            if(readAirportArrivals.valid){
                this.arrivals.addAll(readAirportArrivals.getArrivals());
                log.info("Arrivals for " + this.airportName + " airport updated successfully\n");

            } else {
                log.error("Error updating arrivals for " + this.airportName + " airport\n");
            }

        } catch (Exception e){
            log.error("Error updating arrivals for " + this.airportName + " airport. Exception: " + e + "\n");
        }
    }

    public Airport getAirport(){
        return this;
    }
}
