package com.emergencias.model;

public class HealthCenter {

    private String nombre;
    private String municipio;
    private String tipo;
    private double latitud;
    private double longitud;

    public HealthCenter(String nombre, String municipio, String tipo, double latitud, double longitud) {
        this.nombre = nombre;
        this.municipio = municipio;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() { return nombre; }
    public String getMunicipio() { return municipio; }
    public String getTipo() { return tipo; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }

    @Override
    public String toString() {
        return nombre + " | " + municipio + " | " + tipo + " | (" + latitud + ", " + longitud + ")";
    }
}