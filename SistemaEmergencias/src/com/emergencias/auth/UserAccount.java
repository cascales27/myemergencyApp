package com.emergencias.auth;

import com.emergencias.model.UserData;

public class UserAccount {

    private String username;
    private String password;
    private UserData datosUsuario;

    public UserAccount() {
        // Necesario para Gson
    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.datosUsuario = new UserData("", "", null); // Empty user data initially
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserData getDatosUsuario() {
        return datosUsuario;
    }

    public void setDatosUsuario(UserData datos) {
        this.datosUsuario = datos;
    }
}



