package com.emergencias.model;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private String nombre;
    private String telefono;
    private List<String> contactosConfianza;

    public UserData(String nombre, String telefono, List<String> contactosConfianza) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.contactosConfianza = (contactosConfianza != null)
                ? contactosConfianza
                : new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    /** ✔ Devuelve la lista de contactos de confianza */
    public List<String> getContactosConfianza() {
        return contactosConfianza;
    }

    /** ✔ Permite agregar un contacto de confianza */
    public void addContacto(String telefonoContacto) {
        contactosConfianza.add(telefonoContacto);
    }
}






