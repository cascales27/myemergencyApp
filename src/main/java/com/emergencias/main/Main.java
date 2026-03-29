package com.emergencias.main;

import com.emergencias.controller.EmergencyManager;
import com.emergencias.model.UserData;
import com.emergencias.model.EmergencyRecord;
import com.emergencias.model.HealthCenter;
import com.emergencias.util.HealthCenterLoader;
import com.emergencias.util.HealthCenterUtils;
import com.emergencias.gps.GPSLocation;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Usuario simulado
        UserData usuario = new UserData(
                "Jose Acas",
                "5455466",
                "joseacas",
                new ArrayList<>(), // contactos
                new ArrayList<>()  // contactos de confianza
        );

        EmergencyManager em = new EmergencyManager();

        // -------------------
        // CARGA DE CENTROS DE SALUD
        // -------------------
        List<HealthCenter> centrosSalud = HealthCenterLoader.loadFromFile();
        System.out.println("✅ Centros de salud cargados: " + centrosSalud.size());

        System.out.println("=== Sistema de Emergencias ===");
        System.out.println("Usuario: " + usuario.getUsername());
        System.out.println("Bienvenido, " + usuario.getNombre());

        boolean salir = false;
        while (!salir) {
            System.out.println("\nOpciones:");
            System.out.println("1. Iniciar emergencia manual");
            System.out.println("2. Ver historial");
            System.out.println("3. Ver tutorial de primeros auxilios");
            System.out.println("4. Ver centros de salud cargados");
            System.out.println("5. Ver centro de salud más cercano");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("¿Desea activar una emergencia? (S/N): ");
                    String respuesta = scanner.nextLine();

                    if (respuesta.equalsIgnoreCase("S")) {

                        // GPS simulado
                        GPSLocation gps = new GPSLocation(40.4168, -3.7038, 5.0);

                        em.activarEmergenciaManual(
                                "Emergencia activada manualmente",
                                "Ubicación actual detectada por GPS",
                                usuario,
                                gps
                        );
                    }
                    break;

                case "2":
                    List<EmergencyRecord> historial = em.getHistorial();
                    System.out.println("\n=== HISTORIAL DE EMERGENCIAS ===");

                    if (historial.isEmpty()) {
                        System.out.println("No hay registros aún.");
                    } else {
                        for (EmergencyRecord record : historial) {
                            System.out.println("------------------------------------");
                            System.out.println("Usuario: " + record.getUsuario());
                            System.out.println("Tipo: " + record.getTipo());
                            System.out.println("Ubicación: " + record.getUbicacion());
                            System.out.println("Coordenadas: " + record.getLat() + ", " + record.getLng());
                            System.out.println("Nombre afectado: " + record.getNombreUsuario());
                            System.out.println("Teléfono: " + record.getTelefonoUsuario());
                        }
                        System.out.println("------------------------------------");
                    }
                    break;

                case "3":
                    System.out.print("Ingrese el tipo de emergencia para el tutorial: ");
                    String tipo = scanner.nextLine();
                    em.mostrarTutorial(tipo);
                    break;

                case "4":
                    System.out.println("\n=== CENTROS DE SALUD CARGADOS ===");

                    if (centrosSalud.isEmpty()) {
                        System.out.println("No se han cargado centros de salud.");
                    } else {
                        for (HealthCenter hc : centrosSalud) {
                            System.out.println("------------------------------------");
                            System.out.println("Nombre: " + hc.getMU_NOMBRE());
                            System.out.println("Denominación: " + hc.getDenominacion());
                            System.out.println("Tipo: " + hc.getTipo());
                            System.out.println("Coordenadas: " +
                                    hc.getGeometry().getCoordinates()[0] + ", " +
                                    hc.getGeometry().getCoordinates()[1]);
                        }
                        System.out.println("------------------------------------");
                    }
                    break;

                case "5":
                    if (centrosSalud.isEmpty()) {
                        System.out.println("No hay centros de salud cargados.");
                    } else {

                        double userLat = 40.4168;
                        double userLng = -3.7038;

                        HealthCenter cercano = HealthCenterUtils.getCentroMasCercano(
                                userLat, userLng, centrosSalud
                        );

                        if (cercano != null) {
                            System.out.println("\n=== CENTRO DE SALUD MÁS CERCANO ===");
                            System.out.println("Nombre: " + cercano.getMU_NOMBRE());
                            System.out.println("Denominación: " + cercano.getDenominacion());
                            System.out.println("Tipo: " + cercano.getTipo());
                            System.out.println("Coordenadas: " +
                                    cercano.getGeometry().getCoordinates()[0] + ", " +
                                    cercano.getGeometry().getCoordinates()[1]);
                            System.out.println("------------------------------------");
                        } else {
                            System.out.println("No se pudo determinar el centro más cercano.");
                        }
                    }
                    break;

                case "6":
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}