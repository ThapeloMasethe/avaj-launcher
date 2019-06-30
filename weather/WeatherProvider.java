package weather;

import aircrafts.Coordinates;
import java.util.Random;

/**
 * WeatherProvider Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-20 
 * @version 1.0
*/
public class WeatherProvider {
    private static WeatherProvider weatherProvider =   new WeatherProvider();
    private static String[] weather  = {"RAIN", "FOG", "SUN", "SNOW"};

    /**
     * Declare an empty constructor {@code WeatherProvider} so
     * that the class can be instantiated without parameters.
     */
    private WeatherProvider() {

    }
    
    /**
     * Gets the weather provider object.
     * 
     * @return  the weather provider.
     */
    public static WeatherProvider  getProvider() {
        return WeatherProvider.weatherProvider;
    }

    /**
     * Gets the current weather.
     * 
     * @param coordinates The coordinates of the aircraft.
     * @return the random weather.
     */
    public String   getCurrentWeather(Coordinates coordinates) {
        int randomIndex = 0;

        if (coordinates.getLatitude() < coordinates.getLongitude()) {
            randomIndex = coordinates.getHeight()
                        + coordinates.getLatitude()
                        + coordinates.getLongitude();
            return weather[randomIndex % 4];
        } else {
            Random rand = new Random();
            randomIndex = rand.nextInt(4);
        }
		return weather[randomIndex];
    }
}