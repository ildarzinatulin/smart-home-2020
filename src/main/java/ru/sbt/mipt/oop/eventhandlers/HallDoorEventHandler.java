package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.command.CommandSender;
import ru.sbt.mipt.oop.command.CommandType;
import ru.sbt.mipt.oop.command.SensorCommand;
import ru.sbt.mipt.oop.sensorevent.SensorEvent;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.sensorevent.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler {

    private SmartHome smartHome;
    private CommandSender commandSender;

    public HallDoorEventHandler(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    private void turnOffAllLight() {
        smartHome.execute(smartHomeObject -> {
            if (!(smartHomeObject instanceof Light)) return;

            Light light = (Light) smartHomeObject;
            light.setOn(false);

            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(command);
        });
    }

    @Override
    public void handle(SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) return;

        smartHome.execute(smartHomeObject -> {
            if (!(smartHomeObject instanceof Room)) return;

            Room room = (Room) smartHomeObject;
            if (!room.getName().equals("hall")) return;

            room.execute(nextSmartHomeObject -> {
                if (!(nextSmartHomeObject instanceof Door))  return;

                Door door = (Door) nextSmartHomeObject;
                if (door.getId().equals(event.getObjectId())) {
                    turnOffAllLight();
                }
            });
        });
    }

}
