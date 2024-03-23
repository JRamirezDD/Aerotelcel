/*
 *    Title: Source Code
 *    Author: Ramirez de Diego, Jorge
 *    Date: 2024
 *    Code version: 1.0
 *    Availability: https://github.com/JRamirezDD/Aerotelcel
 */


package com.flightdata_handler.events;

import com.flightdata_handler.model.Airport;
import org.springframework.context.ApplicationEvent;

public class AirportModifiedEvent extends ApplicationEvent {
    private final Airport airport;

    public AirportModifiedEvent(Object source, Airport airport) {
        super(source);
        this.airport = airport;
    }

    public Airport getAirport() {
        return airport;
    }
}
