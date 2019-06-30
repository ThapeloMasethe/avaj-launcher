package weather;

import aircrafts.Flyable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tower Class
 * 
 * @author  Thapelo Masethe
 * @since   2016-06-20
 * @version 1.0
 */
public class Tower {
    // Store flayables in the observers arraylist.
    private List<Flyable> observers = new ArrayList<Flyable>();

    /**
     * Register flyable.
     * 
     * @param flyable The flaybele to be registered
     */
    public void register(Flyable flyable) {
        // Add flyable to observers.
        if (!observers.contains(flyable)) {
            observers.add(flyable);
        }
    }

    /**
     * Unregister flayable.
     * 
     * @param   flyable The flaybale to be unregistered
     */
    public void unregister(Flyable flyable) {
        // Remove flyable from observers.
        observers.remove(flyable);
    }

    // Update conditions of every registered flayable.
    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }
}
