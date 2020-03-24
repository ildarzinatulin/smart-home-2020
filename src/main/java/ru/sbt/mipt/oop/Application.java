package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");

        List<EventHandler> handlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome),
                new HallDoorEventHandler(smartHome, new CommandSender()));

        SensorEventCreator randomSensorEventCreator = new RandomSensorEventCreator();

        LoopOfHandlesSensorEvents loopOfHandlesSensorEvents = new LoopOfHandlesSensorEvents(handlers, randomSensorEventCreator);
        loopOfHandlesSensorEvents.run();
    }

}
