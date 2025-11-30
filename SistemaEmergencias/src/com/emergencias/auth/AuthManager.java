package com.emergencias.auth;

import com.emergencias.model.UserData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AuthManager {

    private final String USERS_FILE = "src/main/resources/users.json";
    private List<UserAccount> users;
    private Gson gson;

    public AuthManager() {
        gson = new Gson();
        users = loadUsers();
    }

    // ============================================================
    // CARGAR USUARIOS
    // ============================================================
    private List<UserAccount> loadUsers() {
        File file = new File(USERS_FILE);

        if (!file.exists()) {
            System.out.println("users.json no encontrado. Creando archivo vacío...");
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {

            Type listType = new TypeToken<List<UserAccount>>() {}.getType();
            List<UserAccount> loaded = gson.fromJson(reader, listType);

            return loaded != null ? loaded : new ArrayList<>();

        } catch (Exception e) {
            System.out.println("Error cargando usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // ============================================================
    // GUARDAR USUARIOS
    // ============================================================
    private void saveUsers() {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        } catch (Exception e) {
            System.out.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    // ============================================================
    // REGISTRO
    // ============================================================
    public boolean register(String username, String password) {
        if (getUser(username) != null) return false;

        UserAccount newUser = new UserAccount(username, password);
        users.add(newUser);
        saveUsers();
        return true;
    }

    // ============================================================
    // LOGIN
    // ============================================================
    public boolean login(String username, String password) {
        UserAccount u = getUser(username);
        return u != null && u.getPassword().equals(password);
    }

    // ============================================================
    // OBTENER USUARIO COMPLETO
    // ============================================================
    public UserAccount getUser(String username) {
        for (UserAccount u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) return u;
        }
        return null;
    }

    // ============================================================
    // ACTUALIZAR DATOS DEL USUARIO
    // ============================================================
    public void updateUserData(UserAccount updated) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equalsIgnoreCase(updated.getUsername())) {
                users.set(i, updated);
                saveUsers();
                return;
            }
        }
    }
}


