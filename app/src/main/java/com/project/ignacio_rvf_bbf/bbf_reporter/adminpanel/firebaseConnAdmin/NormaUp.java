package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin;

/**
 * Created by Ignacio-RVF-BBF on 23/02/2018.
 */

public class NormaUp {

    private String id;
    private String codigo;
    private String nombre;
    private double formula;

    public NormaUp() {
    }

    public NormaUp(String id, String codigo, String nombre, double formula) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.formula = formula;
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getFormula() {
        return formula;
    }
}
