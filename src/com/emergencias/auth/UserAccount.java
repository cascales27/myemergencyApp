package com.emergencias.auth;

import com.emergencias.model.UserData;

public class UserAccount {
    private String username;
    private String password;
    private String role;
    private UserData datosUsuario;

    public UserAccount(String username, String password, String role, UserData datosUsuario) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.datosUsuario = datosUsuario;
    }

    public UserAccount() {}

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public UserData getDatosUsuario() { return datosUsuario; }
}
