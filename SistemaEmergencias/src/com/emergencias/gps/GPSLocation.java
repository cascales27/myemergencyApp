package com.emergencias.gps;

public class GPSLocation {

    private double latitud;
    private double longitud;

    public GPSLocation(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return "Lat: " + latitud + ", Lon: " + longitud;
    }
}


