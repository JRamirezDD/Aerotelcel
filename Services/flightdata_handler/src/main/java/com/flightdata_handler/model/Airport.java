package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JsonProperty("arrivals")
    //@Builder.Default
    @JsonManagedReference
    private List<InAirport> arrivals = new ArrayList<InAirport>();

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JsonProperty("departures")
    //@Builder.Default
    @JsonManagedReference
    private List<InAirport> departures = new ArrayList<InAirport>();

    public synchronized void setNewArrivals(List<InAirport> newArrivals){
        this.arrivals.clear();
        this.arrivals.addAll(newArrivals);
    }

    public synchronized void setNewDepartures(List<InAirport> newDepartures){
        this.departures.clear();
        this.departures.addAll(newDepartures);
    }

    public Airport getAirport(){
        return this;
    }
}
