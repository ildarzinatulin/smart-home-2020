package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

class EventHandler {

    static void handle(SmartHome smartHome, SensorEvent event) {
        handleLightEvent(smartHome, event);
        handleDoorEvent(smartHome, event);
    }

    private static void handleLightEvent(SmartHome smartHome, SensorEvent event) {
        if (isaLightEvent(event)) {
            Light light = getLightById(smartHome, event.getObjectId());
            if (light != null) {
                updateLightState(smartHome, event, light);
            }
        }
    }

    private static void updateLightState(SmartHome smartHome, SensorEvent event, Light light) {
        Room room = findWhereLightById(smartHome, light.getId());
        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
        } else {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
        }
    }

    private static Light getLightById(SmartHome smartHome, String eventId) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(eventId)) {
                    return light;
                }
            }
        }
        return null;
    }

    private static boolean isaLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }

    private static Room findWhereLightById(SmartHome smartHome, String LightId) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(LightId)) {
                    return room;
                }
            }
        }
        return null;
    }

    private static void handleDoorEvent(SmartHome smartHome, SensorEvent event) {
        if (isaDoorEvent(event)) {
            Door door = getDoorById(smartHome, event.getObjectId());
            if (door != null) {
                updateDoorState(smartHome, event, door);
            }
        }
    }

    private static Room findWhereDoorById(SmartHome smartHome, String DoorId) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(DoorId)) {
                    return room;
                }
            }
        }
        return null;
    }

    private static void updateDoorState(SmartHome smartHome, SensorEvent event, Door door) {
        Room room = findWhereDoorById(smartHome, door.getId());
        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
            if (room.getName().equals("hall")) {
                ternOffAllLight(smartHome);
            }
        }
    }

    private static void ternOffAllLight(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }


    private static Door getDoorById(SmartHome smartHome, String objectId) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(objectId)) {
                    return door;
                }
            }
        }
        return null;
    }

    private static boolean isaDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }
}
