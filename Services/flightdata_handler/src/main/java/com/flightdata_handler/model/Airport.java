package com.flightdata_handler.model;

import com.flightdata_handler.service.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.List;

@Data
@Slf4j
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long airportId;

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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "arrival_airport_id")
    private List<Flight> arrivals;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "departures_airport_id")
    private List<Flight> departures;

    // Dependencies

    //private ReadAirportArrivals readAirportArrivals;
    //private ReadAirportDepartures readAirportDepartures;

    // Constructor
    public Airport(String IATA, String ICAO, String airportName, String city, String country) {
        this.IATA_code = IATA;
        this.ICAO_code = ICAO;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
    }

    public void updateArrivalsAndDepartures(){
        /*try {
            log.info("Updating arrivals and departures for " + this.ICAO_code);

            readAirportArrivals.setAirportCode(this.ICAO_code);
            readAirportDepartures.setAirportCode(this.ICAO_code);

            if(readAirportArrivals.readPython().equals("Done")){
                this.arrivals = readAirportArrivals.getArrivals();
            } else {
                throw new IOException("Error reading arrivals from python");
            }

            if(readAirportDepartures.readPython().equals("Done")){
                this.departures = readAirportDepartures.getDepartures();
            } else {
                throw new IOException("Error reading departures from python");
            }

        } catch (IOException e){
            e.printStackTrace();
        }*/
    }

    public Airport getAirport(){
        return this;
    }
}
