package com.cuongtrinh1280.pso;

import java.util.ArrayList;
import java.util.Collections;

public class PSOOptimization {

    /*
     * Initialization attributions of the PSO optimization
     */
    public ArrayList<Particle> particleList = new ArrayList<Particle>();
    public Route gBest = null; // global best solution for the shortest route
    public Location gBestLocation; // global best solution for locations in the shortest route

    public PSOOptimization(Route route) {
        System.out.println("Initial distance : " + (int) route.getFullRouteDistance() + " km");
        System.out.println(route.printRoute());
        System.out.println();
        System.out.println(
                "================================ Initializing Particles ... =================================");
        System.out.println();

        for (int i = 0; i < PSOConstants.MAX_PARTICLES; i++) {
            Collections.shuffle(route.cities);
            this.particleList.add(new Particle(new Route(route)));
        }
        gBestLocation = new Location(new ArrayList<Double>());
        initializeVelocities();
        initializeLocations();
        sortParticles();
        gBest = new Route(this.particleList.get(0).pBest);
        System.out.println("================================" + PSOConstants.MAX_PARTICLES
                + " Particles are Initialized Uniformly ! =================================");
        System.out.println();
    }

    public void findShortestRoute() {
        System.out.println("================================ Starting process =================================");
        System.out.println();

        double w = 0; // constant in the PSO formula
        int countEpoch = 0; // numbers of epochs

        while (countEpoch < PSOConstants.MAX_ITERATIONS) {
            for (int i = 0; i < this.particleList.size() - 1; i++) {
                this.particleList.get(i).getPersonalBestSolution();
            }
            printParticles();
            sortParticles();
            if (this.particleList.get(0).pBest.getFullRouteDistance() < this.gBest.getFullRouteDistance()) {
                gBest = new Route(this.particleList.get(0).pBest);
            }
            System.out.println("Actual gBest = " + (int) gBest.getFullRouteDistance() + " km");

            w = PSOConstants.high
                    - (((double) countEpoch) / PSOConstants.MAX_ITERATIONS) * (PSOConstants.high - PSOConstants.low);
            System.out.println("Updating Velocities and Locations ...");
            for (int i = 0; i < this.particleList.size() - 1; i++) {
                for (int j = 0; j < Particle.problemSize - 1; j++) {
                    // 2 random factor r1 and r2 in the PSO formula
                    double r1 = PSOConstants.random.nextDouble();
                    double r2 = PSOConstants.random.nextDouble();

                    // Velocity of the current particle
                    double currentVelocity = this.particleList.get(i).velocity.getVelocity().get(j);
                    // Location of the current particle
                    double currentLocation = this.particleList.get(i).location.getLocation().get(j);
                    // Position of pBest
                    double pBestLoc = this.particleList.get(i).locationPBest.getLocation().get(j);
                    // Position of gBest
                    double gBestLoc = this.gBestLocation.getLocation().get(j);
                    // Calculate new value of the velocity in the next position
                    double newVelocity = (w * currentVelocity) + (r1 * PSOConstants.c1) * (pBestLoc - currentLocation)
                            + (r2 * PSOConstants.c2) * (gBestLoc - currentLocation);
                    // Update velocity
                    this.particleList.get(i).location.locationList.set(j, newVelocity);
                    // Update new position of the particle
                    double newLocation = currentLocation + newVelocity;
                    this.particleList.get(i).location.locationList.set(j, newLocation);
                    this.particleList.get(i).swapWithLocation((int) Math.abs(currentLocation - newLocation));
                }
            }
            System.out.println("Epoch " + countEpoch + " has been finished.");
            countEpoch++;
        }
        System.out.println("================================ Process Finished =================================");
        System.out.println();
        System.out.println("Approached Global minimum: " + (int) this.gBest.getFullRouteDistance());
        System.out.println("Route: " + this.gBest.printRoute());
    }

    /**
     * Initialization velocity list for the particles system
     */
    public void initializeVelocities() {
        for (Particle particle : this.particleList) {
            for (int i = 0; i < Particle.problemSize - 1; i++) {
                particle.velocity.velocityDimensions.add(PSOConstants.random.nextDouble() * 2.0 - 1.0);
            }
        }
    }

    /**
     * Initialization location list for the particles system
     */
    public void initializeLocations() {
        for (Particle particle : this.particleList) {
            for (int i = 0; i < Particle.problemSize - 1; i++) {
                particle.location.locationList.add((double) i);
                particle.locationPBest.locationList.add((double) i);
            }
        }

        for (int i = 0; i < Particle.problemSize - 1; i++) {
            gBestLocation.getLocation().add((double) i);
        }
    }

    /**
     * Sort all particles to an ascended list of {@code getFullRouteDistance()} in
     * {@linkplain Route}
     */
    public void sortParticles() {
        int n = this.particleList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (this.particleList.get(j).pBest.getFullRouteDistance() > this.particleList.get(j + 1).pBest
                        .getFullRouteDistance()) {
                    Particle tempParticle = this.particleList.get(j);
                    this.particleList.set(j, this.particleList.get(j + 1));
                    this.particleList.set(j + 1, tempParticle);
                }
            }
        }
    }

    public void printParticles() {
        for (int i = 0; i < this.particleList.size() - 1; i++) {
            System.out.println("Particle:" + i);
            for (int j = 0; j < Particle.problemSize - 1; j++) {
                System.out.println("Position :" + this.particleList.get(i).location.getLocation().get(j)
                        + ", Velocity :" + this.particleList.get(i).velocity.getVelocity().get(j));
                System.out.println();
            }
        }
    }
}
