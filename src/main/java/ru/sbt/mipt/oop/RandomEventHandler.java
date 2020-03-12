package ru.sbt.mipt.oop;

import java.util.List;

public class RandomEventHandler {

    private List<EventHandler> handlers;
    private RandomSensorEventCreator eventCreator;

    public RandomEventHandler(List<EventHandler> handlers, RandomSensorEventCreator eventCreator) {
        this.handlers = handlers;
        this.eventCreator = eventCreator;
    }

    public void run() {
        SensorEvent event = eventCreator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler handler: handlers) {
                handler.handle(event);
            }
            event = eventCreator.getNextSensorEvent();
        }
    }
}
