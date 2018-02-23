package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin;

/**
 * Created by Ignacio-RVF-BBF on 05/02/2018.
 */

public class ClienteUp {

    private String id;
    private String rut;
    private String razonsoc;
    private String giro;
    private String codplanta;
    private String nomplanta;
    private String direccion;
    private String contacto;
    private String mail;
    private String fono;
    private int linea;

    public ClienteUp(){  }

    public ClienteUp(String id, String rut, String razonsoc, String giro, String codplanta,
                       String nomplanta, String direccion, String contacto, String mail, String fono, int linea) {
        this.id = id;
        this.rut = rut;
        this.razonsoc = razonsoc;
        this.giro = giro;
        this.codplanta = codplanta;
        this.nomplanta = nomplanta;
        this.direccion = direccion;
        this.contacto = contacto;
        this.mail = mail;
        this.fono = fono;
        this.linea = linea;
    }

    public String getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getRazonsoc() {
        return razonsoc;
    }

    public String getGiro() {
        return giro;
    }

    public String getCodplanta() {return codplanta; }

    public String getNomplanta() {
        return nomplanta;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public String getMail() {
        return mail;
    }

    public String getFono() { return fono; }

    public int getLinea(){return linea;}
}
