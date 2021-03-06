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
        if (!isaLightEvent(event)) return;

        smartHome.execute(smartHomeObject -> {
            if (!(smartHomeObject instanceof Light)) return;

            Light light = (Light) smartHomeObject;
            if (!light.getId().equals(event.getObjectId())) return;
            updateLightState(event, light);
        });
    }

    private void updateLightState(SensorEvent event, Light light) {
        String lightEvent;
        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            lightEvent = " was turned on.";
        } else {
            light.setOn(false);
            lightEvent = " was turned off.";
        }
        System.out.println("Light " + light.getId() + " in room " + lightEvent);
    }

    private boolean isaLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }

}
