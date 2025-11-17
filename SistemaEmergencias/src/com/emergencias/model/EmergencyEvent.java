package com.emergencias.model;

import java.time.LocalDateTime;

import com.emergencias.gps.GPSLocation;

public class EmergencyEvent {

    private String tipo;
    private String ubicacion;
    private LocalDateTime fechaHora;
    private UserData usuario;
    private GPSLocation gps;

    public EmergencyEvent(String tipo, String ubicacion, LocalDateTime fechaHora, UserData usuario, GPSLocation gps) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.fechaHora = fechaHora;
        this.usuario = usuario;
        this.gps = gps;
    }

    public String getTipo() {
        return tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public UserData getUsuario() {
        return usuario;
    }

    public GPSLocation getGps() {
        return gps;
    }
}





