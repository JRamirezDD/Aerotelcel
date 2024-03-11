package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Column(name = "iata")
    @JsonProperty("iata")
    private String iata;
    @Column(name = "icao")
    @JsonProperty("icao")
    private String icao;        // to send to python script
    @Column(name = "airport_name")
    @JsonProperty("airport_name")
    private String airportName;
    @Column(name = "city")
    @JsonProperty("city")
    private String city;
    @Column(name = "country")
    @JsonProperty("country")
    private String country;
    @Column(name = "latitude")
    @JsonProperty("latitude")
    private String latitude;
    @Column(name = "longitude")
    @JsonProperty("longitude")
    private String longitude;

    // Lists of arrivals and departures
    @OneToMany(mappedBy = "airport", orphanRemoval = true, fetch = FetchType.EAGER)
    //@JsonProperty("arrivals")
    @Builder.Default
    @JsonManagedReference
    private List<Arrivals> arrivals = new ArrayList<Arrivals>();

    @OneToMany(mappedBy = "airport", orphanRemoval = true, fetch = FetchType.EAGER)
    //@JsonProperty("departures")
    @Builder.Default
    @JsonManagedReference
    private List<Departures> departures = new ArrayList<Departures>();

    public synchronized void setNewArrivals(List<Arrivals> newArrivals){
        log.info("Setting new arrivals");
        if(!this.arrivals.equals(newArrivals)){
            this.arrivals.clear();
            this.arrivals.addAll(newArrivals);
        }
        log.info("Arrivals set");
    }

    public synchronized void setNewDepartures(List<Departures> newDepartures){
        log.info("Setting new departures");
        if(!this.departures.equals(newDepartures)){
            this.departures.clear();
            this.departures.addAll(newDepartures);
        }
        log.info("Departures set");
    }

    public Airport getAirport(){
        return this;
    }
}
