package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler {

    private SmartHome smartHome;
    private CommandSender commandSender;

    HallDoorEventHandler(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    private void ternOffAllLight() {
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
                    ternOffAllLight();
                }
            });
        });
    }

}
