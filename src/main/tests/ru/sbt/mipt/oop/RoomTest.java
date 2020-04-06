package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;

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