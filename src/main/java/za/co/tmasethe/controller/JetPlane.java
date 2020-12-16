package za.co.tmasethe.controller;

import za.co.tmasethe.Application;
import za.co.tmasethe.model.Coordinates;
import za.co.tmasethe.model.Aircraft;
import za.co.tmasethe.model.Weather;
import za.co.tmasethe.service.Flyable;

import java.security.NoSuchAlgorithmException;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    /**
     * Update the conditions based on the current weather condition.
     */
    public void updateConditions() throws NoSuchAlgorithmException {
        String weatherCondition = weatherTower.getWeather(this.coordinates);
        String jetPlaneMessage = String.format("JetPlane#%s(%s): ", this.name, this.id);

        if (weatherCondition.equals(Weather.SUN.getType())) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 10,
                    coordinates.getLatitude(),
                coordinates.getHeight() + 2);
            Application.printWriter.println(jetPlaneMessage + "Let's enjoy the good weather and take some pics");
        } else if (weatherCondition.equals(Weather.RAIN.getType())) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                coordinates.getLatitude() + 5,
                    coordinates.getHeight());
            Application.printWriter.println(jetPlaneMessage + "It's raining. Better watch out for lighting.");
        } else if (weatherCondition.equals(Weather.FOG.getType())) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                coordinates.getLatitude() + 1,
                    coordinates.getHeight());

            Application.printWriter.println(jetPlaneMessage + "OMG! Winter is coming!");
        } else if (weatherCondition.equals(Weather.SNOW.getType())) {
            this.coordinates = new Coordinates(
                    coordinates.getLongitude(),
                    coordinates.getLatitude(),
                    coordinates.getHeight() - 7);

            Application.printWriter.println(jetPlaneMessage + "It's snowing. We're gonna crash");
        }

        // If height is Zero, the Helicopter lands, unregisters from the weather tower and logs its current coordinates.
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            Application.printWriter.println("JetPlane#" + this.name + "(" + this.id + ") landing.");
            Application.printWriter.println("Tower says: Balloon#" + this.name + "(" + this.id + ")"
                    + " unregistered from weather tower.");
        }
    }
    
    /**
     * Register JetPlane to the tower.
     * 
     * @param weatherTower The object to register to the weather tower.
     */
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;

        // Register JetPlane to the tower.
        this.weatherTower.register(this);
        Application.printWriter.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")"
                + " registered to weather tower.");
    }
}
