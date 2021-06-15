package com.cuongtrinh1280.pso;

/*
* Const declarations for the whole package com.cuongtrinh1280/pso;
*/
import java.util.Random;

public class PSOConstants {
    public static int MAX_PARTICLES = 40;
    public static int MAX_ITERATIONS = 20;

    public static double c1 = 2, c2 = 2; // Self and swarm confident factor
    public static double low = 0.0, high = 1.0; // Inertia factor range (coefficient range)
    public static Random random = new Random();
}
