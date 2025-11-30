package com.emergencias.detector;

import com.emergencias.auth.UserAccount;
import com.emergencias.controller.EmergencyManager;

public class FallDetector extends Thread {

    private boolean running = true;
    private final UserAccount user;
    private boolean enviandoEmergencia = false;

    public FallDetector(UserAccount user) {
        this.user = user;
        setDaemon(true); // No bloquea el cierre de la app
    }

    @Override
    public void run() {

        EmergencyManager em = new EmergencyManager();

        try {
            while (running) {

                Thread.sleep(10000); // Cada 10 segundos

                if (!running) break;

                // Probabilidad simulada
                if (Math.random() > 0.92 && !enviandoEmergencia) {
                    enviandoEmergencia = true;
                    System.out.println("\n⚠ DETECTOR: Caída detectada, enviando emergencia automáticamente...");

                    em.iniciar(user, true);

                    enviandoEmergencia = false;
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void detener() {
        running = false;
    }
}
