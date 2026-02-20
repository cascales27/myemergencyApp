package com.emergencias.detector;

import com.emergencias.gps.GPSLocation;
import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.HealthCenter;
import com.emergencias.model.UserData;
import com.emergencias.utils.HealthCenterFinder;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmergencyDetector {

    private final ArrayList<HealthCenter> centros;

    public EmergencyDetector(ArrayList<HealthCenter> centros) {
        this.centros = (centros == null) ? new ArrayList<>() : centros;
    }

    public EmergencyEvent detectarEmergencia(UserData usuario) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Desea activar una emergencia? (S/N)");
        if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
            System.out.println("Cancelado por el usuario.");
            return null;
        }

        System.out.println("Selecciona el tipo de emergencia:");
        System.out.println("1 - Caída");
        System.out.println("2 - Accidente");
        System.out.println("3 - Médico");
        System.out.println("4 - Incendio");
        System.out.println("5 - Otro");
        String tipo = obtenerTipo(scanner.nextLine().trim());

        System.out.println("Municipio de la emergencia (ej: Benidorm):");
        String municipio = scanner.nextLine().trim();

        System.out.println("Calle / referencia (opcional):");
        String calle = scanner.nextLine().trim();

        String ubicacion = municipio.isBlank() ? calle : (calle.isBlank() ? municipio : municipio + ", " + calle);

        // ✅ GPS desde JSON
        GPSLocation gps = null;

        List<HealthCenter> matches = HealthCenterFinder.findByMunicipio(centros, municipio);

        if (!matches.isEmpty()) {
            // Si hay varios en el mismo municipio, enseña el primero (o podrías preguntar)
            HealthCenter elegido = matches.get(0);

            gps = new GPSLocation(elegido.getLatitud(), elegido.getLongitud());

            System.out.println("===== CENTRO SANITARIO (JSON) =====");
            System.out.println("Municipio buscado: " + municipio);
            System.out.println("Municipio encontrado: " + elegido.getMunicipio());
            System.out.println(elegido);
        } else {
            System.out.println("⚠ No se encontró municipio en el JSON: " + municipio);
        }

        System.out.println("¿Confirmar envío de alerta? (S/N)");
        if (!scanner.nextLine().trim().equalsIgnoreCase("s")) {
            System.out.println("Cancelado por el usuario.");
            return null;
        }

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