package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.sensorevent.SensorEvent;
import ru.sbt.mipt.oop.sensorevent.SensorEventCreator;

import java.util.List;

class LoopOfHandlesSensorEvents {

    private List<EventHandler> handlers;
    private SensorEventCreator eventCreator;

    LoopOfHandlesSensorEvents(List<EventHandler> handlers, SensorEventCreator eventCreator) {
        this.handlers = handlers;
        this.eventCreator = eventCreator;
    }

    void run() {
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
