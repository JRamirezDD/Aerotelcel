package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.persistence.*;

// Child class to be used for flights that are in the airport whether it's a departure or arrival
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class InAirport {
    @JsonProperty("icao24")
    private String icao24;

    @JsonProperty("firstSeen")
    private int firstSeen;

    @JsonProperty("estDepartureAirport")
    private String estDepartureAirport;

    @JsonProperty("lastSeen")
    private int lastSeen;

    @JsonProperty("estArrivalAirport")
    private String estArrivalAirport;

    @Id
    @JsonProperty("callsign")
    private String callsign;

    // Departure Airport data
    @JsonProperty("estDepartureAirportHorizDistance")
    private int estDepartureAirportHorizDistance;

    @JsonProperty("estDepartureAirportVertDistance")
    private int estDepartureAirportVertDistance;

    // Arrival Airport data
    @JsonProperty("estArrivalAirportHorizDistance")
    private int estArrivalAirportHorizDistance;

    @JsonProperty("estArrivalAirportVertDistance")
    private int estArrivalAirportVertDistance;

    // Departure & Arrival Airport candidates
    @JsonProperty("departureAirportCandidatesCount")
    private String departureAirportCandidatesCount;

    @JsonProperty("arrivalAirportCandidatesCount")
    private int arrivalAirportCandidatesCount;
}