package com.emergencias.model;

import java.time.LocalDateTime;

import com.emergencias.gps.GPSLocation;


public class EmergencyEvent {


private String tipo;
private String ubicacion;
private LocalDateTime fecha;
private GPSLocation gps;
private UserData usuario;

public EmergencyEvent(String tipo, String ubicacion, GPSLocation gps, UserData usuario) {
    this.tipo = tipo;
    this.ubicacion = ubicacion;
    this.gps = gps;
    this.usuario = usuario;
    this.fecha = LocalDateTime.now();
}

public String getTipo() {
    return tipo;
}

public String getUbicacion() {
    return ubicacion;
}

public LocalDateTime getFecha() {
    return fecha;
}

public GPSLocation getGps() {
    return gps;
}

public UserData getUsuario() {
    return usuario;
}


}






