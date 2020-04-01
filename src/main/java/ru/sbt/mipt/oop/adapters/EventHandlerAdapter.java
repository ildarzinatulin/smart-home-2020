package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.sensorevent.SensorEvent;
import ru.sbt.mipt.oop.sensorevent.SensorEventType;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;

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

    public EventHandlerAdapter(EventHandler eventHandler, Map<String, SensorEventType> typeMap) {
        this.eventHandler = eventHandler;
        this.typeMap = typeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent sensorEvent) {
        SensorEventType sensorEventType = typeMap.get(sensorEvent.getEventType());
        eventHandler.handle(new SensorEvent(sensorEventType, sensorEvent.getObjectId()));
    }

}
