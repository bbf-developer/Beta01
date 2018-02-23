package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin;

/**
 * Created by Ignacio-RVF-BBF on 23/02/2018.
 */

public class ListCliente {

    private String razonsoc;
    private String nomplanta;

    public ListCliente() {
    }

    public ListCliente(String razonsoc, String nomplanta) {
        this.razonsoc = razonsoc;
        this.nomplanta = nomplanta;
    }

    public String getRazonsoc() {
        return razonsoc;
    }

    public String getNomplanta() {
        return nomplanta;
    }

    public String toString(){
        return razonsoc.toString() + " " + "-"+" "+ nomplanta.toString();
    }
}
