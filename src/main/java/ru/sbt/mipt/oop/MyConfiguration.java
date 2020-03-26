package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

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
    public EventHandler handler(SmartHome smartHome) {
        List<EventHandler> handlers = Arrays.asList(new DoorEventHandler(smartHome), new LightEventHandler(smartHome),
                new HallDoorEventHandler(smartHome, new CommandSenderOutInConsole()));
        return new AlarmDeviceDecorator(handlers, smartHome.getAlarmDevice());
    }

    @Bean
    public SensorEventsManager sensorEventsManager(EventHandler handler, SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new EventHandlerAdapter(handler));
        return sensorEventsManager;
    }

}
