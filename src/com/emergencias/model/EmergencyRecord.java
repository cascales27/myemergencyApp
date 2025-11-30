package com.emergencias.model;

import java.util.List;

public class EmergencyRecord {

    private String usuario;
    private String tipo;
    private String ubicacion;
    private double lat;
    private double lng;
    private String nombreUsuario;
    private String telefonoUsuario;
    private List<String> contactos;

    public EmergencyRecord(String usuario, String tipo, String ubicacion,
                           double lat, double lng, String nombreUsuario,
                           String telefonoUsuario, List<String> contactos) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.lat = lat;
        this.lng = lng;
        this.nombreUsuario = nombreUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.contactos = contactos;
    }

    public String getUsuario() { return usuario; }
    public String getTipo() { return tipo; }
    public String getUbicacion() { return ubicacion; }
    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getTelefonoUsuario() { return telefonoUsuario; }
    public List<String> getContactos() { return contactos; }
}

