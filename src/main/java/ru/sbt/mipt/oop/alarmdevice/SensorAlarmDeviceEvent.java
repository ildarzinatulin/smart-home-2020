package ru.sbt.mipt.oop.alarmdevice;

import ru.sbt.mipt.oop.sensorevent.SensorEvent;
import ru.sbt.mipt.oop.sensorevent.SensorEventType;

public class SensorAlarmDeviceEvent extends SensorEvent {

    private final String code;

    public SensorAlarmDeviceEvent(SensorEventType type, String objectId, String code) {
        super(type, objectId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + super.getType() +
                ", objectId='" + super.getObjectId() + '\'' +
                ", code=" + code +
                '}';
    }
}
