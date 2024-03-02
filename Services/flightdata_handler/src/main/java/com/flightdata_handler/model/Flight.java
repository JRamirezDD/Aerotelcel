package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.sql.Timestamp;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "flights")
public class Flight {

    // Database properties
    @Id
    @Column(name = "icao24")
    @JsonProperty("icao24")
    private String icao24;
    @Column(name = "callsign")
    @JsonProperty("callsign")
    private String callsign;

    @Column(name = "airline")
    @JsonProperty("airline")
    private String airline;

    @Column(name = "origin_country")
    @JsonProperty("origin_country")
    private String origin_country;
    @Column(name = "time_position")
    @JsonProperty("time_position")
    private Timestamp time_position;
    @Column(name = "last_contact")
    @JsonProperty("last_contact")
    private Timestamp last_contact;
    @Column(name = "longitude")
    @JsonProperty("longitude")
    private float longitude;
    @Column(name = "latitude")
    @JsonProperty("latitude")
    private float latitude;
    @Column(name = "baro_altitude")
    @JsonProperty("baro_altitude")
    private float baro_altitude;
    @Column(name = "on_ground")
    @JsonProperty("on_ground")
    private boolean on_ground;
    @Column(name = "velocity")
    @JsonProperty("velocity")
    private float velocity;
    @Column(name = "true_track")
    @JsonProperty("true_track")
    private float true_track;
    @Column(name = "vertical_rate")
    @JsonProperty("vertical_rate")
    private float vertical_rate;
    @Column(name = "sensors")
    @JsonProperty("sensors")
    private String sensors;
    @Column(name = "geo_altitude")
    @JsonProperty("geo_altitude")
    private float geo_altitude;
    @Column(name = "squawk")
    @JsonProperty("squawk")
    private String squawk;
    @Column(name = "spi")
    @JsonProperty("spi")
    private boolean spi;
    @Column(name = "position_source")
    @JsonProperty("position_source")
    private int position_source;
    @Column(name = "category")
    @JsonProperty("category")
    private int category;

    public Flight getFlightObject() {
        return this;
    }
}