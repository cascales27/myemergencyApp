package com.emergencias.controller;

import com.emergencias.alert.AlertSender;
import com.emergencias.auth.UserAccount;
import com.emergencias.gps.GPSLocation;
import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.model.UserData;
import com.emergencias.history.EmergencyHistoryViewer;

import java.util.Scanner;
import java.time.LocalDateTime;

public class EmergencyManager {

    private Scanner scanner = new Scanner(System.in);
    private UserAccount usuario;

    public EmergencyManager(UserAccount usuario) {
        this.usuario = usuario;
    }

    public void iniciar() {

        System.out.println("¿Desea activar una emergencia? (S/N)");
        if (!scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Saliendo...");
            return;
        }

        EmergencyEvent evento = crearEvento();

        // Enviar alerta
        AlertSender sender = new AlertSender();
        sender.enviarAlerta(evento);
        System.out.println("✔ Alerta enviada correctamente.");

        // Guardar en historial
        UserData datos = usuario.getDatosUsuario();
        EmergencyRecord registro = new EmergencyRecord(
                evento.getTipo(),
                evento.getUbicacion(),
                LocalDateTime.now().toString(), // Guardamos fecha como String
                evento.getGps().getLatitud(),
                evento.getGps().getLongitud(),
                datos
        );
        EmergencyHistoryViewer.guardarHistorial(registro);
    }

    private EmergencyEvent crearEvento() {

        System.out.println("Selecciona el tipo de emergencia:");
        System.out.println("1 - Caída\n2 - Accidente\n3 - Médico\n4 - Incendio\n5 - Otro");
        String tipo = obtenerTipo(scanner.nextLine());

        System.out.println("Introduce la ubicación de la emergencia:");
        String ubicacion = scanner.nextLine();

        UserData datos = usuario.getDatosUsuario();
        GPSLocation gps = generarGPS();

        return new EmergencyEvent(
            tipo,
            ubicacion,
             LocalDateTime.now(),  // agrega la fecha/hora
             datos,                // UserData
             gps                   // GPSLocation
       );
    }

    private String obtenerTipo(String opcion) {
        switch (opcion) {
            case "1": return "Caída";
            case "2": return "Accidente";
            case "3": return "Médico";
            case "4": return "Incendio";
            default:  return "Otro";
        }
    }

    private GPSLocation generarGPS() {
        double lat = 40.0 + (Math.random() * 2);
        double lon = -4.0 + (Math.random() * 2);
        return new GPSLocation(lat, lon);
    }
}



