package com.flightdata_handler.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flightdata_handler.model.enums.FlightStatusEnum;
import lombok.*;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Calendar;

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

    @JsonProperty("type")           // Either departure or arrival
    private String type;

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

    // If its a departuretype, the comparison is done between if its already at the airport and the departure time
    public Timestamp getDepartureDelay(Flight flight){
        Calendar departureTime = getHourFromTimestamp(this.firstSeen);
        Calendar arrivalTime = getHourFromTimestamp(this.lastSeen);

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

    public Timestamp getArrivalDelay(Flight flight){
        Calendar arrivalTime = getHourFromTimestamp(this.lastSeen);

        Calendar now = Calendar.getInstance();

        int depHour = arrivalTime.get(Calendar.HOUR_OF_DAY);
        int depMin = arrivalTime.get(Calendar.MINUTE);

        int currHour = now.get(Calendar.HOUR_OF_DAY);
        int currMin = now.get(Calendar.MINUTE);

        if(flight.isOn_ground() && depHour < currHour && depMin < currMin){
            flight.setArrivalDelayed(true);

            long diffInMillis = now.getTimeInMillis() - arrivalTime.getTimeInMillis();
            long diffInMinutes = diffInMillis / (60 * 1000);
            long hours = diffInMinutes / 60;
            long minutes = diffInMinutes % 60;

            now.set(Calendar.HOUR_OF_DAY, (int)hours);
            now.set(Calendar.MINUTE, (int)minutes);
            now.set(Calendar.SECOND, 0);
            now.set(Calendar.MILLISECOND, 0);

            return new Timestamp(now.getTimeInMillis());
        } else {
            flight.setArrivalDelayed(false);
            return new Timestamp(0);
        }
    }

    public Calendar getHourFromTimestamp(Timestamp timestamp){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal;
    }
}
