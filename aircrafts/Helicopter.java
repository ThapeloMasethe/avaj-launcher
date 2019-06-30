package aircrafts;

import weather.WeatherTower;
import simulator.Simulator;

/**
 * Helicopter Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-20
 * @version 1.0
 */
public class Helicopter extends Aircraft implements Flyable {
    WeatherTower weatherTower;

    /**
     * Helicopter Constructor
     * 
     * @param name The name of the Helicopter.
     * @param coordinates The coordinates of the Helicopter.
     */
    Helicopter(String name, Coordinates coordinates) {
        // Invoke Aircraft Constructor.
        super(name, coordinates);
    }

    /**
     * Update the conditions based on the current weather.
     */
    public void updateConditions() {
        String weatherType  =   weatherTower.getWeather(this.coordinates);

        if (weatherType.equals("SUN")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 10,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() + 2
            );
            Simulator.printWriter.println("Helicopter#" + this.name + "(" + this.id + "): " 
            + "This is hot.");
        } else if (weatherType.equals("RAIN")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 5,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() + 0
            );
            Simulator.printWriter.println("Helicopter#" + this.name + "(" + this.id + "): " 
            + "It's raining. Better watch out for lightings.");
        } else if (weatherType.equals("FOG")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 1,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() + 0
            );
            Simulator.printWriter.println("Helicopter#" + this.name + "(" + this.id + "): " 
            + "My rotor is going to freeze!");
        } else if (weatherType.equals("SNOW")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 0,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() - 12
            );
            Simulator.printWriter.println("Helicopter#" + this.name + "(" + this.id + "): " 
            + "It's snowing. We're gonna crash");
        }
        /**
         * If height is Zero, the Helicopter lands, unregisters from the weather tower
         * and logs its current coordinates.
         */
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            Simulator.printWriter.println("Helicopter#" + this.name + "(" + this.id + ") landing.");
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
        Simulator.printWriter.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")"
        + " registered to weather tower.");
    }
}