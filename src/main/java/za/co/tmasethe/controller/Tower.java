package za.co.tmasethe.controller;


import za.co.tmasethe.service.Flyable;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Tower {

    private final List<Flyable> observers = new ArrayList<>();

    /**
     * Register flyable.
     * 
     * @param flyable The flyable to be registered
     */
    public void register(Flyable flyable) {
        // Add flyable to observers.
        if (!observers.contains(flyable)) {
            observers.add(flyable);
        }
    }

    /**
     * Unregister flyable.
     * 
     * @param   flyable The flyable to be unregistered
     */
    public void unregister(Flyable flyable) {
        // Remove flyable from observers.
        observers.remove(flyable);
    }

    // Update conditions of every registered flyable.
    protected void conditionsChanged() throws NoSuchAlgorithmException {
        for (Flyable observer : observers) {
            observer.updateConditions();
        }
    }
}
