package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");

        List<EventHandler> handlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome),
                new HallDoorEventHandler(smartHome, new CommandSender()));

        RandomSensorEventCreator randomSensorEventCreator = new RandomSensorEventCreator();

        RandomEventHandler randomEventHandler = new RandomEventHandler(handlers, randomSensorEventCreator);
        randomEventHandler.run();
    }

}
