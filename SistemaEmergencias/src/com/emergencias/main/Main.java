package com.emergencias.main;

import com.emergencias.auth.AuthManager;
import com.emergencias.auth.UserAccount;
import com.emergencias.controller.EmergencyManager;
import com.emergencias.model.UserData;

import java.util.ArrayList;
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

        // 🔄 Comprobar si faltan datos del usuario
        completarDatosSiFaltan(usuarioActual, scanner, auth);

        // 🚨 Iniciar gestor de emergencias
        EmergencyManager manager = new EmergencyManager(usuarioActual);
        manager.iniciar();
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

            switch (scanner.nextLine()) {

                case "1":
                    System.out.print("Usuario: ");
                    String user = scanner.nextLine();

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
                    String newUser = scanner.nextLine();

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

        boolean necesitaGuardar = false;

        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            System.out.print("Introduce tu nombre: ");
            datos = new UserData(scanner.nextLine(), datos.getTelefono(), datos.getContactosConfianza());
            necesitaGuardar = true;
        }

        if (datos.getTelefono() == null || datos.getTelefono().isBlank()) {
            System.out.print("Introduce tu teléfono: ");
            datos = new UserData(datos.getNombre(), scanner.nextLine(), datos.getContactosConfianza());
            necesitaGuardar = true;
        }

        if (datos.getContactosConfianza().isEmpty()) {
            System.out.println("¿Desea agregar contactos de confianza? (S/N)");
            if (scanner.nextLine().equalsIgnoreCase("s")) {

                ArrayList<String> contactos = new ArrayList<>();

                while (true) {
                    System.out.print("Introduce teléfono del contacto: ");
                    contactos.add(scanner.nextLine());

                    System.out.println("¿Agregar otro? (S/N)");
                    if (!scanner.nextLine().equalsIgnoreCase("s")) break;
                }

                datos = new UserData(datos.getNombre(), datos.getTelefono(), contactos);
                necesitaGuardar = true;
            }
        }

        if (necesitaGuardar) {
            cuenta.setDatosUsuario(datos);
            auth.updateUserData(cuenta);
            System.out.println("✔ Datos guardados correctamente.\n");
        }
    }
}


