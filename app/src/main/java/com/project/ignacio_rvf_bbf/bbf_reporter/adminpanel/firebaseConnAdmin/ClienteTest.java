package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin;

/**
 * Created by Ignacio-RVF-BBF on 31/01/2018.
 */

public class ClienteTest {

   public String rut,razonsoc, giro,codplanta, nomplanta, direccion,contacto, mail, fono;
   public String id;


    public ClienteTest() {
    }

    public ClienteTest(String rut, String razonsoc, String giro, String codplanta, String nomplanta,
                       String direccion, String contacto, String mail, String fono, String id) {
        this.rut = rut;
        this.razonsoc = razonsoc;
        this.giro = giro;
        this.codplanta = codplanta;
        this.nomplanta = nomplanta;
        this.direccion = direccion;
        this.contacto = contacto;
        this.mail = mail;
        this.fono = fono;

        this.id = id;
    }
}

