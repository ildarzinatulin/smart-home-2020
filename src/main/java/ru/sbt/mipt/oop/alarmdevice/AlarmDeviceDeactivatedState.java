package ru.sbt.mipt.oop.alarmdevice;

public class AlarmDeviceDeactivatedState implements AlarmDevice {

    private SmartAlarmDevice alarmDevice;

    public AlarmDeviceDeactivatedState(SmartAlarmDevice alarmDevice) {
        this.alarmDevice = alarmDevice;
    }

    @Override
    public void activate(String code) {
        if (!alarmDevice.codeIsEqualTo(code)) {
            throw new RuntimeException("Code is not true");
        }
        alarmDevice.setState(new AlarmDeviceActivatedState(alarmDevice));
    }

    @Override
    public void deactivate(String code) {
        //do nothing
    }

    @Override
    public void setToAlarmMode() {
        alarmDevice.setState(new AlarmDeviceAlarmState(alarmDevice));
    }
}
