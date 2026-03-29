package com.emergencias.controller;

import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.model.UserData;
import com.emergencias.gps.GPSLocation;
import com.emergencias.history.EmergencyHistoryManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmergencyManager {

    private EmergencyHistoryManager historyManager;

    public EmergencyManager() {
        historyManager = new EmergencyHistoryManager();
    }

    /**
     * Activa una emergencia manual.
     *
     * IMPORTANTE:
     * Ahora recibe coordenadas reales desde el GPS.
     */
    public EmergencyEvent activarEmergenciaManual(
            String tipo,
            String ubicacion,
            UserData usuario,
            GPSLocation coordenadas
    ) {

        // 🚨 DEBUG
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

        // 📅 Fecha con formato humano
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

        historyManager.saveHistory(record);

        return evento;
    }

    /**
     * Obtiene el historial completo de emergencias.
     */
    public List<EmergencyRecord> getHistorial() {
        return historyManager.loadHistory();
    }

    /**
     * Muestra un tutorial básico para un tipo de emergencia.
     */
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