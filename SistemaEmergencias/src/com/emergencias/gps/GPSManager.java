package com.emergencias.gps;

public class GPSManager {

    /**
     * Devuelve GPS real si lo tienes implementado.
     * Si NO tienes GPS real (API, geocoding, etc.), devuelve null.
     */
    public static GPSLocation getGPSOrNull(String ubicacion) {
        // Por ahora NO inventamos coordenadas:
        return null;
    }
}