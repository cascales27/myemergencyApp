package com.emergencias.model;

import java.util.List;

public class FeatureCollection {
    private List<Feature> features;
    public List<Feature> getFeatures() { return features; }
    public void setFeatures(List<Feature> features) { this.features = features; }
}

class Feature {
    private Properties properties;
    private Geometry geometry;

    public Properties getProperties() { return properties; }
    public void setProperties(Properties properties) { this.properties = properties; }

    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry geometry) { this.geometry = geometry; }
}

class Properties {
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

    // Getters y setters
    public String getMU_NOMBRE() { return MU_NOMBRE; }
    public void setMU_NOMBRE(String MU_NOMBRE) { this.MU_NOMBRE = MU_NOMBRE; }

    public String getCS_DENOMINACION() { return CS_DENOMINACION; }
    public void setCS_DENOMINACION(String CS_DENOMINACION) { this.CS_DENOMINACION = CS_DENOMINACION; }

    public String getCS_TIPO() { return CS_TIPO; }
    public void setCS_TIPO(String CS_TIPO) { this.CS_TIPO = CS_TIPO; }

    // Añadimos más getters/setters según necesidad
}

class Geometry {
    private String type;
    private List<Double> coordinates;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public List<Double> getCoordinates() { return coordinates; }
    public void setCoordinates(List<Double> coordinates) { this.coordinates = coordinates; }
}
