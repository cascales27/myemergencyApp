package com.emergencias.detector;

import com.emergencias.controller.EmergencyManager;
import com.emergencias.gps.GPSLocation;
import com.emergencias.model.UserData;

public class FallDetector {

    private EmergencyManager em = new EmergencyManager();

    public void detectarCaida(UserData usuario) {

        // 🚨 GPS simulado (no hay sensor aquí)
        GPSLocation gps = new GPSLocation(40.4168, -3.7038, 5.0);

        em.activarEmergenciaManual(
                "Caída detectada",
                "Ubicación desconocida",
                usuario,
                gps
        );
    }
}

