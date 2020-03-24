package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void execute() {
        Light light = new Light("1", false);
        Door door = new Door(false, "1");
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "badbadroom");
        room.execute(object -> {
            if (!(object instanceof Light)) return;
            assertEquals(object, light);
        });
    }
}