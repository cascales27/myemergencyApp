package com.emergencias.auth;

import com.emergencias.model.UserData;

import java.util.ArrayList;

public class AuthSystem {

    public void crearUsuarios() {
        // Administrador
        UserData admin = new UserData(
                "Administrador",          // username
                "Administrador",          // nombre completo
                "000000000",              // teléfono
                new ArrayList<>(),        // contactos
                new ArrayList<>()         // contactos de confianza
        );

        // Usuario normal
        UserData usuario = new UserData(
                "usuario1",               // username
                "Usuario Uno",            // nombre completo
                "123456789",              // teléfono
                new ArrayList<>(),        // contactos
                new ArrayList<>()         // contactos de confianza
        );

        // Aquí agregarías la lógica para guardar los usuarios en JSON o memoria
    }

    public UserData registrarUsuario(String nombre, String telefono) {
        return new UserData(
                nombre,                   // username
                nombre,                   // nombre completo
                telefono,                 // teléfono
                new ArrayList<>(),        // contactos
                new ArrayList<>()         // contactos de confianza
        );
    }
}

