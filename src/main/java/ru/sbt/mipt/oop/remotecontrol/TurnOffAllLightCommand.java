package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

public class TurnOffAllLightCommand implements Command{
    private final SmartHome smartHome;

    public TurnOffAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            light.setOn(false);
        });
    }
}
