package com.cuongtrinh1280.pso;

/**
 * Geographical coordinates, latitude and longitude name of the city
 */
public class City {

    public String cityName; // city name
    public double longitude; // longitude
    public double latitude; // latitude

    public City(double longitude, double latitude, String name) {
        this.cityName = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * @param degrees
     * @return radians converted from degrees to use in
     *         {@linkplain #distanceBetweenTwoCities()}
     */
    public double degreesToRad(double degrees) {
        return degrees * (Math.PI / 180D);
    }

    /**
     * @param neighbors_city
     * @return {@code d(this.city, neighbors_city)} by using Haversine formula
     */
    public double distanceBetweenTwoCities(City neighbors_city) {
        double R = 6371; // Radius of the earth
        double dLat = degreesToRad(neighbors_city.latitude - this.latitude);
        double dLng = degreesToRad(neighbors_city.longitude - this.longitude);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(degreesToRad(this.latitude))
                * Math.cos(degreesToRad(neighbors_city.latitude)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // convert to kilometers
        return d;
    }
}
