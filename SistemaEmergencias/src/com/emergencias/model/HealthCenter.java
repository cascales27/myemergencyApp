// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.emergencias.model;

public class HealthCenter {
   private String nombre;
   private String municipio;
   private String tipo;
   private double latitud;
   private double longitud;

   public HealthCenter(String var1, String var2, String var3, double var4, double var6) {
      this.nombre = var1;
      this.municipio = var2;
      this.tipo = var3;
      this.latitud = var4;
      this.longitud = var6;
   }

   public String getNombre() {
      return this.nombre;
   }

   public String getMunicipio() {
      return this.municipio;
   }

   public String getTipo() {
      return this.tipo;
   }

   public double getLatitud() {
      return this.latitud;
   }

   public double getLongitud() {
      return this.longitud;
   }

   public String toString() {
      return this.nombre + " | " + this.municipio + " | " + this.tipo + " | (" + this.latitud + ", " + this.longitud + ")";
   }
}