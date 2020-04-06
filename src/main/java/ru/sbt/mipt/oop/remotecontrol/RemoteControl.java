package ru.sbt.mipt.oop.remotecontrol;

import java.util.HashMap;
import java.util.Map;

public class RemoteControl implements rc.RemoteControl {
    private Map<String, Command> buttonsCommandMap = new HashMap<>();

    public RemoteControl(){
    }

    public RemoteControl(Map<String, Command> buttonsCommandMap) {
        this.buttonsCommandMap = buttonsCommandMap;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (buttonsCommandMap.containsKey(buttonCode)) {
            buttonsCommandMap.get(buttonCode).execute();
        }
    }
}
