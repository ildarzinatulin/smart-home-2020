package ru.sbt.mipt.oop;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.objects.Light;

import static org.junit.jupiter.api.Assertions.*;

class LightTest {

    @Test
    void execute() {
        Light light = new Light("0", false);
        light.execute(object -> {
            Light thisLight = (Light) object;
            thisLight.setOn(true);
        });
        assertTrue(light.isOn());
    }
}