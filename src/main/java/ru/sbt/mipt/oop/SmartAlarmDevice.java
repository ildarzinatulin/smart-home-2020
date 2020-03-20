package ru.sbt.mipt.oop;

public class SmartAlarmDevice implements AlarmDevice{

    private AlarmDevice state = new AlarmDeviceDeactivatedState(this);
    private String code;

    public SmartAlarmDevice(String code) {
        this.code = code;
    }

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public void setToAlarmMode() {
        state.setToAlarmMode();
    }

    void setState(AlarmDevice state) {
        this.state = state;
    }

    boolean codeIsEqualTo(String code) {
        return this.code.equals(code);
    }

    boolean isActivated() {
        return state instanceof AlarmDeviceActivatedState;
    }

    boolean isAlarm() {
        return state instanceof AlarmDeviceAlarmState;
    }

}
