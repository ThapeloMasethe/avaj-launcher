package za.co.tmasethe.controller;

import za.co.tmasethe.model.Coordinates;

import java.security.NoSuchAlgorithmException;

public class WeatherTower extends Tower {

    /**
     * Gets the weather.
     * 
     * @param coordinates The coordinates.
     * @return weather.
     */
    public String getWeather(Coordinates coordinates) throws NoSuchAlgorithmException {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    /** Change weather based on the changed conditions. */
    public void changeWeather() throws NoSuchAlgorithmException {
        this.conditionsChanged();
    }
}
