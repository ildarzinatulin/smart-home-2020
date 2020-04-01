package ru.sbt.mipt.oop;

class CommandSenderOutInConsole implements CommandSender{
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

}
