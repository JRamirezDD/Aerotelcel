/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.flightdata_handler.events.FlightModifiedEvent;

import com.flightdata_handler.model.Flight;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class FlightModifiedEvent extends ApplicationEvent {
    private final Flight flight;

    public FlightModifiedEvent(Object source, Flight flight) {
        super(source);
        this.flight = flight;
    }

}
