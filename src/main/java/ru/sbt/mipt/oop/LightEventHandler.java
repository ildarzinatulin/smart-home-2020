package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler {

    private SmartHome smartHome;

    LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (isaLightEvent(event)) {
            Light light = getLightById(event.getObjectId());
            if (light != null) {
                updateLightState(event, light);
            }
        }
    }

    private void updateLightState(SensorEvent event, Light light) {
        Room room = findWhereLightById(light.getId());
        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        } else {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
    }

    private Light getLightById(String eventId) {
        Room room = findWhereLightById(eventId);
        for (Light light : room.getLights()) {
            if (light.getId().equals(eventId)) {
                return light;
            }
        }
        return null;
    }

    private boolean isaLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }

    private Room findWhereLightById(String LightId) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(LightId)) {
                    return room;
                }
            }
        }
        return null;
    }
}
