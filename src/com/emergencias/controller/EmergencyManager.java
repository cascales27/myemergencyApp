package com.emergencias.controller;

import com.emergencias.auth.UserAccount;
import com.emergencias.history.EmergencyHistoryManager;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.util.GPSService;

public class EmergencyManager {

    private EmergencyHistoryManager historyManager = new EmergencyHistoryManager();

    public void iniciarEmergencia(UserAccount usuario, boolean automatica) {

        double[] coords = GPSService.getCoordinates();

        String tipo = automatica
                ? "Emergencia detectada automáticamente"
                : "Emergencia activada manualmente";

        String estado = automatica
                ? "ENVIADA_AUTOMATICA"
                : "ENVIADA_MANUAL";

        EmergencyRecord record = new EmergencyRecord(
                usuario.getUsername(),
                tipo,
                "Ubicación actual detectada por GPS",
                coords[0],
                coords[1],
                usuario.getDatosUsuario().getNombre(),
                usuario.getDatosUsuario().getTelefono(),
                usuario.getDatosUsuario().getContactosConfianza(),
                estado
        );

        historyManager.saveHistory(record);

        System.out.println("? Emergencia enviada");
        System.out.println("Coordenadas: " + coords[0] + ", " + coords[1]);
    }

    public void mostrarHistorial() {
        historyManager.loadHistory().forEach(e -> {
            System.out.println("------------------------------------");
            System.out.println("Usuario: " + e.getUsuario());
            System.out.println("Tipo: " + e.getTipo());
            System.out.println("Estado: " + e.getEstado());
            System.out.println("Ubicación: " + e.getUbicacion());
            System.out.println("Coordenadas: " + e.getLat() + ", " + e.getLng());
            System.out.println("Nombre afectado: " + e.getNombreUsuario());
            System.out.println("Teléfono: " + e.getTelefonoUsuario());
        });
        System.out.println("------------------------------------");
    }
}










