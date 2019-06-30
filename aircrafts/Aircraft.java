package aircrafts;

/**
 * Aircraft Class
 * 
 * @author  Thapelo Masethe
 * @version 1.0
 * @since   2019-06-20
 */
public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;
    
    /**
     * Use this constructor to set values.
     * 
     * @param name The name of the aircraft.
     * @param coordinates The coordinates of the aircraft.
     */
    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = this.nextId();
    }

    /**
     * Creates the aircaft ID.
     *
     * @return id of the aircraft.
     */
    private long nextId() {
        return ++Aircraft.idCounter;
    }
}