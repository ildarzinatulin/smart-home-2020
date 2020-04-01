package ru.sbt.mipt.oop.alarmdevice;

public class AlarmDeviceAlarmState implements AlarmDevice {

    private SmartAlarmDevice alarmDevice;

    public AlarmDeviceAlarmState(SmartAlarmDevice alarmDevice) {
        this.alarmDevice = alarmDevice;
    }

    @Override
    public void activate(String code) {
        //do nothing
    }

    @Override
    public void deactivate(String code) {
        if (alarmDevice.codeIsEqualTo(code)) {
           alarmDevice.setState(new AlarmDeviceDeactivatedState(alarmDevice));
        }
    }

    @Override
    public void setToAlarmMode() {
        //do nothing
    }
}
