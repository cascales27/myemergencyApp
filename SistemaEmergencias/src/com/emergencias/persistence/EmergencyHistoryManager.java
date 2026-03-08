package com.emergencias.persistence;

import com.emergencias.model.EmergencyEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class EmergencyHistoryManager {

    private static final String FILE_PATH = "src/main/resources/emergency_history.json";

    public static void guardarEmergencia(EmergencyEvent evento) {

        try {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Map<String, Object> registro = new HashMap<>();

            registro.put("tipo", evento.getTipo());
            registro.put("ubicacion", evento.getUbicacion());
            registro.put("fecha", evento.getFechaHora().toString());
            registro.put("latitud", evento.getGps().getLatitud());
            registro.put("longitud", evento.getGps().getLongitud());

            FileWriter writer = new FileWriter(FILE_PATH, true);
            gson.toJson(registro, writer);
            writer.write("\n");
            writer.close();

            System.out.println("📁 Emergencia guardada en historial.");

        } catch (Exception e) {
            System.out.println("Error guardando historial: " + e.getMessage());
        }
    }
}
