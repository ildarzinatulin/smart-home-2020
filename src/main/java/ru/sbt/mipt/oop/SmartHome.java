package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private AlarmDevice alarmDevice;

    public SmartHome(Collection<Room> rooms, AlarmDevice alarmDevice) {
        this.rooms = rooms;
        this.alarmDevice = alarmDevice;
    }

    public SmartHome(AlarmDevice alarmDevice) {
        this.alarmDevice = alarmDevice;
    }

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
        rooms.forEach(room -> room.execute(action));
    }

    public AlarmDevice getAlarmDevice() {
        return alarmDevice;
    }
}
