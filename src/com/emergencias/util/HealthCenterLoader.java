package com.emergencias.util;

import com.emergencias.model.HealthCenter;
import com.google.gson.Gson;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HealthCenterLoader {

    public static List<HealthCenter> loadFromFile(String path) {
        List<HealthCenter> centers = new ArrayList<>();
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            Map<String, Object> jsonMap = gson.fromJson(reader, Map.class);
            List<Map<String, Object>> features = (List<Map<String, Object>>) jsonMap.get("features");

            for (Map<String, Object> feature : features) {
                Map<String, Object> properties = (Map<String, Object>) feature.get("properties");
                Map<String, Object> geometry = (Map<String, Object>) feature.get("geometry");

                HealthCenter hc = new HealthCenter();

                // Propiedades
                hc.setMU_NOMBRE((String) properties.get("MU_NOMBRE"));
                hc.setDenominacion((String) properties.get("CS_DENOMINACION"));
                hc.setTipo((String) properties.get("CS_TIPO"));

                // Geometry
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



