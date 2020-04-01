package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DoorEventHandlerTest {
    @Test
    void handle() throws IOException {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        String objectId = "4";
        SensorEvent event = new SensorEvent(DOOR_CLOSED, objectId);
        DoorEventHandler handler = new DoorEventHandler(smartHome);
        handler.handle(event);

        smartHome.execute(object -> {
            if (!(object instanceof Door)) return;
            Door door = (Door) object;
            if (door.getId() != objectId) return;
            assertFalse(door.isOpen());
        });
    }

    @Test
    void alienEvent() throws IOException {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        String objectId = "4";
        SensorEvent event = new SensorEvent(LIGHT_ON, objectId);
        DoorEventHandler handler = new DoorEventHandler(smartHome);
        handler.handle(event);

        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            if (light.getId() != objectId) return;
            assertFalse(light.isOn());
        });
    }

}