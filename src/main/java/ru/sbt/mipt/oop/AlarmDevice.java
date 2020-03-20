package ru.sbt.mipt.oop;

public interface AlarmDevice {
    void activate(String code);
    void deactivate(String code);
    void setToAlarmMode();
}
