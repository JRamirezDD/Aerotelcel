package com.notifications_handler.service;

@FunctionalInterface
public interface EventHandler<E> {
    public void processEvent(E event);
}
