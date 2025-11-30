package com.emergencias.alert;

import com.emergencias.model.EmergencyEvent;

public class AlertSender {

    public void enviarAlerta(EmergencyEvent evento) {

        System.out.println("Enviando alerta a 112: " +
                evento.getTipo() + " en " + evento.getUbicacion());

        // MOSTRAR GPS
        System.out.println("🌍 Coordenadas GPS: " +
                evento.getGps().getLatitud() + ", " +
                evento.getGps().getLongitud());

        // MAPA GOOGLE
        System.out.println("📍 Google Maps:");
        System.out.println("https://www.google.com/maps?q=" +
                evento.getGps().getLatitud() + "," +
                evento.getGps().getLongitud());

        // CONTACTOS
        for (String contacto : evento.getUsuario().getContactosConfianza()) {
            System.out.println("Enviando alerta a contacto de confianza: " + contacto);
        }
    }
}





