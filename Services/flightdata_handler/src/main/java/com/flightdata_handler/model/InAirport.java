package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

// Child class to be used for flights that are in the airport whether it's a departure or arrival
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "in_airport")
public class InAirport {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport")
    @JsonBackReference
    private Airport airport;

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

    public void updateFrom(InAirport inAirport){
        this.icao24 = inAirport.getIcao24();
        this.firstSeen = inAirport.getFirstSeen();
        this.estDepartureAirport = inAirport.getEstDepartureAirport();
        this.lastSeen = inAirport.getLastSeen();
        this.estArrivalAirport = inAirport.getEstArrivalAirport();
        this.callsign = inAirport.getCallsign();
        this.estDepartureAirportHorizDistance = inAirport.getEstDepartureAirportHorizDistance();
        this.estDepartureAirportVertDistance = inAirport.getEstDepartureAirportVertDistance();
        this.estArrivalAirportHorizDistance = inAirport.getEstArrivalAirportHorizDistance();
        this.estArrivalAirportVertDistance = inAirport.getEstArrivalAirportVertDistance();
        this.departureAirportCandidatesCount = inAirport.getDepartureAirportCandidatesCount();
        this.arrivalAirportCandidatesCount = inAirport.getArrivalAirportCandidatesCount();
    }
}
