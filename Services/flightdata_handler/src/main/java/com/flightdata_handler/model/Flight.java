package com.flightdata_handler.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.json.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Flight extends JSONObject {
    // Database properties
    private int id;

    // JSON object properties
    private JSONObject flightObject;

    private String icao24;
    private String callsign;
    private String origin_country;
    private Timestamp time_position;
    private Timestamp last_contact;
    private float longitude;
    private float latitude;
    private float baro_altitude;
    private float velocity;
    private float true_track;
    private float vertical_rate;
    private String sensors;
    private float geo_altitude;
    private String squawk;
    private int position_source;
    private int category;

    public Flight() throws JSONException {
    }

    public Flight(String toJSON) throws JSONException {
        super(toJSON);
    }

    public void setValue(String key, Object value){
        this.flightObject.put(key, value);
    }

    public Flight getFlightObject() {
        return this;
    }
}