/*
 *    Title: Source Code
 *    Author: Ortega Mendoza, Javier
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */

package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Calendar;

// Child class to be used for flights that are in the airport whether it's a departure or arrival
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "departures")
public class Departures {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport")
    @JsonBackReference
    private Airport airport;

    @JsonProperty("icao24")
    private String icao24;

    // Time of departure!
    @JsonProperty("firstSeen")
    private Timestamp firstSeen;

    @JsonProperty("estDepartureAirport")
    private String estDepartureAirport;

    // Time of arrival!
    @JsonProperty("lastSeen")
    private Timestamp lastSeen;

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

    public void updateFrom(Departures departures){
        this.icao24 = departures.getIcao24();
        this.firstSeen = departures.getFirstSeen();
        this.estDepartureAirport = departures.getEstDepartureAirport();
        this.lastSeen = departures.getLastSeen();
        this.estArrivalAirport = departures.getEstArrivalAirport();
        this.callsign = departures.getCallsign();
        this.estDepartureAirportHorizDistance = departures.getEstDepartureAirportHorizDistance();
        this.estDepartureAirportVertDistance = departures.getEstDepartureAirportVertDistance();
        this.estArrivalAirportHorizDistance = departures.getEstArrivalAirportHorizDistance();
        this.estArrivalAirportVertDistance = departures.getEstArrivalAirportVertDistance();
        this.departureAirportCandidatesCount = departures.getDepartureAirportCandidatesCount();
        this.arrivalAirportCandidatesCount = departures.getArrivalAirportCandidatesCount();
    }

    // If its a departuretype, the comparison is done between if its already at the airport and the departure time
    public Timestamp getDepartureDelay(Flight flight){
        Calendar departureTime = getHourFromTimestamp(this.firstSeen);

        Calendar now = Calendar.getInstance();

        int depHour = departureTime.get(Calendar.HOUR_OF_DAY);
        int depMin = departureTime.get(Calendar.MINUTE);

        int currHour = now.get(Calendar.HOUR_OF_DAY);
        int currMin = now.get(Calendar.MINUTE);

        if(flight.isOn_ground() && depHour < currHour && depMin < currMin){
            flight.setDepartureDelayed(true);

            long diffInMillis = now.getTimeInMillis() - departureTime.getTimeInMillis();
            long diffInMinutes = diffInMillis / (60 * 1000);
            long hours = diffInMinutes / 60;
            long minutes = diffInMinutes % 60;

            now.set(Calendar.HOUR_OF_DAY, (int)hours);
            now.set(Calendar.MINUTE, (int)minutes);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);

            return new Timestamp(now.getTimeInMillis());
        } else {
            flight.setDepartureDelayed(false);
            return new Timestamp(0);
        }
    }

    public Calendar getHourFromTimestamp(Timestamp timestamp){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal;
    }
}
