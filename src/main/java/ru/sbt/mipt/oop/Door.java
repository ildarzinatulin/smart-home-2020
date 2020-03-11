package ru.sbt.mipt.oop;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    String getId() {
        return id;
    }

    boolean getState() {
        return isOpen;
    }

    void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.accept(this);
    }
}
