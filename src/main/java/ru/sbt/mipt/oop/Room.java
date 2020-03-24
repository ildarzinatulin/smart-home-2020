package ru.sbt.mipt.oop;

import java.util.Collection;

public class Room implements Actionable{
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
        lights.forEach(light -> light.execute(action));
        doors.forEach(door -> door.execute(action));
    }
}
