package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.alarmdevice.SensorAlarmDeviceEvent;
import ru.sbt.mipt.oop.sensorevent.SensorEvent;
import ru.sbt.mipt.oop.sensorevent.SensorEventType;
import ru.sbt.mipt.oop.objects.SmartHome;

public class AlarmDeviceHandler implements EventHandler {

    private SmartHome smartHome;

    public AlarmDeviceHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!(event instanceof SensorAlarmDeviceEvent)) return;
        SensorAlarmDeviceEvent alarmDeviceEvent = (SensorAlarmDeviceEvent) event;

        if (isAnAlarmActivateEvent(alarmDeviceEvent)) {
            smartHome.getAlarmDevice().activate(alarmDeviceEvent.getCode());
        }

        if (isAnAlarmDeactivateEvent(alarmDeviceEvent)) {
            smartHome.getAlarmDevice().deactivate(alarmDeviceEvent.getCode());
        }
    }

    private boolean isAnAlarmDeactivateEvent(SensorAlarmDeviceEvent alarmDeviceEvent) {
        return alarmDeviceEvent.getType() == SensorEventType.ALARM_DEACTIVATE;
    }

    private boolean isAnAlarmActivateEvent(SensorAlarmDeviceEvent alarmDeviceEvent) {
        return alarmDeviceEvent.getType() == SensorEventType.ALARM_ACTIVATE;
    }
}
