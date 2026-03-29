package com.emergencias.util;

import com.emergencias.model.HealthCenter;
import java.util.List;

/**
 * Utilidades para centros de salud.
 */
public class HealthCenterUtils {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public static HealthCenter getCentroMasCercano(double lat, double lon, List<HealthCenter> centers) {

        if (centers == null || centers.isEmpty()) {
            return null;
        }

        HealthCenter closest = null;
        double minDistance = Double.MAX_VALUE;

        for (HealthCenter hc : centers) {

            double distance = haversine(
                    lat, lon,
                    hc.getLatitude(), hc.getLongitude()
            );

            if (distance < minDistance) {
                minDistance = distance;
                closest = hc;
            }
        }

        return closest;
    }

    /**
     * Distancia real sobre la superficie terrestre (km)
     */
    private static double haversine(double lat1, double lon1,
                                    double lat2, double lon2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.pow(Math.sin(dLon / 2), 2)
                * Math.cos(lat1) * Math.cos(lat2);

        double c = 2 * Math.asin(Math.sqrt(a));

        return EARTH_RADIUS_KM * c;
    }
}