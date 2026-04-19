package com.emergencias.controller;

import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.model.UserData;
import com.emergencias.gps.GPSLocation;
import com.emergencias.history.EmergencyHistoryManager;
import com.emergencias.database.EmergencyDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmergencyManager {

    private EmergencyHistoryManager historyManager;
    private EmergencyDAO emergencyDAO;

    public EmergencyManager() {
        historyManager = new EmergencyHistoryManager();
        emergencyDAO = new EmergencyDAO(); // ✅ conexión a BD
    }

    /**
     * Activa una emergencia manual.
     */
    public EmergencyEvent activarEmergenciaManual(
            String tipo,
            String ubicacion,
            UserData usuario,
            GPSLocation coordenadas
    ) {

        System.out.println("🚨 Emergencia enviada");
        System.out.println("Coordenadas: " +
                coordenadas.getLatitud() + ", " +
                coordenadas.getLongitud());

        EmergencyEvent evento = new EmergencyEvent(
                tipo,
                ubicacion,
                coordenadas,
                usuario
        );

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String fecha = LocalDateTime.now().format(formatter);

        EmergencyRecord record = new EmergencyRecord(
                usuario.getUsername(),
                tipo,
                ubicacion,
                coordenadas.getLatitud(),
                coordenadas.getLongitud(),
                usuario.getNombre(),
                usuario.getTelefono(),
                usuario.getContactos(),
                fecha
        );

        // 🟢 1. Guardar en historial local (UI)
        historyManager.saveHistory(record);

        // 🔵 2. Guardar en MySQL (ESTO FALTABA)
        emergencyDAO.insertEmergency(evento);

        return evento;
    }

    public List<EmergencyRecord> getHistorial() {
        return historyManager.loadHistory();
    }

    public void mostrarTutorial(String tipo) {
        System.out.println("\n=== TUTORIAL DE PRIMEROS AUXILIOS ===");

        switch (tipo.toLowerCase()) {
            case "caida":
                System.out.println("Revisar respiración y estado general.");
                break;
            case "atragantamiento":
                System.out.println("Aplicar maniobra de Heimlich.");
                break;
            default:
                System.out.println("Tutorial no disponible.");
        }

        System.out.println();
    }
}