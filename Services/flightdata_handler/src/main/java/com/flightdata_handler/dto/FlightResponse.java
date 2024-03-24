/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_handler.dto;

import com.flightdata_handler.model.Flight;
import com.flightdata_handler.model.enums.FlightStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class FlightResponse extends Flight {

    private String icao24;
    private String callsign;
    private String airline;
    private String origin_country;
    private Timestamp time_position;
    private Timestamp last_contact;
    private float longitude;
    private float latitude;
    private float baro_altitude;
    private boolean on_ground;
    private float velocity;
    private float true_track;
    private float vertical_rate;
    private String sensors;
    private float geo_altitude;
    private String squawk;
    private boolean spi;
    private int position_source;
    private int category;
    private Timestamp ETA;  // ETA Logic set on creation and before saving to DB
    private boolean isArrivalDelayed;
    private Timestamp ETD;
    private boolean isDepartureDelayed;
    private FlightStatusEnum status;
    private Timestamp lastTimeUpdated;

    public FlightResponse() {
    }

    public FlightResponse (Flight flight) {
        this.icao24 = flight.getIcao24();
        this.callsign = flight.getCallsign();
        this.airline = flight.getAirline();
        this.origin_country = flight.getOrigin_country();
        this.time_position = flight.getTime_position();
        this.last_contact = flight.getLast_contact();
        this.longitude = flight.getLongitude();
        this.latitude = flight.getLatitude();
        this.baro_altitude = flight.getBaro_altitude();
        this.on_ground = flight.isOn_ground();
        this.velocity = flight.getVelocity();
        this.true_track = flight.getTrue_track();
        this.vertical_rate = flight.getVertical_rate();
        this.sensors = flight.getSensors();
        this.geo_altitude = flight.getGeo_altitude();
        this.squawk = flight.getSquawk();
        this.spi = flight.isSpi();
        this.position_source = flight.getPosition_source();
        this.category = flight.getCategory();
        this.ETA = flight.getETA();
        this.isArrivalDelayed = flight.isArrivalDelayed();
        this.ETD = flight.getETD();
        this.isDepartureDelayed = flight.isDepartureDelayed();
        this.status = flight.getStatus();
        this.lastTimeUpdated = flight.getLastTimeUpdated();
    }
}
