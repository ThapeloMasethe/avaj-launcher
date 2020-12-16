package za.co.tmasethe.service;

import za.co.tmasethe.controller.WeatherTower;

import java.security.NoSuchAlgorithmException;

public interface Flyable {
    void updateConditions() throws NoSuchAlgorithmException;
    void registerTower(WeatherTower weatherTower);
}
