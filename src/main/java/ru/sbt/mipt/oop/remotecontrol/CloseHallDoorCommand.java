package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

public class CloseHallDoorCommand implements Command{
    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (!(object instanceof Room)) return;
            Room room = (Room) object;
            if (!room.getName().equals("hall")) return;
            room.execute(roomObject -> {
               if (!(roomObject instanceof Door)) return;
               Door door = (Door) roomObject;
               door.setOpen(true);
            });
        });
    }
}
