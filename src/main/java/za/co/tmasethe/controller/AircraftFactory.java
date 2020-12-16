package za.co.tmasethe.controller;

import za.co.tmasethe.model.Coordinates;
import za.co.tmasethe.service.Flyable;
import za.co.tmasethe.model.Aircraft;

public class AircraftFactory {

    /**
     * Create new aircraft based on the type, if type is unknown returns null
     * and no aircraft is going to be created.
     * 
     * @param type The type of the aircraft enums.g Helicopter, Balloon or JetPlane.
     * @param name The name of the aircraft.
     * @param longitude The coordinate that specifies the east–west position of the aircraft.
     * @param latitude The coordinate that specifies the north–south position of the aircraft.
     * @param height The coordinate the specifies the position of the aircraft above the ground.
     * @return the new aircraft object depending on the {@code type}, otherwise returns {@code null}.
     */
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type.toLowerCase()) {
            case Aircraft.JET_PLANE:
                return new JetPlane(name, coordinates);
            case Aircraft.HELICOPTER:
                return new Helicopter(name, coordinates);
            case Aircraft.BALLOON:
                return new Balloon(name, coordinates);
            default:
                return null;
        }
    }
}
