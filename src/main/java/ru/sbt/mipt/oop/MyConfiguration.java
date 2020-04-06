package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.adapters.EventHandlerAdapter;
import ru.sbt.mipt.oop.alarmdevice.SmartAlarmDevice;
import ru.sbt.mipt.oop.command.CommandSenderOutInConsole;
import ru.sbt.mipt.oop.decorators.AlarmDeviceDecorator;
import ru.sbt.mipt.oop.eventhandlers.DoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.EventHandler;
import ru.sbt.mipt.oop.eventhandlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.eventhandlers.LightEventHandler;
import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.readers.SmartHomeJsonReader;
import ru.sbt.mipt.oop.remotecontrol.*;
import ru.sbt.mipt.oop.sensorevent.SensorEventType;

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
    public Command turnOffAllLightCommand(SmartHome smartHome) {
        return new TurnOffAllLightCommand(smartHome);
    }

    @Bean
    public Command closeHallDoorCommand(SmartHome smartHome) {
        return new CloseHallDoorCommand(smartHome);
    }

    @Bean
    public Command turnOnHallLightCommand(SmartHome smartHome) {
        return new TurnOnHallLightCommand(smartHome);
    }

    @Bean
    public Command activateAlarmDeviceCommand(SmartHome smartHome, String code) {
        return new ActivateAlarmDeviceCommand(smartHome,code);
    }

    @Bean
    public String code() {
        return "1234";
    }

    @Bean
    public Command activateAlarmModeCommand(SmartHome smartHome) {
        return new ActivateAlarmModeCommand(smartHome);
    }

    @Bean
    public Command turnOnAllLightCommand(SmartHome smartHome) {
        return new TurnOnAllLightCommand(smartHome);
    }

    @Bean
    public RemoteControl remoteControl(Command turnOffAllLightCommand, Command closeHallDoorCommand,
                                       Command turnOnHallLightCommand, Command activateAlarmDeviceCommand,
                                       Command activateAlarmModeCommand, Command turnOnAllLightCommand) {
        return new RemoteControl(Map.of(
                "1", turnOffAllLightCommand,
                "2", closeHallDoorCommand,
                "3", turnOnHallLightCommand,
                "4", activateAlarmDeviceCommand,
                "A", activateAlarmModeCommand,
                "B", turnOnAllLightCommand));
    }

    @Bean
    public String rcId() {
        return "1";
    }

    @Bean
    public RemoteControlRegistry remoteControlRegistry(RemoteControl remoteControl, String rcId) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteControl, rcId);
        return remoteControlRegistry;
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
