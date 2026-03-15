package com.emergencias.controller;

import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.model.UserData;
import com.emergencias.gps.GPSLocation;
import com.emergencias.history.EmergencyHistoryManager;

import java.util.List;
import java.util.ArrayList;

public class EmergencyManager {

    private EmergencyHistoryManager historyManager;

    public EmergencyManager() {
        historyManager = new EmergencyHistoryManager();
    }

    /**
     * Activa una emergencia manual.
     *
     * @param tipo    Tipo de emergencia
     * @param ubicacion Ubicación descriptiva
     * @param usuario Usuario afectado
     * @return evento generado
     */
    public EmergencyEvent activarEmergenciaManual(String tipo, String ubicacion, UserData usuario) {

        // Generar coordenadas simuladas
        GPSLocation coordenadas = new GPSLocation(
                40 + Math.random() * 2,   // Latitud simulada
                -3 + Math.random() * 2,   // Longitud simulada
                5.0                        // Precisión simulada ±5m
        );

        System.out.println("🚨 Emergencia enviada");
        System.out.println("Coordenadas: " + coordenadas.getLatitud() + ", " + coordenadas.getLongitud());

        EmergencyEvent evento = new EmergencyEvent(
                tipo,
                ubicacion,
                coordenadas,
                usuario
        );

        // Guardar en historial
        EmergencyRecord record = new EmergencyRecord(
                usuario.getNombre(),
                tipo,
                ubicacion,
                coordenadas.getLatitud(),
                coordenadas.getLongitud(),
                usuario.getNombre(),
                usuario.getTelefono(),
                usuario.getContactos() != null ? usuario.getContactos() : new ArrayList<>()
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
     *
     * @param tipo Tipo de emergencia
     */
    public void mostrarTutorial(String tipo) {
        // Para simplificar, usamos tutoriales en consola
        System.out.println("\n=== TUTORIAL DE PRIMEROS AUXILIOS ===");
        switch (tipo.toLowerCase()) {
            case "caida":
                System.out.println("Si la persona ha sufrido una caída: Revisar respiración y llamar a emergencias.");
                break;
            case "atragantamiento":
                System.out.println("Si la persona se atraganta: Aplicar maniobra de Heimlich.");
                break;
            default:
                System.out.println("Tutorial no disponible para este tipo de emergencia.");
        }
        System.out.println();
    }
}




