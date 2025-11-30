package com.emergencias.main;

import com.emergencias.auth.AuthSystem;
import com.emergencias.auth.UserAccount;
import com.emergencias.controller.EmergencyManager;
import com.emergencias.detector.FallDetector;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthSystem auth = new AuthSystem();
        UserAccount usuario = null;
        FallDetector detector = null;

        System.out.println("=== SISTEMA DE EMERGENCIAS ===");
        System.out.println("Antes de continuar, debes iniciar sesión.\n");

        while (usuario == null) {

            System.out.println("1 - Iniciar sesión");
            System.out.println("2 - Registrarse");
            System.out.println("3 - Salir");
            System.out.print("Elige una opción: ");
            String op = sc.nextLine();

            switch (op) {

                case "1":
                    System.out.print("Usuario: ");
                    String u = sc.nextLine();

                    System.out.print("Contraseña: ");
                    String p = sc.nextLine();

                    usuario = auth.login(u, p);

                    if (usuario == null) {
                        System.out.println("❌ Usuario o contraseña incorrectos.\n");
                    } else {
                        System.out.println("\n✔ Sesión iniciada como: " + usuario.getUsername());

                        detector = new FallDetector(usuario);
                        detector.start();
                    }
                    break;

                case "2":
                    System.out.print("Nuevo usuario: ");
                    String nu = sc.nextLine();

                    System.out.print("Nueva contraseña: ");
                    String np = sc.nextLine();

                    System.out.print("Nombre completo: ");
                    String nc = sc.nextLine();

                    System.out.print("Teléfono: ");
                    String tel = sc.nextLine();

                    if (auth.register(nu, np, nc, tel)) {
                        System.out.println("✔ Registro exitoso. Ya puedes iniciar sesión.\n");
                    }
                    break;

                case "3":
                    System.out.println("Saliendo del sistema...");
                    return;

                default:
                    System.out.println("Opción no válida\n");
            }
        }

        boolean ejecutando = true;

        while (ejecutando) {

            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1 - Registrar nueva emergencia");
            System.out.println("2 - Ver historial de emergencias");
            System.out.println("3 - Salir");
            System.out.print("Elige una opción: ");
            String op = sc.nextLine();

            switch (op) {

                case "1":
                    new EmergencyManager().iniciar(usuario, false);

                    break;

                case "2":
                    new EmergencyManager().verHistorial();
                    break;

                case "3":
                    System.out.println("Saliendo del sistema...");
                    if (detector != null) detector.detener();
                    ejecutando = false;
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
