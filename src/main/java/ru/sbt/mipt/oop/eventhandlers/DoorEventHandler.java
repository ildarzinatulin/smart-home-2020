package ru.sbt.mipt.oop.eventhandlers;

import ru.sbt.mipt.oop.sensorevent.SensorEvent;
import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.sensorevent.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensorevent.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {

    private SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isaDoorEvent(event)) return;
        smartHome.execute(smartHomeObject -> {
            if (!(smartHomeObject instanceof Door)) {
                return;
            }
            Door door = (Door) smartHomeObject;
            if (!door.getId().equals(event.getObjectId())) return;
            updateDoorState(event, door);
        });
    }

    private void updateDoorState(SensorEvent event, Door door) {
        String doorEvent;
        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            doorEvent = " was opened.";
        } else {
            door.setOpen(false);
            doorEvent = " was closed.";
        }
        System.out.println("Door " + door.getId() + doorEvent);
    }

    private boolean isaDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

}
