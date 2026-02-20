package com.emergencias.main;

import com.emergencias.auth.AuthSystem;
import com.emergencias.auth.UserAccount;
import com.emergencias.controller.EmergencyManager;
import com.emergencias.detector.FallDetector;
import com.emergencias.model.HealthCenter;
import com.emergencias.util.HealthCenterLoader;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Inicializar sistema de autenticación
        AuthSystem auth = new AuthSystem();
        UserAccount usuario = null;
        FallDetector detector = null;

        System.out.println("=== Sistema de Emergencias ===");

        // Login simple
        System.out.print("Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        usuario = auth.login(username, password);

        if (usuario == null) {
            System.out.println("Usuario o contraseña incorrectos.");
            return;
        }

        System.out.println("Bienvenido, " + usuario.getDatosUsuario().getNombre());

        // Inicializar detector de caídas
        detector = new FallDetector(usuario);

        // Cargar centros de salud
        String path = "C:\\ProyectoJava\\SistemaEmergencias\\data\\health_centers.json";
        List<HealthCenter> healthCenters = HealthCenterLoader.loadFromFile(path);

        if (healthCenters.isEmpty()) {
            System.out.println("No se pudieron cargar los centros de salud.");
        } else {
            System.out.println("Se cargaron " + healthCenters.size() + " centros de salud.");
            HealthCenter first = healthCenters.get(0);
            System.out.println("Primer centro: " + first.getMU_NOMBRE() + " - " + first.getDenominacion());
        }

        // Menú simple
        boolean running = true;
        while (running) {
            System.out.println("\nOpciones:");
            System.out.println("1. Iniciar emergencia manual");
            System.out.println("2. Ver historial");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    new EmergencyManager().iniciar(usuario, false);
                    break;
                case 2:
                    new EmergencyManager().verHistorial();
                    break;
                case 3:
                    running = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}



