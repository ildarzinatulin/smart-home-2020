package ru.sbt.mipt.oop;

class SmartAlarmDevice {

    private AlarmDevice state = new AlarmDeviceDeactivatedState(this);
    private String code;

    SmartAlarmDevice(String code) {
        this.code = code;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void setToAlarmMode() {
        state.setToAlarmMode();
    }

    void setState(AlarmDevice state) {
        this.state = state;
    }

    public boolean codeIsEqualTo(String code) {
        return this.code.equals(code);
    }

    public boolean isActivated() {
        return state instanceof AlarmDeviceActivatedState;
    }

    public boolean isAlarm() {
        return state instanceof AlarmDeviceAlarmState;
    }

}
