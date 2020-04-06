package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.sensorevent.SensorEvent;

public interface EventHandler {
    void handle(SensorEvent event);
}
