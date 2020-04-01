package ru.sbt.mipt.oop;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SmartAlarmDeviceTest {

    private final String trueCode = "1234";
    private final String falseCode = "1235";
    SmartAlarmDevice alarmDevice = new SmartAlarmDevice(trueCode);

    @Test
    void deactivate() {
        alarmDevice.deactivate(trueCode);
        assertFalse(alarmDevice.isActivated());
    }

    @Test
    void setToAlarmMode() {
        alarmDevice.setToAlarmMode();
        assertTrue(alarmDevice.isAlarm());
    }

    @Test
    void setState() {
        alarmDevice.setState(new AlarmDeviceActivatedState(alarmDevice));
        assertTrue(alarmDevice.isActivated());

        alarmDevice.setState(new AlarmDeviceDeactivatedState(alarmDevice));
        assertFalse(alarmDevice.isActivated());

        alarmDevice.setState(new AlarmDeviceAlarmState(alarmDevice));
        assertFalse(alarmDevice.isActivated());
    }

    @Test
    void codeIsEqualTo() {
        assertTrue(alarmDevice.codeIsEqualTo(trueCode));
        assertFalse(alarmDevice.codeIsEqualTo(falseCode));
    }

    @Test
    void isActivated() {
        alarmDevice.activate(trueCode);
        assertTrue(alarmDevice.isActivated());

        alarmDevice.deactivate(trueCode);
        assertFalse(alarmDevice.isActivated());

        try {
            alarmDevice.activate(falseCode);
            Assert.fail();
        } catch (RuntimeException ignored) {}
    }

    @Test
    void isAlarm() {
        alarmDevice.activate(trueCode);
        alarmDevice.deactivate(falseCode);
        assertTrue(alarmDevice.isAlarm());
    }
}