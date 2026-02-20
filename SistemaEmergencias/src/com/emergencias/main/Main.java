package com.emergencias.main;

import com.emergencias.alert.AlertSender;
import com.emergencias.auth.AuthManager;
import com.emergencias.auth.UserAccount;
import com.emergencias.detector.EmergencyDetector;
import com.emergencias.loader.HealthCenterLoader;
import com.emergencias.model.EmergencyEvent;
import com.emergencias.model.HealthCenter;
import com.emergencias.model.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static UserAccount usuarioActual;

    public static void main(String[] args) {

        AuthManager auth = new AuthManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA DE EMERGENCIAS ===");
        System.out.println("Antes de continuar, debes iniciar sesión.\n");

        // 🔐 Login obligatorio
        usuarioActual = loginMenu(auth, scanner);

        if (usuarioActual == null) {
            System.out.println("No se pudo iniciar sesión. Saliendo...");
            return;
        }

        System.out.println("✔ Sesión iniciada correctamente.\n");

        // 👤 Completar datos si faltan
        completarDatosSiFaltan(usuarioActual, scanner, auth);

        // 🏥 Cargar centros sanitarios desde JSON
        String rutaJson = "data/health_centers.json";
        ArrayList<HealthCenter> centros = HealthCenterLoader.load(rutaJson);

        System.out.println("\n=== CENTROS SANITARIOS (JSON) ===");
        System.out.println("Centros cargados: " + centros.size());

        if (centros.isEmpty()) {
            System.out.println("⚠ No se cargó ningún centro. Revisa la ruta: " + rutaJson);
            return;
        }

        // ✅ Mostrar/filtrar centros (solo menú JSON)
        mostrarMenuCentros(scanner, centros);

        // ✅ Detectar emergencia usando el JSON (coordenadas del centro del municipio)
        EmergencyDetector detector = new EmergencyDetector(centros);
        EmergencyEvent evento = detector.detectarEmergencia(usuarioActual.getDatosUsuario());

        if (evento != null) {
            new AlertSender().enviarAlerta(evento);
        }
    }

    // ============================================================
    // 🏥 MENÚ JSON: mostrar / filtrar (NO envía alertas)
    // ============================================================
    private static void mostrarMenuCentros(Scanner scanner, ArrayList<HealthCenter> centros) {

        // Mostrar los primeros 10
        imprimirListaCentros(centros, 10);

        while (true) {
            System.out.println("\nOpciones JSON:");
            System.out.println("1 - Ver otros 10");
            System.out.println("2 - Buscar por municipio");
            System.out.println("3 - Continuar");
            System.out.print("Elige una opción: ");

            String op = scanner.nextLine().trim();

            if (op.equals("1")) {
                System.out.print("¿Desde qué número? (ej. 11): ");
                int desde = parseIntSeguro(scanner.nextLine().trim(), 1);
                imprimirRangoCentros(centros, desde, 10);

            } else if (op.equals("2")) {
                System.out.print("Municipio (texto): ");
                String mun = scanner.nextLine().trim();
                ArrayList<HealthCenter> filtrados = filtrarPorMunicipio(centros, mun);
                System.out.println("Coincidencias: " + filtrados.size());
                imprimirListaCentros(filtrados, 10);

            } else if (op.equals("3")) {
                System.out.println();
                break;

            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    private static void imprimirListaCentros(List<HealthCenter> centros, int max) {
        int limite = Math.min(max, centros.size());
        for (int i = 0; i < limite; i++) {
            System.out.println((i + 1) + ") " + centros.get(i));
        }
        if (centros.size() > limite) {
            System.out.println("... (" + (centros.size() - limite) + " más)");
        }
    }

    private static void imprimirRangoCentros(List<HealthCenter> centros, int desde1Based, int cuantos) {
        int start = Math.max(1, desde1Based) - 1;
        if (start >= centros.size()) {
            System.out.println("⚠ Fuera de rango. Total: " + centros.size());
            return;
        }
        int end = Math.min(centros.size(), start + cuantos);
        for (int i = start; i < end; i++) {
            System.out.println((i + 1) + ") " + centros.get(i));
        }
        if (end < centros.size()) {
            System.out.println("... (" + (centros.size() - end) + " más)");
        }
    }

    private static ArrayList<HealthCenter> filtrarPorMunicipio(List<HealthCenter> centros, String municipio) {
        ArrayList<HealthCenter> res = new ArrayList<>();
        if (municipio == null) return res;

        String q = municipio.trim().toLowerCase();
        for (HealthCenter hc : centros) {
            String m = (hc.getMunicipio() == null) ? "" : hc.getMunicipio().toLowerCase();
            if (m.contains(q)) res.add(hc);
        }
        return res;
    }

    private static int parseIntSeguro(String s, int porDefecto) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return porDefecto;
        }
    }

    // ============================================================
    // 🔐 MENÚ LOGIN / REGISTRO
    // ============================================================
    private static UserAccount loginMenu(AuthManager auth, Scanner scanner) {

        while (true) {
            System.out.println("1 - Iniciar sesión");
            System.out.println("2 - Registrarse");
            System.out.println("3 - Salir");
            System.out.print("Elige una opción: ");

            switch (scanner.nextLine().trim()) {

                case "1":
                    System.out.print("Usuario: ");
                    String user = scanner.nextLine().trim();

                    System.out.print("Contraseña: ");
                    String pass = scanner.nextLine();

                    if (auth.login(user, pass)) {
                        return auth.getUser(user);
                    } else {
                        System.out.println("❌ Usuario o contraseña incorrectos.\n");
                    }
                    break;

                case "2":
                    System.out.print("Nuevo nombre de usuario: ");
                    String newUser = scanner.nextLine().trim();

                    System.out.print("Nueva contraseña: ");
                    String newPass = scanner.nextLine();

                    if (auth.register(newUser, newPass)) {
                        System.out.println("✔ Usuario registrado correctamente.\n");
                        return auth.getUser(newUser);
                    } else {
                        System.out.println("❌ El usuario ya existe.\n");
                    }
                    break;

                case "3":
                    return null;

                default:
                    System.out.println("Opción no válida.\n");
            }
        }
    }

    // ============================================================
    // 👤 COMPLETAR DATOS PERSONALES SOLO LA PRIMERA VEZ
    // ============================================================
    private static void completarDatosSiFaltan(UserAccount cuenta, Scanner scanner, AuthManager auth) {

        UserData datos = cuenta.getDatosUsuario();
        if (datos == null) {
            datos = new UserData("", "", new ArrayList<>());
        }

        boolean necesitaGuardar = false;

        String nombre = datos.getNombre();
        String telefono = datos.getTelefono();

        List<String> contactosBase = datos.getContactosConfianza();
        ArrayList<String> contactos = (contactosBase == null) ? new ArrayList<>() : new ArrayList<>(contactosBase);

        if (nombre == null || nombre.isBlank()) {
            System.out.print("Introduce tu nombre: ");
            nombre = scanner.nextLine().trim();
            necesitaGuardar = true;
        }

        if (telefono == null || telefono.isBlank()) {
            System.out.print("Introduce tu teléfono: ");
            telefono = scanner.nextLine().trim();
            necesitaGuardar = true;
        }

        if (contactos.isEmpty()) {
            System.out.print("¿Desea agregar contactos de confianza? (S/N): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
                while (true) {
                    System.out.print("Introduce teléfono del contacto: ");
                    String c = scanner.nextLine().trim();
                    if (!c.isEmpty()) contactos.add(c);

                    System.out.print("¿Agregar otro? (S/N): ");
                    if (!scanner.nextLine().trim().equalsIgnoreCase("s")) break;
                }
                necesitaGuardar = true;
            }
        }

        if (necesitaGuardar) {
            UserData actualizado = new UserData(nombre, telefono, contactos);
            cuenta.setDatosUsuario(actualizado);
            auth.updateUserData(cuenta);
            System.out.println("✔ Datos guardados correctamente.\n");
        }
    }
}