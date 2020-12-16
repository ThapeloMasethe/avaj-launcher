package za.co.tmasethe.controller;

import za.co.tmasethe.Application;
import za.co.tmasethe.model.Aircraft;
import za.co.tmasethe.model.Coordinates;
import za.co.tmasethe.model.Weather;
import za.co.tmasethe.service.Flyable;

import java.security.NoSuchAlgorithmException;

public class Balloon extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    /**
     * Update the conditions based on the current weather.
     */
    public void updateConditions() throws NoSuchAlgorithmException {
        String weatherCondition  = weatherTower.getWeather(this.coordinates);
        String balloonMessage = String.format("Balloon#%s(%s): ", this.name, this.id);
      
        if (weatherCondition.equals(Weather.SUN.getType())) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude() + 2,
                    coordinates.getLatitude(),
                    coordinates.getHeight() + 4);

            Application.printWriter.println(balloonMessage + "Let's enjoy the good weather and take some pics");
        } else if (weatherCondition.equals(Weather.RAIN.getType())) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                coordinates.getHeight() - 5);

            Application.printWriter.println(balloonMessage + "Damn you rain! You messed up my balloon.");
        } else if (weatherCondition.equals(Weather.FOG.getType())) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                coordinates.getHeight() - 3);

            Application.printWriter.println(balloonMessage + "It's foggy, I cant see for real man!");
        } else if (weatherCondition.equals(Weather.SNOW.getType())) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                coordinates.getHeight() - 15
            );
            Application.printWriter.println(balloonMessage + "It's snowing. We're gonna crash");
        }
        // If height is Zero, the Helicopter lands, unregisters from the weather tower and logs its current coordinates
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            Application.printWriter.println("Balloon#" + this.name + "(" + this.id + ") landing.");
            Application.printWriter.println("Tower says: Balloon#"
                    + balloonMessage
                    + " unregistered from weather tower.");
        }
    }
    
    /**
     * Register the Balloon to the tower.
     *  
     * @param weatherTower The object to register to the weather tower.
     */
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;

        // Register Balloon to the tower.
        this.weatherTower.register(this);
        Application.printWriter.println("Tower says: Balloon#" + this.name + "(" + this.id + ")"
        + " registered to weather tower.");
    }
}
