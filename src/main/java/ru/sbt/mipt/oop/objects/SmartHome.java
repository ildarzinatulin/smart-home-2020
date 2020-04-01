package ru.sbt.mipt.oop.objects;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.alarmdevice.SmartAlarmDevice;

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

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
        rooms.forEach(room -> room.execute(action));
    }

    public void addAlarmDevice(SmartAlarmDevice alarmDevice) {
        this.alarmDevice = alarmDevice;
    }

    public SmartAlarmDevice getAlarmDevice() {
        return alarmDevice;
    }
}
