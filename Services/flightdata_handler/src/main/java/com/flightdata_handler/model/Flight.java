package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightdata_handler.model.enums.FlightStatusEnum;
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
    @Column(name = "icao24")
    @JsonProperty("icao24")
    private String icao24;

    @Id
    @Column(name = "callsign")
    @JsonProperty("callsign")
    private String callsign = "No callsign " + icao24;

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

        // Estimated time of Arrival
    @Column(name = "eta")
    @JsonProperty("eta")
    private Timestamp ETA;  // ETA Logic set on creation and before saving to DB
    public boolean etaDelayed(Flight other) {
        // If (self.ETA < other.ETA)
        // Flight got delayed
        // return true
        return false;
    }

    @Column(name = "isArrivalDelayed")
    @JsonProperty("isArrivalDelayed")
    private boolean isArrivalDelayed;

    @Column(name = "etd")
    @JsonProperty("etd")
    private Timestamp ETD;  // ETD Logic set on creation and before saving to DB
    public boolean etdDelayed(Flight other) {
        // If (self.ETD < other.ETD)
        // Flight got delayed
        // return true
        return false;
    }

    @Column(name = "isDepartureDelayed")
    @JsonProperty("isDepartureDelayed")
    private boolean isDepartureDelayed;


        // Status
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, updatable = false)
    @JsonProperty("status")
    private FlightStatusEnum status;    // Set on creation of object. Creator is responsible for setting value.

    @Column(name = "lastTimeUpdated")
    @JsonProperty("lastTimeUpdated")
    private Timestamp lastTimeUpdated;   // Set on creation of object

    public FlightStatusEnum statusLogic(Flight old) {
        // Flying -> On_Ground = Landed
        if (this.status.equals(FlightStatusEnum.On_Ground) && old.status.equals(FlightStatusEnum.Flying)) {
            return FlightStatusEnum.Landed;
        }
        // Landed -> Landed = On_Ground
        else if (this.status.equals(FlightStatusEnum.Landed) && old.status.equals(FlightStatusEnum.Landed)) {
            return FlightStatusEnum.On_Ground;
        }
        // On_Ground -> Flying = Taken_Off
        else if (this.status.equals(FlightStatusEnum.Flying) && old.status.equals(FlightStatusEnum.On_Ground)) {
            return FlightStatusEnum.Taken_Off;
        }
        // Taken_Off -> Taken_Off = Flying
        else if (this.status.equals(FlightStatusEnum.Taken_Off) && old.status.equals(FlightStatusEnum.Taken_Off)) {
            return FlightStatusEnum.Flying;
        }
        // Taken_Off -> Landed = On_Ground (Intermediary 'Flying' State Skipped)
        else if (this.status.equals(FlightStatusEnum.Landed) && old.status.equals(FlightStatusEnum.Taken_Off)) {
            return FlightStatusEnum.Flying;
        }
        // Landed -> Taken_Off = Flying (Intermediary 'On_Ground' State Skipped)
        else if (this.status.equals(FlightStatusEnum.Taken_Off) && old.status.equals(FlightStatusEnum.Landed)) {
            return FlightStatusEnum.On_Ground;
        }
        // No status change
        else {
            return this.status;
        }
    }
}