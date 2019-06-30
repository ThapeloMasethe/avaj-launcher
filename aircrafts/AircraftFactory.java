package aircrafts;

/**
 * AircraftFactory Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-20
 * @version 1.0
 */
public class AircraftFactory {

    /**
     * Create new aircraft based on the type, if type is unknown returns null
     * and no aircraft is going to be created.
     * 
     * @param type The type of the aircarft e.g Helicopter, Baloon or JetPlane.
     * @param name The name of the aircraft.
     * @param longitude The coordinate that specifies the east–west position of the aircraft.
     * @param latitude The coordinate that specifies the north–south position of the aircraft.
     * @param height The coordinate the specifies the position of the aircraft above the ground.
     * @return the new aircraft object depending on the {@code type}, otherwise returns {@code null}.
     */
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if (type.toLowerCase().equals("jetplane")) {
            // Creates new Jeplane.
            return new JetPlane(name, coordinates);
        } else if (type.toLowerCase().equals("helicopter")) {
            // Creates new Helicopter.
            return new Helicopter(name, coordinates);
        } else if (type.toLowerCase().equals("baloon")) {
            // Creates new Baloon.
            return new Baloon(name, coordinates);
        }
        return null;
    }
}