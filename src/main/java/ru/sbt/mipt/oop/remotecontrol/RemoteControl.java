package ru.sbt.mipt.oop.remotecontrol;

import java.util.HashMap;
import java.util.Map;

public class RemoteControl implements rc.RemoteControl {
    private Map<String, Command> buttonsCommandMap = new HashMap<>();

    public void setButtonCommand(String button, Command command) {
        if (buttonsCommandMap.containsKey(button)) {
            buttonsCommandMap.replace(button, command);
        } else {
            buttonsCommandMap.put(button, command);
        }
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (buttonsCommandMap.containsKey(buttonCode)) {
            buttonsCommandMap.get(buttonCode).execute();
        }
    }

    public Command getButtonCommand(String buttonCode) {
        return buttonsCommandMap.get(buttonCode);
    }

}
