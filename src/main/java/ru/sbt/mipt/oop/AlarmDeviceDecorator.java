package ru.sbt.mipt.oop;

public class AlarmDeviceDecorator implements EventHandler {

    private EventHandler eventHandler;
    private SmartAlarmDevice alarmDevice;

    public AlarmDeviceDecorator(EventHandler eventHandler, SmartAlarmDevice alarmDevice) {
        this.eventHandler = eventHandler;
        this.alarmDevice = alarmDevice;
    }

    @Override
    public void handle(SensorEvent event) {
        if (alarmDevice.isActivated()) {
            alarmDevice.setToAlarmMode();
            System.out.println("Sending sms");
        }

        if (alarmDevice.isAlarm()) {
            System.out.println("Sending sms");
            return;
        }

        eventHandler.handle(event);
    }
}
