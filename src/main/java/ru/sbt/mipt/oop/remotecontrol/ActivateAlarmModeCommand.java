package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.objects.SmartHome;

public class ActivateAlarmModeCommand implements Command {
    private final SmartHome smartHome;

    public ActivateAlarmModeCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarmDevice().setToAlarmMode();
    }
}
