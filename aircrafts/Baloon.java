package aircrafts;

import  weather.WeatherTower;
import  simulator.Simulator;

/**
 * Baloon Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-20
 * @version 1.0
 */
public class Baloon extends Aircraft implements Flyable {
    WeatherTower weatherTower;

    /**
     * Baloon constructor
     * 
     * @param name The name of the Baloon.
     * @param coordinates The coordinates of the Baloon.
     */
    Baloon(String name, Coordinates coordinates) {
        // Invoke Aircraft constructor.
        super(name, coordinates);
    }

    /**
     * Upadate the conditions based on the current weather.
     */
    public void updateConditions() {
        String weatherCondtion  =   weatherTower.getWeather(this.coordinates);
      
        if (weatherCondtion.equals("SUN")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 2,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() + 4
            );
            Simulator.printWriter.println("Baloon#" + this.name + "(" + this.id + "): "
            + "Let's enjoy the good weather and take some pics");
        } else if (weatherCondtion.equals("RAIN")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 0,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() - 5
            );
            Simulator.printWriter.println("Baloon#" + this.name + "(" + this.id + "): " 
            + "Damn you rain! You messed up my baloon.");
        } else if (weatherCondtion.equals("FOG")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 0,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() - 3
            );
            Simulator.printWriter.println("Baloon#" + this.name + "(" + this.id + "): " 
            + "It's foggy, I cant see for real man!");
        } else if (weatherCondtion.equals("SNOW")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 0,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() - 15
            );
            Simulator.printWriter.println("Baloon#" + this.name + "(" + this.id + "): " 
            + "It's snowing. We're gonna crash");
        }
        /**
         * If height is Zero, the Helicopter lands, unregisters from the weather tower
         * and logs its current coordinates
         */
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            Simulator.printWriter.println("Baloon#" + this.name + "(" + this.id + ") landing.");
            Simulator.printWriter.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" 
        + " unregistered from weather tower.");
        }
    }
    
    /**
     * Regiter the Baloon to the tower.
     *  
     * @param weatherTower The object to register to the weather tower.
     */
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;

        // Register Baloon to the tower.
        this.weatherTower.register(this);
        Simulator.printWriter.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" 
        + " registered to weather tower.");
    }
}