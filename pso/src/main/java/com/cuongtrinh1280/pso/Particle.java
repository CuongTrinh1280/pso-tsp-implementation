package com.cuongtrinh1280.pso;

import java.util.ArrayList;

public class Particle {

    // attributions of a particle
    public Route route;
    public Location location;
    public Velocity velocity;
    // pBest
    public Route pBest;
    public Location locationPBest;
    // size
    public static int problemSize;
    public static int maximumIterations = 100;

    /**
     * Initialization attributions to the particle constructor
     *
     * @param route
     */
    public Particle(Route route) {
        this.route = route;
        this.pBest = route;

        location = new Location(new ArrayList<Double>());
        locationPBest = new Location(new ArrayList<Double>());
        velocity = new Velocity(new ArrayList<Double>());

        problemSize = route.cities.size();
    }

    /**
     * Initialization two random cities: {@code city_1}, {@code city_2}
     *
     * @param neighbors_city
     * @return the sequential route {@code neighbors_city} with both cities in the
     *         list
     */
    public Route getNeighborSolution(Route neighbors_city) {
        int random_1 = 0;
        int random_2 = 0;

        while (random_1 == random_2) {
            random_1 = (int) (neighbors_city.cities.size() * Math.random());
            random_2 = (int) (neighbors_city.cities.size() * Math.random());
        }

        City city_1 = neighbors_city.cities.get(random_1);
        City city_2 = neighbors_city.cities.get(random_2);
        neighbors_city.cities.set(random_2, city_1);
        neighbors_city.cities.set(random_1, city_2);
        return neighbors_city;
    }

    /**
     * Get personal best solution for the route
     *
     * @return update pBest
     */
    public void getPersonalBestSolution() {
        Route neighborsRoute = null;
        int i = 0;

        while (i < maximumIterations) {
            neighborsRoute = getNeighborSolution(new Route(this.pBest));
            if (neighborsRoute.getFullRouteDistance() < pBest.getFullRouteDistance())
                pBest = new Route(neighborsRoute);
            i++;
        }
    }

    /**
     * Coefficient is the constant that have been declarations in
     * {@linkplain PSOConstants}
     *
     * @param coefficient
     */
    public void swapWithLocation(int coefficient) {
        for (int i = 0; i < coefficient; i++) {
            int random_1 = 0;
            int random_2 = 0;

            while (random_1 == random_2) {
                random_1 = (int) (pBest.cities.size() * Math.random());
                random_2 = (int) (pBest.cities.size() * Math.random());
            }

            City city_1 = pBest.cities.get(random_1);
            City city_2 = pBest.cities.get(random_2);
            pBest.cities.set(random_2, city_1);
            pBest.cities.set(random_1, city_2);
        }
    }
}
