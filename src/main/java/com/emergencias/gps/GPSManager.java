package com.emergencias.gps;

import java.util.Random;

public class GPSManager {


// Simulador de GPS
public static GPSLocation getGPS() {

    Random rnd = new Random();

    double lat = 40.4 + rnd.nextDouble() * 0.01;   // Simula Madrid
    double lon = -3.7 + rnd.nextDouble() * 0.01;

    double precision = 5 + rnd.nextDouble() * 20;  // precisión aleatoria 5–25m

    return new GPSLocation(lat, lon, precision);
}


}




