package com.emergencias.history;

import com.emergencias.model.EmergencyRecord;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class EmergencyHistoryViewer {

    private static final String HISTORIAL_FILE = "src/main/resources/emergency_history.json";

    public static ArrayList<EmergencyRecord> cargarHistorial() {
        ArrayList<EmergencyRecord> historial = new ArrayList<>();
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(HISTORIAL_FILE);
            historial = gson.fromJson(reader, new TypeToken<ArrayList<EmergencyRecord>>(){}.getType());
            reader.close();
        } catch (Exception e) {
            System.out.println("No se pudo cargar el historial: " + e.getMessage());
        }
        return (historial != null) ? historial : new ArrayList<>();
    }

    public static void guardarHistorial(EmergencyRecord registro) {
        try {
            ArrayList<EmergencyRecord> historial = cargarHistorial();
            historial.add(registro);

            Gson gson = new Gson();
            FileWriter writer = new FileWriter(HISTORIAL_FILE);
            gson.toJson(historial, writer);
            writer.flush();
            writer.close();

            System.out.println("? Emergencia guardada en historial.");
        } catch (Exception e) {
            System.out.println("Error guardando historial: " + e.getMessage());
        }
    }

    public static void mostrarHistorial() {
        ArrayList<EmergencyRecord> historial = cargarHistorial();
        if (historial.isEmpty()) {
            System.out.println("No hay emergencias registradas.");
        } else {
            System.out.println("=== HISTORIAL DE EMERGENCIAS ===");
            for (EmergencyRecord rec : historial) {
                System.out.println(rec);
            }
        }
    }
}
