package com.emergencias.loader;

import com.emergencias.model.HealthCenter;
import com.google.gson.Gson;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HealthCenterLoader {

    // --- Clases internas para mapear GeoJSON ---
    private static class FeatureCollection {
        List<Feature> features;
    }
    private static class Feature {
        Properties properties;
        Geometry geometry;
    }
    private static class Properties {
        String MU_NOMBRE;          // municipio
        String CS_DENOMINACION;    // nombre centro
        String CS_TIPO;            // tipo
    }
    private static class Geometry {
        String type;
        List<Double> coordinates;  // [lon, lat]
    }

    public static ArrayList<HealthCenter> load(String path) {
        ArrayList<HealthCenter> out = new ArrayList<>();
        try {
            System.out.println("JSON absoluto: " + new java.io.File(path).getAbsolutePath());
            Gson gson = new Gson();
            FeatureCollection fc = gson.fromJson(new FileReader(path), FeatureCollection.class);
            if (fc == null || fc.features == null) return out;

            for (Feature f : fc.features) {
                if (f == null || f.properties == null || f.geometry == null || f.geometry.coordinates == null) continue;
                if (f.geometry.coordinates.size() < 2) continue;

                // OJO: GeoJSON = [lon, lat]
                double lon = f.geometry.coordinates.get(0);
                double lat = f.geometry.coordinates.get(1);

                String municipio = safe(f.properties.MU_NOMBRE);
                String nombre = safe(f.properties.CS_DENOMINACION);
                String tipo = safe(f.properties.CS_TIPO);

                out.add(new HealthCenter(nombre, municipio, tipo, lat, lon));
            }

        } catch (Exception e) {
            System.out.println("Error cargando JSON: " + e.getMessage());
        }
        return out;
    }

    private static String safe(String s) {
        return (s == null) ? "" : s.trim();
    }
}