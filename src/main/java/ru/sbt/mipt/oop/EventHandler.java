package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventHandler {
    void handle(SensorEvent event);
}
