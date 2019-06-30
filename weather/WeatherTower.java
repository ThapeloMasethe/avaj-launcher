package weather;

import aircrafts.Coordinates;

/**
 * WeatherTower Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-20
 * @version 1.0
 */
public class WeatherTower extends Tower {

    /**
     * Gets the weather.
     * 
     * @param coordinates The coordinates.
     * @return weather.
     */
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    /** Change weather based on the changed conditions. */
    public void changeWeather() {
        this.conditionsChanged();
    }
}