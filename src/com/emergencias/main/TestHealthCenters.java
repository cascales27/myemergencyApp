package com.emergencias.main;

import com.emergencias.model.HealthCenter;
import com.emergencias.util.HealthCenterLoader;
import java.util.List;

public class TestHealthCenters {

    public static void main(String[] args) {

        String path = "src/main/resources/health_centers.json";
        List<HealthCenter> centers = HealthCenterLoader.loadFromFile(path);

        for (HealthCenter hc : centers) {
            double[] coords = hc.getGeometry().getCoordinates();
            System.out.println(hc.getMU_NOMBRE() + " | " + hc.getDenominacion() +
                               " | Tipo: " + hc.getTipo() +
                               " | Lat: " + coords[1] + " | Lon: " + coords[0]);
        }
    }
}

