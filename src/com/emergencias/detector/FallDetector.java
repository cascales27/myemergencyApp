package com.emergencias.detector;

import com.emergencias.controller.EmergencyManager;
import com.emergencias.model.UserData;

public class FallDetector {

    private EmergencyManager em;

    public FallDetector(UserData usuario) {
        em = new EmergencyManager();
        // Activación automática simulada
        em.activarEmergenciaManual("Caída detectada", "Ubicación desconocida", usuario);
    }
}

