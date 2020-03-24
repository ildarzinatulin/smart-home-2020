package ru.sbt.mipt.oop;

import java.util.Collection;

public class AlarmDeviceDecorator implements EventHandler {

    private Collection<EventHandler> eventHandlers;
    private SmartAlarmDevice alarmDevice;

    AlarmDeviceDecorator(Collection<EventHandler> eventHandlers, SmartAlarmDevice alarmDevice) {
        this.eventHandlers = eventHandlers;
        this.alarmDevice = alarmDevice;
    }

    @Override
    public void handle(SensorEvent event) {
        if (alarmDevice.isActivated()) {
            alarmDevice.setToAlarmMode();
        }

        if (alarmDevice.isAlarm()) {
            System.out.println("Sending sms");
            return;
        }

        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handle(event);
        }
    }
}
