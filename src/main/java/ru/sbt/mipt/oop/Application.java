package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = SmartHomeJsonReader.readSmartHome("smart-home-1.js");
        SensorEvent event = RandomSenserEventCreator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            EventHandler.handle(smartHome, event);
            event = RandomSenserEventCreator.getNextSensorEvent();
        }
    }

}
