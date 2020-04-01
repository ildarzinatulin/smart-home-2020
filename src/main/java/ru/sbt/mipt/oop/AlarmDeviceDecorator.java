package ru.sbt.mipt.oop;

import java.util.Collection;

import static ru.sbt.mipt.oop.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDeviceDecorator implements EventHandler {

    private Collection<EventHandler> eventHandlers;
    private SmartAlarmDevice alarmDevice;

    AlarmDeviceDecorator(Collection<EventHandler> eventHandlers, SmartAlarmDevice alarmDevice) {
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
