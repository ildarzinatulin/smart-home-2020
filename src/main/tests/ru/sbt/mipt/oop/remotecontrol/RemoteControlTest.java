package ru.sbt.mipt.oop.remotecontrol;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.readers.SmartHomeJsonReader;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RemoteControlTest {
    @Test
    void buttonWork() {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        smartHome.execute(object -> { //turn оn all light
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            light.setOn(true);
        });

        String buttonCode = "1";
        String rcId = "1";

        TurnOffAllLightCommand turnOffAllLightCommand = new TurnOffAllLightCommand(smartHome);
        RemoteControl remoteControl = new RemoteControl(Map.of(
                buttonCode, turnOffAllLightCommand));

        remoteControl.onButtonPressed(buttonCode, rcId);

        smartHome.execute(object -> { //turn оn all light
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            assertFalse(light.isOn());
        });
    }
}