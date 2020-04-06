package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.SmartHome;

public class TurnOnAllLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            light.setOn(true);
        });
    }
}
