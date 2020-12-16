package za.co.tmasethe.controller;

import za.co.tmasethe.model.Coordinates;
import za.co.tmasethe.model.Weather;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class WeatherProvider {

    private final Random random;

    private WeatherProvider() throws NoSuchAlgorithmException {
        random = SecureRandom.getInstanceStrong();
    }
    
    /**
     * Gets the weather provider object.
     * 
     * @return  the weather provider.
     */
    public static WeatherProvider getProvider() throws NoSuchAlgorithmException {
        return new WeatherProvider();
    }

    /**
     * Gets the current weather.
     * 
     * @param coordinates The coordinates of the aircraft.
     * @return the random weather.
     */
    public String getCurrentWeather(Coordinates coordinates) {
        int randomIndex;

        if (coordinates.getLatitude() < coordinates.getLongitude()) {
            randomIndex = coordinates.getHeight()
                        + coordinates.getLatitude()
                        + coordinates.getLongitude();
            return Weather.getWeather(randomIndex % 4);
        } else {
            randomIndex = this.random.nextInt(4);
        }
		return Weather.getWeather(randomIndex);
    }
}
