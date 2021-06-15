package com.cuongtrinh1280.pso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class PsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsoApplication.class, args);
        ArrayList<City> cities = new ArrayList<City>();

        try {
            Scanner input = new Scanner(new File(
                    "E:/FileHistory/LENOVO/DESKTOP-R7LTVM7/Data/C/Users/LENOVO/pso-for-tsp/pso/src/main/resources/cities-list.txt"));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] line_array = line.split(",");
                cities.add(
                        new City(Double.parseDouble(line_array[0]), Double.parseDouble(line_array[1]), line_array[2]));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "!");
        }

        Route route = new Route(cities);
        PSOOptimization pso = new PSOOptimization(route);

        long startTime = System.nanoTime();
        pso.findShortestRoute();
        long endTime = System.nanoTime();
        System.out.println();
        System.out.println("================================ Execution time =================================");
        System.out.println("Your algorithms runs: " + (endTime - startTime) / 1000000000.0 + "s");
    }
}
