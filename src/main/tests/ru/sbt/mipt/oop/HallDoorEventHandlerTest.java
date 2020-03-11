package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

class HallDoorEventHandlerTest {

    @Test
    void handle() throws IOException {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        String objectId = "4"; //door in Hall
        SensorEvent event = new SensorEvent(DOOR_CLOSED, objectId);
        HallDoorEventHandler handler = new HallDoorEventHandler(smartHome);
        handler.handle(event);
        smartHome.execute(smartHomeObject->{
            if (!(smartHomeObject instanceof Light)) return;
            Light light = (Light) smartHomeObject;
            assertFalse(light.isOn());
        });
    }

    @Test
    void alienEvent() throws IOException {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        String lightId = "7";
        LightEventHandler lightEventHandler = new LightEventHandler(smartHome);
        SensorEvent eventLightOn = new SensorEvent(LIGHT_ON, lightId);
        lightEventHandler.handle(eventLightOn);

        String doorInHallId = "4";
        SensorEvent eventClosedDoor = new SensorEvent(DOOR_CLOSED, doorInHallId);
        HallDoorEventHandler handler = new HallDoorEventHandler(smartHome);
        handler.handle(eventClosedDoor);

        smartHome.execute(smartHomeObject->{
            if (!(smartHomeObject instanceof Light)) return;
            Light light = (Light) smartHomeObject;
            if (light.getId() != lightId) return;
            assertFalse(light.isOn());
        });
    }
}