package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;

import java.util.Map;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler{
    private final EventHandler eventHandler;
    private final Map<String, SensorEventType> typeMap;
/*
    private final Map<String, SensorEventType> convertType = Map.of(
            "LightIsOn", SensorEventType.LIGHT_ON,
            "LightIsOff", SensorEventType.LIGHT_OFF,
            "DoorIsOpen", SensorEventType.DOOR_OPEN,
            "DoorIsClosed", SensorEventType.DOOR_CLOSED
    );*/

    EventHandlerAdapter(EventHandler eventHandler, Map<String, SensorEventType> typeMap) {
        this.eventHandler = eventHandler;
        this.typeMap = typeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent sensorEvent) {
        SensorEventType sensorEventType = typeMap.get(sensorEvent.getEventType());
        eventHandler.handle(new SensorEvent(sensorEventType, sensorEvent.getObjectId()));
    }

}
