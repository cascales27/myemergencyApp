package com.emergencias.model;

import java.util.List;

public class UserData {

    private String username;
    private String nombre;
    private String telefono;
    private List<String> contactos;
    private List<String> contactosConfianza;

    public UserData(String username, String nombre, String telefono,
                    List<String> contactos, List<String> contactosConfianza) {
        this.username = username;
        this.nombre = nombre;
        this.telefono = telefono;
        this.contactos = contactos;
        this.contactosConfianza = contactosConfianza;
    }

    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<String> getContactos() {
        return contactos;
    }

    public List<String> getContactosConfianza() {
        return contactosConfianza;
    }
}
