package com.emergencias.detector;

import com.emergencias.auth.UserAccount;
import com.emergencias.controller.EmergencyManager;

public class FallDetector extends Thread {

    private boolean running = true;
    private final UserAccount user;

    public FallDetector(UserAccount user) {
        this.user = user;
    }

    @Override
    public void run() {

        try {
            while (running) {

                Thread.sleep(10000);

                if (Math.random() > 0.92) {

                    EmergencyManager em = new EmergencyManager();
                    em.iniciar(user, true);
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

