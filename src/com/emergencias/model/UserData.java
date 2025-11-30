package com.emergencias.model;

import java.util.ArrayList;
import java.util.List;

/**

* Datos personales del usuario logueado.
* Se guarda con la cuenta y se usa en todas las emergencias automáticamente.
  */
  public class UserData {

  private String nombre;
  private String telefono;
  private List<String> contactosConfianza;

  // Constructor vacío requerido por Gson
  public UserData() {
  this.contactosConfianza = new ArrayList<>();
  }

  public UserData(String nombre, String telefono, List<String> contactosConfianza) {
  this.nombre = nombre;
  this.telefono = telefono;
  this.contactosConfianza = contactosConfianza != null ? contactosConfianza : new ArrayList<>();
  }

  public String getNombre() {
  return nombre;
  }

  public String getTelefono() {
  return telefono;
  }

  public List<String> getContactosConfianza() {
  return contactosConfianza;
  }

  public void addContacto(String telefono) {
  if (this.contactosConfianza == null) {
  this.contactosConfianza = new ArrayList<>();
  }
  this.contactosConfianza.add(telefono);
  }

  public void removeContacto(String telefono) {
  if (this.contactosConfianza != null) {
  this.contactosConfianza.remove(telefono);
  }
  }
  }
