package za.co.tmasethe.model;

import lombok.Getter;

@Getter
public class Coordinates {

    private final int longitude;
    private final int latitude;
    private final int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;

        // The range of the height is 0-100, if an aircraft needs to pass the upper limit height, it remains at 100.
        if (height > 100) {
            this.height = 100;
        } else {
            this.height = Math.max(height, 0);
        }
    }

}
