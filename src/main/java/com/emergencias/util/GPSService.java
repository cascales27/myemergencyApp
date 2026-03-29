package com.emergencias.util;

public class GPSService {


public static double[] getCoordinates() {

    // Simulación de coordenadas dentro de Madrid
    double lat = 40.40 + Math.random() * 0.02;
    double lng = -3.70 + Math.random() * 0.02;

    return new double[]{lat, lng};
}


}
