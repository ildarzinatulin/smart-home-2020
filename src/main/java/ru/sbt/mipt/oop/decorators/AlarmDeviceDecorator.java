package ru.sbt.mipt.oop.decorators;

import ru.sbt.mipt.oop.sensorevent.SensorEvent;
import ru.sbt.mipt.oop.alarmdevice.SmartAlarmDevice;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;

import java.util.Collection;

import static ru.sbt.mipt.oop.sensorevent.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDeviceDecorator implements EventHandler {

    private Collection<EventHandler> eventHandlers;
    private SmartAlarmDevice alarmDevice;

    public AlarmDeviceDecorator(Collection<EventHandler> eventHandlers, SmartAlarmDevice alarmDevice) {
        this.eventHandlers = eventHandlers;
        this.alarmDevice = alarmDevice;
    }

    @Override
    public void handle(SensorEvent event) {
        if (alarmDevice.isActivated() && event.getType() != ALARM_DEACTIVATE) {
            alarmDevice.setToAlarmMode();
        }

        if (alarmDevice.isAlarm() && event.getType() != ALARM_DEACTIVATE) {
            System.out.println("Sending sms");
            return;
        }

        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handle(event);
        }
    }
}
