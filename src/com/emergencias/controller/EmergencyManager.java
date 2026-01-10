package com.emergencias.controller;

import com.emergencias.auth.UserAccount;
import com.emergencias.history.EmergencyHistoryManager;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.util.GPSService;

import java.util.Scanner;

public class EmergencyManager {

    public void iniciar(UserAccount usuario, boolean automatica) {

        Scanner scanner = new Scanner(System.in);

        if (automatica) {
            System.out.println("\n⚠ DETECTOR: Posible caída detectada automáticamente");
            System.out.print("¿Desea activar una emergencia? (S/N): ");
        } else {
            System.out.print("¿Desea activar una emergencia? (S/N): ");
        }

        String respuesta = scanner.nextLine().trim();

        if (!respuesta.equalsIgnoreCase("s")) {
            System.out.println("Cancelado.");
            return;
        }

        System.out.println("Activando emergencia automática...");

        String tipo = automatica
                ? "Emergencia detectada automáticamente"
                : "Emergencia activada manualmente";

        String ubicacion = "Ubicación actual detectada por GPS";

        double[] coordenadas = GPSService.getCoordinates();

        EmergencyRecord record = new EmergencyRecord(
                usuario.getUsername(),
                tipo,
                ubicacion,
                coordenadas[0],
                coordenadas[1],
                usuario.getDatosUsuario().getNombre(),
                usuario.getDatosUsuario().getTelefono(),
                usuario.getDatosUsuario().getContactosConfianza()
        );

        EmergencyHistoryManager history = new EmergencyHistoryManager();
        history.saveHistory(record);

        System.out.println("🚨 Emergencia enviada");
        System.out.println("Coordenadas: " + coordenadas[0] + ", " + coordenadas[1]);
    }

    public void verHistorial() {

        EmergencyHistoryManager hm = new EmergencyHistoryManager();
        var lista = hm.loadHistory();

        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay emergencias registradas.");
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
            System.out.println("------------------------------------");
        }
    }
}



