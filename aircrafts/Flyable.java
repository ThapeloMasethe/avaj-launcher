package aircrafts;

import weather.*;

/**
 * Flyable Interface
 * 
 * @author  Thapelo Masethe
 * @since   2019-20-06
 * @version 1.0
 */
public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}
