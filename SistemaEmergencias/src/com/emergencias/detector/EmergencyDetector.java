package com.emergencias.detector;

import com.emergencias.model.UserData;
import com.emergencias.model.EmergencyEvent;
import com.emergencias.gps.GPSLocation;
import com.emergencias.gps.GPSManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmergencyDetector {

    /**
     * Detecta una emergencia solicitando los datos al usuario.
     */
    public EmergencyEvent detectarEmergencia() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Desea activar una emergencia? (S/N)");
        String activar = scanner.nextLine().trim().toLowerCase();
        if (!activar.equals("s")) {
            System.out.println("Cancelado por el usuario.");
            return null;
        }

        System.out.println("Selecciona el tipo de emergencia:");
        System.out.println("1 - Caída");
        System.out.println("2 - Accidente");
        System.out.println("3 - Médico");
        System.out.println("4 - Incendio");
        System.out.println("5 - Otro");
        String tipo = obtenerTipo(scanner.nextLine());

        System.out.println("Introduce la ubicación manual de la emergencia:");
        String ubicacion = scanner.nextLine();

        System.out.println("Introduce tu nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Introduce tu teléfono:");
        String telefono = scanner.nextLine();

        // Contactos de confianza
        List<String> contactos = new ArrayList<>();

        System.out.println("¿Desea notificar a algún contacto de confianza? (S/N)");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {

            while (true) {
                System.out.println("Introduce el teléfono del contacto de confianza:");
                contactos.add(scanner.nextLine());

                System.out.println("¿Desea agregar otro contacto de confianza? (S/N)");
                if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
                    break;
                }
            }
        }

        // Crear el usuario con sus contactos
        UserData usuario = new UserData(nombre, telefono, contactos);

        // Obtener GPS real o simulado
        GPSLocation gps = GPSManager.getGPS();

        System.out.println("¿Confirmar envío de alerta? (S/N)");
        if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
            System.out.println("Cancelado por el usuario.");
            return null;
        }

        System.out.println("Generando evento de emergencia…");

        return new EmergencyEvent(
                tipo,
                ubicacion,
                LocalDateTime.now(),
                usuario,
                gps
        );
    }

    private String obtenerTipo(String opcion) {
        return switch (opcion) {
            case "1" -> "Caída";
            case "2" -> "Accidente";
            case "3" -> "Médico";
            case "4" -> "Incendio";
            default -> "Otro";
        };
    }
}










