package za.co.tmasethe.controller;

import za.co.tmasethe.Application;
import za.co.tmasethe.model.Coordinates;
import za.co.tmasethe.model.Aircraft;
import za.co.tmasethe.service.Flyable;

import java.security.NoSuchAlgorithmException;

public class Helicopter extends Aircraft implements Flyable {

    WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    /**
     * Update the conditions based on the current weather.
     */
    public void updateConditions() throws NoSuchAlgorithmException {
        String weatherType = weatherTower.getWeather(this.coordinates);
        String helicopterMessage = String.format("Helicopter#%s(%s): ", this.name, this.id);

        if (weatherType.equals("SUN")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 10,
                    coordinates.getLatitude(),
                coordinates.getHeight() + 2);

            Application.printWriter.println(helicopterMessage + "This is hot.");
        } else if (weatherType.equals("RAIN")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 5,
                    coordinates.getLatitude(),
                    coordinates.getHeight());

            Application.printWriter.println(helicopterMessage + "It's raining. Better watch out for lightings.");
        } else if (weatherType.equals("FOG")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 1,
                    coordinates.getLatitude(),
                    coordinates.getHeight());

            Application.printWriter.println(helicopterMessage + "My rotor is going to freeze!");
        } else if (weatherType.equals("SNOW")) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                coordinates.getHeight() - 12);

            Application.printWriter.println(helicopterMessage + "It's snowing. We're gonna crash");
        }

        // If height is Zero, the Helicopter lands, unregisters from the weather tower and logs its current coordinates.
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            Application.printWriter.println("Helicopter#" + this.name + "(" + this.id + ") landing.");
            Application.printWriter.println("Tower says: Helicopter#"
                    + helicopterMessage
                    + " unregistered from weather tower.");
        }
    }

    /**
     * Register Helicopter to the tower.
     * 
     * @param weatherTower The object to register to the weather tower.
     */
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;

        // Register Helicopter to the tower.
        this.weatherTower.register(this);
        Application.printWriter.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")"
                + " registered to weather tower.");
    }
}
