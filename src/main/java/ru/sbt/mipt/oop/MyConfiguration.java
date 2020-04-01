package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
public class MyConfiguration {

    @Bean
    public SmartHome smartHome() {
        SmartHome smartHome = new SmartHomeJsonReader().readSmartHome("smart-home-1.js");
        SmartAlarmDevice smartAlarmDevice = new SmartAlarmDevice("1234");
        smartHome.addAlarmDevice(smartAlarmDevice);
        return smartHome;
    }

    @Bean
    public EventHandler doorEventHandler(SmartHome smartHome) {
        return new DoorEventHandler(smartHome);
    }

    @Bean
    public EventHandler lightEventHandler(SmartHome smartHome) {
        return new LightEventHandler(smartHome);
    }

    @Bean
    public EventHandler hallDoorEventHandler(SmartHome smartHome) {
        return new HallDoorEventHandler(smartHome, new CommandSenderOutInConsole());
    }

    @Bean
    public List<EventHandler> handlers(EventHandler hallDoorEventHandler, EventHandler lightEventHandler,
                                EventHandler doorEventHandler) {
        return Arrays.asList(hallDoorEventHandler, lightEventHandler, doorEventHandler);
    }

    @Bean
    public Map<String, SensorEventType> typeMap () {
        return Map.of(
                "LightIsOn", SensorEventType.LIGHT_ON,
                "LightIsOff", SensorEventType.LIGHT_OFF,
                "DoorIsOpen", SensorEventType.DOOR_OPEN,
                "DoorIsClosed", SensorEventType.DOOR_CLOSED
        );
    }

    @Bean
    public SensorEventsManager sensorEventsManager(List<EventHandler> handlers, SmartHome smartHome,
                                                   Map<String, SensorEventType> typeMap) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new EventHandlerAdapter(new AlarmDeviceDecorator(handlers,
                smartHome.getAlarmDevice()), typeMap));
        return sensorEventsManager;
    }

}
