package com.emergencias.gps;

public class GPSLocation {


private double latitud;
private double longitud;
private double precision;

public GPSLocation(double latitud, double longitud, double precision) {
    this.latitud = latitud;
    this.longitud = longitud;
    this.precision = precision;
}

public double getLatitud() {
    return latitud;
}

public double getLongitud() {
    return longitud;
}

public double getPrecision() {
    return precision;
}

@Override
public String toString() {
    return "Lat: " + latitud + ", Lon: " + longitud + " (±" + precision + "m)";
}


}









