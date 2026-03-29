package com.emergencias.main;

import com.emergencias.model.HealthCenter;
import com.emergencias.util.HealthCenterLoader;

import java.util.List;

public class TestHealthCenters {

    public static void main(String[] args) {
        // Carga de centros de salud usando el nuevo método de recurso
        List<HealthCenter> centers = HealthCenterLoader.loadFromFile();

        // Mensaje de depuración
        System.out.println("✅ Centros de salud cargados: " + centers.size());

        // Listado completo de centros para comprobar
        for (HealthCenter hc : centers) {
            System.out.println("------------------------------------");
            System.out.println("Nombre: " + hc.getMU_NOMBRE());
            System.out.println("Denominación: " + hc.getDenominacion());
            System.out.println("Tipo: " + hc.getTipo());
            System.out.println("Coordenadas: " + hc.getGeometry().getCoordinates()[0] + ", " +
                               hc.getGeometry().getCoordinates()[1]);
        }
    }
}

