package com.cuongtrinh1280.pso;

import java.util.ArrayList;

/**
 * Define attributions and methods for the location of the particle
 */
public class Location {
    public ArrayList<Double> locationList;

    /**
     * Constructor to build the location list to store the exchange location
     * information
     *
     * @param locationList
     */
    public Location(ArrayList<Double> locationList) {
        this.locationList = new ArrayList<Double>();
        this.locationList = locationList;
    }

    public ArrayList<Double> getLocation() {
        return this.locationList;
    }

    public void updateLocation(ArrayList<Double> update) {
        this.locationList.addAll(update);
    }
}
