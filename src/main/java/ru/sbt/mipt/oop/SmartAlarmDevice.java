package ru.sbt.mipt.oop;

class SmartAlarmDevice {

    private AlarmDevice state = new AlarmDeviceDeactivatedState(this);
    private String code;

    SmartAlarmDevice(String code) {
        this.code = code;
    }

    void activate(String code) {
        state.activate(code);
    }

    void deactivate(String code) {
        state.deactivate(code);
    }

    void setToAlarmMode() {
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
