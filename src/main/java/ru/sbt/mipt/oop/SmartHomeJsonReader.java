package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class SmartHomeJsonReader {

    SmartHome readSmartHome(String path) {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get(path)));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not read SmartHome from " + path, e);
        }
    }
}
