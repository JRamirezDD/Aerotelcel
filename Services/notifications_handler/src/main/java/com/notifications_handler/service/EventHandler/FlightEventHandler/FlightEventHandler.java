package com.notifications_handler.service.EventHandler.FlightEventHandler;

import com.flightdata_handler.events.FlightModifiedEvent.FlightDelayedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightLandedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightModifiedEvent;
import com.flightdata_handler.events.FlightModifiedEvent.FlightTakenoffEvent;
import com.notifications_handler.dto.NotificationData;
import com.notifications_handler.service.EventHandler.FlightEventHandler.FeignClients.Client_SubscriptionRedis;
import com.notifications_handler.service.EventHandler.FlightEventHandler.structurer.DelayedFlightEventStructurer;
import com.notifications_handler.service.EventHandler.FlightEventHandler.structurer.LandedFlightEventStructurer;
import com.notifications_handler.service.EventHandler.FlightEventHandler.structurer.TakenoffFlightEventStructurer;
import com.notifications_handler.service.SendingService.SendingProxy;
import com.notifications_handler.service.EventHandler.EventHandler;
import com.subscription_redis.dto.AviationDataSubscriptionsResponse;
import com.subscription_redis.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightEventHandler implements EventHandler<FlightModifiedEvent> {
    private final DelayedFlightEventStructurer delayedFlightEventStructurer;
    private final LandedFlightEventStructurer landedFlightEventStructurer;
    private final TakenoffFlightEventStructurer takenoffFlightEventStructurer;

    private final Client_SubscriptionRedis subscriptionRedisClient;


    private final SendingProxy sendingProxy;


    public void processEvent(FlightModifiedEvent event) {
        Class<?> eventClass = event.getClass();
        List<NotificationData> notificationDataList = new LinkedList<>();

        AviationDataSubscriptionsResponse aviationDataSubscriptionsResponse = subscriptionRedisClient.fetchSubscriptions(event.getFlight().getCallsign());
        List<String> recipientsList = aviationDataSubscriptionsResponse.getSubscriptions()
                .stream() // Convert the list to a stream
                .map(Subscription::getEmail) // Extract the email from each Subscription
                .toList(); // Collect the emails into a new List

        if (eventClass == FlightDelayedEvent.class) {
            for (String recipient : recipientsList) {
                notificationDataList.add(delayedFlightEventStructurer.convertToEmail((FlightDelayedEvent) event, recipient));
                notificationDataList.add(delayedFlightEventStructurer.convertToSms((FlightDelayedEvent) event, recipient));
            }
        }
        else if (eventClass == FlightLandedEvent.class) {
            for (String recipient : recipientsList) {
                notificationDataList.add(landedFlightEventStructurer.convertToEmail((FlightLandedEvent) event, recipient));
                notificationDataList.add(landedFlightEventStructurer.convertToSms((FlightLandedEvent) event, recipient));
            }
        }
        else if (eventClass == FlightTakenoffEvent.class) {
            for (String recipient : recipientsList) {
                notificationDataList.add(takenoffFlightEventStructurer.convertToEmail((FlightTakenoffEvent) event, recipient));
                notificationDataList.add(takenoffFlightEventStructurer.convertToSms((FlightTakenoffEvent) event, recipient));
            }
        }

        notificationDataList.forEach(sendingProxy::sendNotification);

    }

}
