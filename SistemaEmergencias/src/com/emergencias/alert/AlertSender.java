package com.emergencias.alert;

import com.emergencias.gps.GPSLocation;
import com.emergencias.model.EmergencyEvent;

public class AlertSender {

    public void enviarAlerta(EmergencyEvent evento) {

        System.out.println("Enviando alerta a 112: " +
                evento.getTipo() + " en " + evento.getUbicacion());

        GPSLocation gps = evento.getGps();

        if (evento.getGps() != null) {
            System.out.println( "🌍 Coordenadas: "+
                    evento.getGps().getLatitud() + ", " + evento.getGps().getLongitud());
            System.out.println("📍 Google Maps:");
            System.out.println("https://www.google.com/maps?q=" +
                    evento.getGps().getLatitud() + "," + evento.getGps().getLongitud());
        } else {
            System.out.println("🌍 Coordenadas: no disponibles (no hay match en JSON)");
        }

        for (String contacto : evento.getUsuario().getContactosConfianza()) {
            System.out.println("Enviando alerta a contacto de confianza: " + contacto);
        }

        System.out.println("✔ Alerta enviada correctamente.");
    }
}