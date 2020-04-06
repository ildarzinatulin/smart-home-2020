package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class TurnOnHallLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (!(object instanceof Room)) return;
            Room room = (Room) object;
            if (!room.getName().equals("hall")) return;
            room.execute(roomObject -> {
                if (!(roomObject instanceof Light)) return;
                Light light = (Light) roomObject;
                light.setOn(true);
            });
        });
    }
}
