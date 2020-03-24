package ru.sbt.mipt.oop;

public class Light implements Actionable{
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    String getId() {
        return id;
    }

    void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }
}
