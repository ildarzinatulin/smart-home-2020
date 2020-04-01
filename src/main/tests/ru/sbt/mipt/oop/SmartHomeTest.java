package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeTest {

    @Test
    void execute() {
        SmartHome smartHome = new SmartHome();
        Light light = new Light("1", false);
        Door door = new Door(false, "1");
        Room room = new Room(Arrays.asList(light), Arrays.asList(door), "badbadroom");
        smartHome.addRoom(room);
        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            assertEquals(object, light);
        });
    }
}