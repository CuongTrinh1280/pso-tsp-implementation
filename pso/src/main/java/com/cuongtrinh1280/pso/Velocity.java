package com.cuongtrinh1280.pso;

import java.util.ArrayList;

/**
 * Define attributions and methods for the velocity of the particle
 */
public class Velocity {
    public ArrayList<Double> velocityDimensions;

    /**
     * Constructor to build the velocity list to store the evolution velocity in
     * each step
     *
     * @param dimensions
     */
    public Velocity(ArrayList<Double> dimensions) {
        this.velocityDimensions = new ArrayList<Double>();
        this.velocityDimensions = dimensions;
    }

    public ArrayList<Double> getVelocity() {
        return this.velocityDimensions;
    }

    public void updateVelocity(ArrayList<Double> update) {
        this.velocityDimensions.addAll(update);
    }
}
