package com.cuongtrinh1280.pso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Define attributions and methods for the route of all particles
 */
public class Route {

    /**
     * List of cities in the current routes
     */
    public ArrayList<City> cities = new ArrayList<City>();

    /**
     * Initialization the object route with the lists of cities
     */
    public Route(ArrayList<City> cities) {
        this.cities.addAll(cities);
        Collections.shuffle(this.cities);
    }

    /**
     * Initialization the object route, with each route, add city to the list step
     * by step
     */
    public Route(Route route) {
        for (int i = 0; i < route.cities.size(); i++) {
            cities.add(route.cities.get(i));
        }
    }

    /**
     * Add the distance between current city and the neighbors_city to the route
     *
     * @return {@code total_distance} of the route in class {@linkplain Route}
     */
    public double getFullRouteDistance() {
        double fullDistance = 0;
        for (int i = 0; i < cities.size(); i++) {
            if (i + 1 == cities.size()) {
                fullDistance += cities.get(i - 1).distanceBetweenTwoCities(cities.get(i));
            } else {
                fullDistance += cities.get(i).distanceBetweenTwoCities(cities.get(i + 1));
            }
        }
        fullDistance += cities.get(cities.size() - 1).distanceBetweenTwoCities(cities.get(0));
        return fullDistance;
    }

    /**
     * Print the route: {@code cities_list}
     */
    public String printRoute() {
        String str = "";
        str += "{ ";
        for (City city : cities) {
            str += city.cityName + " ";
        }
        str += " }";
        return str;
    }
}
