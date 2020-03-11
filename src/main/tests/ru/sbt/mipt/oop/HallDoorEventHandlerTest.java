package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HallDoorEventHandlerTest {

    @Test
    void lightsTurnedOff() throws IOException {
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
    void dontHandleFalseEvent() {

    }
}