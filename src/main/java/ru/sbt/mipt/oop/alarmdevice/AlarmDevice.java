package ru.sbt.mipt.oop.alarmdevice;

public interface AlarmDevice {
    void activate(String code);
    void deactivate(String code);
    void setToAlarmMode();
}
