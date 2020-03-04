package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {

    private SmartHome smartHome;

    DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (isaDoorEvent(event)) {
            Door door = getDoorById(event.getObjectId());
            if (door != null) {
                updateDoorState(event, door);
            }
        }
    }

    private Room findWhereDoorById(String DoorId) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(DoorId)) {
                    return room;
                }
            }
        }
        return null;
    }

    private void updateDoorState(SensorEvent event, Door door) {
        Room room = findWhereDoorById(door.getId());
        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            if (room.getName().equals("hall")) {
                ternOffAllLight();
            }
        }
    }

    private void ternOffAllLight() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }

    private Door getDoorById(String objectId) {
        Room room = findWhereDoorById(objectId);
        for (Door door : room.getDoors()) {
            if (door.getId().equals(objectId)) {
                return door;
            }
        }

        return null;
    }

    private boolean isaDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

}
