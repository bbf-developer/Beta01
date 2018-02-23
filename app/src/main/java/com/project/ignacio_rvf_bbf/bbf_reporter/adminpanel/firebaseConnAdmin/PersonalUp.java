package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin;

/**
 * Created by Ignacio-RVF-BBF on 23/02/2018.
 */

public class PersonalUp {

    private String id;
    private String rut;
    private String nombre;
    private String apellidos;
    private String categoria;
    private String celular;
    private String mail;
    private String direccion;
    private String fono;

    public PersonalUp() {
    }

    public PersonalUp(String id, String rut, String nombre, String apellidos, String categoria, String celular,
                      String mail, String direccion, String fono) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.categoria = categoria;
        this.celular = celular;
        this.mail = mail;
        this.direccion = direccion;
        this.fono = fono;
    }

    public String getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCelular() {
        return celular;
    }

    public String getMail() {
        return mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFono() {
        return fono;
    }
}
