package com.emergencias.model;

import com.emergencias.model.UserData;

public class EmergencyRecord {

    private String tipo;
    private String ubicacion;
    private String fecha; // Cambiado de LocalDateTime a String
    private double latitud;
    private double longitud;
    private UserData usuario;

    public EmergencyRecord(String tipo, String ubicacion, String fecha,
                           double latitud, double longitud, UserData usuario) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.usuario = usuario;
    }

    public String getTipo() { return tipo; }
    public String getUbicacion() { return ubicacion; }
    public String getFecha() { return fecha; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }
    public UserData getUsuario() { return usuario; }

    @Override
    public String toString() {
        return fecha + " | " + tipo + " | " + ubicacion +
               " | (" + latitud + ", " + longitud + ")";
    }
}
