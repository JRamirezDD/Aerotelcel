package com.notifications_handler.service.EventHandler;

@FunctionalInterface
public interface EventHandler<E> {
    public void processEvent(E event);
}
