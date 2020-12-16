package za.co.tmasethe.model;

import lombok.Getter;

@Getter
public enum Weather {

    RAIN(0, "RAIN"),
    FOG(1, "FOG"),
    SUN(2, "SUN"),
    SNOW(3, "SNOW");

    private final int index;
    private final String type;

    Weather(int index, String type) {
        this.index = index;
        this.type = type;
    }

    public static String getWeather(int index) {
        for (Weather weather : Weather.values()) {
            if (weather.index == index) {
                return weather.getType();
            }
        }
        return getWeather(0);
    }
}
