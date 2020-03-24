package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private SmartAlarmDevice alarmDevice;

    public SmartHome(Collection<Room> rooms, SmartAlarmDevice alarmDevice) {
        this.rooms = rooms;
        this.alarmDevice = alarmDevice;
    }

    public SmartHome(SmartAlarmDevice alarmDevice) {
        this.alarmDevice = alarmDevice;
    }

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
        rooms.forEach(room -> room.execute(action));
    }

    void addAlarmDevice(SmartAlarmDevice alarmDevice) {
        this.alarmDevice = alarmDevice;
    }

    SmartAlarmDevice getAlarmDevice() {
        return alarmDevice;
    }
}
