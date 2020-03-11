package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

    @Test
    void execute() {
        Door door = new Door(false, "0");
        door.execute(object -> {
            Door thisDoor = (Door) object;
            thisDoor.setOpen(true);
        });
        assertTrue(door.getState());
    }
}