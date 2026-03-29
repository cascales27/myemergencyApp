package com.emergencias.detector;

import com.emergencias.gps.GPSLocation;
import com.emergencias.gps.GPSManager;
import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.UserData;

public class EmergencyDetector {


public EmergencyEvent detectarCaida(UserData usuario) {

    try {
        System.out.println("⚠ Detectando caída...");
        Thread.sleep(2000); // Simulación de tiempo de detección
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.out.println("Error en la simulación de detección.");
    }

    String tipo = "Caída";
    String ubicacion = "Desconocida";

    GPSLocation gps = GPSManager.getGPS();

    return new EmergencyEvent(
            tipo,
            ubicacion,
            gps,
            usuario
    );
}



}











