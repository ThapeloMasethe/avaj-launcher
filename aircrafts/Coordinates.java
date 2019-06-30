package aircrafts;

/**
 * Coordinates Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-20
 * @version 1.0
 */
public class Coordinates {
    private int  longitude;
    private int  latitude;
    private int  height;

    /**
     * Coordinates Constructor, use it as as setter.
     * 
     * @param longitude The coordinate that specifies the east–west position of the aircraft.
     * @param latitude The coordinate that specifies the north–south position of the aircraft.
     * @param height The coordinate the specifies the position of the aircraft above the ground.
     */
    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;

        /**
         *  The range of the height is 0-100, if an aircraft needs to pass the upper
         * limit height, it remains at 100.
         */
        if (height > 100) {
            this.height =   100;
        } else if (height < 0) {
            this.height = 0;
        } 
        else {
            this.height = height;
        }
    }

    /**
     * GETTER: {@code longitude} accessor.
     * 
     * @return  the value of {@code longitude}.
     */
    public int  getLongitude() {
        return this.longitude;
    }

    /**
     * GETTER: {@code latitude} accessor.
     * 
     * @return  the value of {@code latitude}.
     */
    public int  getLatitude() {
        return this.latitude;
    }

    /**
     * GETTER: {@code height} accesor.
     * 
     * @return  the value of {@code height}.
     */
    public int getHeight() {
        return this.height;
    }
}
