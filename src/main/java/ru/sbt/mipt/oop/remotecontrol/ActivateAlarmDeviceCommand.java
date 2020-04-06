package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.objects.SmartHome;

public class ActivateAlarmDeviceCommand implements Command{
    private final SmartHome smartHome;
    private final String code;

    public ActivateAlarmDeviceCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getAlarmDevice().activate(code);
    }
}
