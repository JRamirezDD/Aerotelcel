package com.notifications_handler.service.FlightEventHandler;

import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightModifiedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import com.notifications_handler.service.SendingService.SendingProxy;
import com.notifications_handler.service.EventHandler;
import com.notifications_handler.service.FlightEventHandler.structurer.DelayedFlightEventStructurer;
import com.notifications_handler.service.FlightEventHandler.structurer.LandedFlightEventStructurer;
import com.notifications_handler.service.FlightEventHandler.structurer.TakenoffFlightEventStructurer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FlightEventHandler implements EventHandler<FlightModifiedEvent> {
    private final DelayedFlightEventStructurer delayedFlightEventStructurer;
    private final LandedFlightEventStructurer landedFlightEventStructurer;
    private final TakenoffFlightEventStructurer takenoffFlightEventStructurer;

    private final SendingProxy sendingProxy;


    public void processEvent(FlightModifiedEvent event) {
        Class<?> eventClass = event.getClass();

        if (eventClass == FlightDelayedEvent.class) {
            sendingProxy.sendNotification(delayedFlightEventStructurer.convertToEmail((FlightDelayedEvent) event));
            sendingProxy.sendNotification(delayedFlightEventStructurer.convertToSms((FlightDelayedEvent) event));
        }
        else if (eventClass == FlightLandedEvent.class) {
            sendingProxy.sendNotification(landedFlightEventStructurer.convertToEmail((FlightLandedEvent) event));
            sendingProxy.sendNotification(landedFlightEventStructurer.convertToSms((FlightLandedEvent) event));
        }
        else if (eventClass == FlightTakenoffEvent.class) {
            sendingProxy.sendNotification(takenoffFlightEventStructurer.convertToEmail((FlightTakenoffEvent) event));
            sendingProxy.sendNotification(takenoffFlightEventStructurer.convertToSms((FlightTakenoffEvent) event));
        }
    }

}
