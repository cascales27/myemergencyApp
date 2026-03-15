package com.emergencias.util;

import com.emergencias.model.HealthCenter;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HealthCenterLoader {

    /**
     * Carga los centros de salud desde el recurso dentro de bin/resources
     * @return lista de HealthCenter
     */
    public static List<HealthCenter> loadFromResource() {
        List<HealthCenter> centers = new ArrayList<>();
        try (InputStream is = HealthCenterLoader.class.getClassLoader().getResourceAsStream("resources/health_centers.json")) {
            if (is == null) {
                System.out.println("❌ No se ha encontrado el recurso: health_centers.json");
                return centers;
            }

            Gson gson = new Gson();
            Map<String, Object> jsonMap = gson.fromJson(new InputStreamReader(is), Map.class);
            List<Map<String, Object>> features = (List<Map<String, Object>>) jsonMap.get("features");

            for (Map<String, Object> feature : features) {
                Map<String, Object> properties = (Map<String, Object>) feature.get("properties");
                Map<String, Object> geometry = (Map<String, Object>) feature.get("geometry");

                HealthCenter hc = new HealthCenter();
                hc.setMU_NOMBRE((String) properties.get("MU_NOMBRE"));
                hc.setDenominacion((String) properties.get("CS_DENOMINACION"));
                hc.setTipo((String) properties.get("CS_TIPO"));

                HealthCenter.Geometry geo = new HealthCenter.Geometry();
                geo.setType((String) geometry.get("type"));
                List<Double> coords = (List<Double>) geometry.get("coordinates");
                geo.setCoordinates(new double[]{coords.get(0), coords.get(1)});
                hc.setGeometry(geo);

                centers.add(hc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return centers;
    }
}



