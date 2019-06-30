package aircrafts;

import simulator.Simulator;
import weather.*;

/**
 * JetPlane Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-20
 * @version 1.0
 */
public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    /**
     * JetPlane Constructor
     * 
     * @param name The name of the Jetplane.
     * @param coordinates The coordinates of the Jetplane.
     */
    JetPlane(String name, Coordinates coordinates) {
        // Invoke Aircraft Constructor.
        super(name, coordinates);
    }

    /**
     * Update the conditons based on the current weather condition.
     */
    public void updateConditions() {
        String weatherCondition  =   weatherTower.getWeather(this.coordinates);

        if (weatherCondition.equals("SUN")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 10,
                coordinates.getLatitude() + 0,
                coordinates.getHeight() + 2
            );
            Simulator.printWriter.println("JetPlane#" + this.name + "(" + this.id + "): "
            + "Let's enjoy the good weather and take some pics");
        } else if (weatherCondition.equals("RAIN")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 0,
                coordinates.getLatitude() + 5,
                coordinates.getHeight() + 0
            );
            Simulator.printWriter.println("JetPlane#" + this.name + "(" + this.id + "): "
            + "It's raining. Better watch out for lightings.");
        } else if (weatherCondition.equals("FOG")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 0,
                coordinates.getLatitude() + 1,
                coordinates.getHeight() + 0
            );
            Simulator.printWriter.println("JetPlane#" + this.name + "(" + this.id + "): "
            + "OMG! Winter is coming!");
        } else if (weatherCondition.equals("SNOW")) {
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 0,
                coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 7
            );
            Simulator.printWriter.println("JetPlane#" + this.name + "(" + this.id + "): " 
            + "It's snowing. We're gonna crash");
        }
        /**
         * If height is Zero, the Helicopter lands, unregisters from the weather tower
         * and logs its current coordinates.
         */
        if (this.coordinates.getHeight() == 0) {
            this.weatherTower.unregister(this);
            Simulator.printWriter.println("JetPlane#" + this.name + "(" + this.id + ") landing.");
            Simulator.printWriter.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" 
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
        Simulator.printWriter.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")"
        + " registered to weather tower.");
    }
}