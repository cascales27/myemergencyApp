package com.emergencias.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.emergencias.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AuthSystem {

    private static final String FILE_PATH = "usuarios.json";
    private static List<UserAccount> usuarios = new ArrayList<>();
    private Gson gson = new Gson();

    public AuthSystem() {
        loadUsuarios();
        if (usuarios.isEmpty()) {
            // Admin por defecto
            usuarios.add(new UserAccount(
                    "admin",
                    "admin",
                    "ADMIN",
                    new UserData("Administrador", "000000000", new ArrayList<>())
            ));
            saveUsuarios();
        }
    }

    private void saveUsuarios() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (Exception e) {
            System.out.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    private void loadUsuarios() {
        try {
            File f = new File(FILE_PATH);
            if (!f.exists()) return;

            Type listType = new TypeToken<ArrayList<UserAccount>>(){}.getType();
            usuarios = gson.fromJson(new FileReader(FILE_PATH), listType);

        } catch (Exception e) {
            System.out.println("Error cargando usuarios: " + e.getMessage());
        }
    }

    public UserAccount login(String username, String password) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username)
                        && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean register(String username, String password, String nombre, String telefono) {
        if (usuarios.stream().anyMatch(u -> u.getUsername().equals(username))) {
            return false;
        }

        usuarios.add(new UserAccount(
                username,
                password,
                "USER",
                new UserData(nombre, telefono, new ArrayList<>())
        ));

        saveUsuarios();
        return true;
    }
}

