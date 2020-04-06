package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventhandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.LightEventHandler;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.readers.SmartHomeJsonReader;
import ru.sbt.mipt.oop.sensorevent.SensorEvent;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.sensorevent.SensorEventType.*;


class LightEventHandlerTest {

    @Test
    void handle() throws IOException {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        String objectId = "2";
        SensorEvent event = new SensorEvent(LIGHT_OFF, objectId);
        LightEventHandler handler = new LightEventHandler(smartHome);
        handler.handle(event);

        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            if (light.getId() != objectId) return;
            assertFalse(light.isOn());
        });
    }

    @Test
    void alienEvent() throws IOException {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        String objectId = "4";
        SensorEvent event = new SensorEvent(DOOR_CLOSED, objectId);
        DoorEventHandler handler = new DoorEventHandler(smartHome);
        handler.handle(event);

        smartHome.execute(object -> {
            if (!(object instanceof Door)) return;
            Door door = (Door) object;
            if (door.getId() != objectId) return;
            assertTrue(door.isOpen());
        });
    }
}