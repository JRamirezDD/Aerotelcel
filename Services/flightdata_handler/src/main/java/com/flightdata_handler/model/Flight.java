package com.flightdata_handler.model;


import jakarta.persistence.*;
import lombok.*;


import org.json.*;
import java.sql.Timestamp;

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
    private String icao24;
    @Column(name = "callsign")
    private String callsign;
    @Column(name = "origin_country")
    private String origin_country;
    @Column(name = "time_position")
    private Timestamp time_position;
    @Column(name = "last_contact")
    private Timestamp last_contact;
    @Column(name = "longitude")
    private float longitude;
    @Column(name = "latitude")
    private float latitude;
    @Column(name = "baro_altitude")
    private float baro_altitude;
    @Column(name = "on_ground")
    private boolean on_ground;
    @Column(name = "velocity")
    private float velocity;
    @Column(name = "true_track")
    private float true_track;
    @Column(name = "vertical_rate")
    private float vertical_rate;
    @Column(name = "sensors")
    private String sensors;
    @Column(name = "geo_altitude")
    private float geo_altitude;
    @Column(name = "squawk")
    private String squawk;
    @Column(name = "spi")
    private boolean spi;
    @Column(name = "position_source")
    private int position_source;
    @Column(name = "category")
    private int category;

//    public Flight(String toJSON) throws JSONException { // Dont convert JSON to Flight through an inherited constructor.
//        super(toJSON);
//    }
//
//    public void setValue(String key, Object value){
//        this.put(key, value);
//    }
//
//    public Flight getFlightObject() {
//        return this;
//    }
}