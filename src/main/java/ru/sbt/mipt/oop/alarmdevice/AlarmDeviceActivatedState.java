package ru.sbt.mipt.oop.alarmdevice;

public class AlarmDeviceActivatedState implements AlarmDevice {

    private SmartAlarmDevice alarmDevice;

    public AlarmDeviceActivatedState(SmartAlarmDevice alarmDevice) {
        this.alarmDevice = alarmDevice;
    }

    @Override
    public void activate(String code) {
        //do nothing
    }

    @Override
    public void deactivate(String code) {
        if (!alarmDevice.codeIsEqualTo(code)) {
            alarmDevice.setState(new AlarmDeviceAlarmState(alarmDevice));
        } else {
            alarmDevice.setState(new AlarmDeviceDeactivatedState(alarmDevice));
        }
    }

    @Override
    public void setToAlarmMode() {
        alarmDevice.setState(new AlarmDeviceAlarmState(alarmDevice));
    }
}
