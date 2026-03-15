package com.emergencias.util;

import com.emergencias.model.HealthCenter;
import java.util.List;

public class HealthCenterUtils {

    /**
     * Calcula el centro de salud más cercano a las coordenadas dadas
     *
     * @param userLat Latitud del usuario
     * @param userLng Longitud del usuario
     * @param centros Lista de centros de salud
     * @return Centro de salud más cercano o null si la lista está vacía
     */
    public static HealthCenter getCentroMasCercano(double userLat, double userLng, List<HealthCenter> centros) {
        if (centros == null || centros.isEmpty()) {
            return null;
        }

        HealthCenter nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (HealthCenter hc : centros) {
            double lat = hc.getGeometry().getCoordinates()[0];
            double lng = hc.getGeometry().getCoordinates()[1];
            double distance = haversine(userLat, userLng, lat, lng);

            if (distance < minDistance) {
                minDistance = distance;
                nearest = hc;
            }
        }

        return nearest;
    }

    /**
     * Fórmula de Haversine para calcular distancia en kilómetros entre dos puntos
     */
    private static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la Tierra en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
