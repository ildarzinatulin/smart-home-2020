package ru.sbt.mipt.oop;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        SmartAlarmDevice smartAlarmDevice = new SmartAlarmDevice("1234");
        smartHome.addAlarmDevice(smartAlarmDevice);


        List<EventHandler> handlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome),
                new HallDoorEventHandler(smartHome, new CommandSenderOutInConsole()));
        AlarmDeviceDecorator alarmDeviceDecorator = new AlarmDeviceDecorator(handlers, smartAlarmDevice);


        SensorEventCreator randomSensorEventCreator = new RandomSensorEventCreator();


        LoopOfHandlesSensorEvents loopOfHandlesSensorEvents = new LoopOfHandlesSensorEvents(
                Collections.singletonList(alarmDeviceDecorator), randomSensorEventCreator);
        loopOfHandlesSensorEvents.run();
    }


}
