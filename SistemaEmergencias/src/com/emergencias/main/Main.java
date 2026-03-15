package com.emergencias.main;

import com.emergencias.controller.EmergencyManager;
import com.emergencias.model.UserData;
import com.emergencias.model.EmergencyRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserData usuario = new UserData(
            "joseacas",        // username
            "Jose Acas",       // nombre completo
            "5455466",         // teléfono
            new ArrayList<>(), // contactos
            new ArrayList<>()  // contactos de confianza
        );

        EmergencyManager em = new EmergencyManager();

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Iniciar emergencia manual");
            System.out.println("2. Ver historial");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("¿Desea activar una emergencia? (S/N): ");
                    String respuesta = sc.nextLine();
                    if (respuesta.equalsIgnoreCase("S")) {
                        em.activarEmergenciaManual("Emergencia activada manualmente", "Ubicación actual detectada por GPS", usuario);
                    }
                    break;

                case "2":
                    List<EmergencyRecord> historial = em.verHistorial();
                    System.out.println("\n=== HISTORIAL DE EMERGENCIAS ===");
                    for (EmergencyRecord e : historial) {
                        System.out.println("------------------------------------");
                        System.out.println("Usuario: " + e.getUsuario());
                        System.out.println("Tipo: " + e.getTipo());
                        System.out.println("Ubicación: " + e.getUbicacion());
                        System.out.println("Coordenadas: " + e.getLat() + ", " + e.getLng());
                        System.out.println("Nombre afectado: " + e.getNombreUsuario());
                        System.out.println("Teléfono: " + e.getTelefonoUsuario());
                    }
                    System.out.println("------------------------------------");
                    break;

                case "3":
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}


