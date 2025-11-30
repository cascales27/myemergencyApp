package com.emergencias.controller;

import com.emergencias.auth.UserAccount;
import com.emergencias.history.EmergencyHistoryManager;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.util.GPSService;

public class EmergencyManager {

    public void iniciar(UserAccount usuario, boolean automatico) {

        double[] coordenadas = GPSService.getCoordinates();

        EmergencyRecord record = new EmergencyRecord(
                usuario.getUsername(),
                automatico ? "Emergencia detectada automáticamente" : "Emergencia manual",
                "Ubicación actual detectada por GPS",
                coordenadas[0],
                coordenadas[1],
                usuario.getDatosUsuario().getNombre(),
                usuario.getDatosUsuario().getTelefono(),
                usuario.getDatosUsuario().getContactosConfianza()

        );

        EmergencyHistoryManager history = new EmergencyHistoryManager();
        history.saveHistory(record);

        System.out.println("\n✔ Emergencia enviada");
        System.out.println("Coordenadas: " + coordenadas[0] + ", " + coordenadas[1]);
    }

    public void verHistorial() {
        EmergencyHistoryManager history = new EmergencyHistoryManager();
        var lista = history.loadHistory();

        if (lista.isEmpty()) {
            System.out.println("\nNo hay historial de emergencias.");
            return;
        }

        System.out.println("\n=== HISTORIAL DE EMERGENCIAS ===");
        for (EmergencyRecord r : lista) {
            System.out.println("------------------------------------");
            System.out.println("Usuario: " + r.getUsuario());
            System.out.println("Tipo: " + r.getTipo());
            System.out.println("Ubicación: " + r.getUbicacion());
            System.out.println("Coordenadas: " + r.getLat() + ", " + r.getLng());
            System.out.println("Nombre afectado: " + r.getNombreUsuario());
            System.out.println("Teléfono: " + r.getTelefonoUsuario());
        }
        System.out.println("------------------------------------");
    }
}


