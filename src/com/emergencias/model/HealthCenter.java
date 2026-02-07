package com.emergencias.model;

public class HealthCenter {
    private String CD_FASE;
    private String MU_CODINE;
    private String MU_NOMBRE;
    private String CM_CODCM;
    private String CM_NOMBRE;
    private String CS_DENOMINACION;
    private String CS_TIPO;
    private String CS_TITULAR;
    private String CS_GESTION;
    private String CS_ACRUEDAS;
    private String CS_UCI;
    private String CS_CAMAS;
    private String CS_SCUBIERTA;
    private String CS_SAIRE;
    private String CS_SSOLAR;
    private Geometry geometry;

    // Clase interna Geometry
    public static class Geometry {
        private String type;
        private double[] coordinates;

        // Getters y Setters
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public double[] getCoordinates() { return coordinates; }
        public void setCoordinates(double[] coordinates) { this.coordinates = coordinates; }
    }

    // Getters y Setters para HealthCenter
    public String getMU_NOMBRE() { return MU_NOMBRE; }
    public void setMU_NOMBRE(String MU_NOMBRE) { this.MU_NOMBRE = MU_NOMBRE; }

    public String getDenominacion() { return CS_DENOMINACION; }
    public void setDenominacion(String denominacion) { this.CS_DENOMINACION = denominacion; }

    public String getTipo() { return CS_TIPO; }
    public void setTipo(String tipo) { this.CS_TIPO = tipo; }

    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry geometry) { this.geometry = geometry; }

    // Métodos auxiliares para latitud y longitud
    public double getLatitude() { return geometry != null ? geometry.getCoordinates()[1] : 0; }
    public double getLongitude() { return geometry != null ? geometry.getCoordinates()[0] : 0; }
}



