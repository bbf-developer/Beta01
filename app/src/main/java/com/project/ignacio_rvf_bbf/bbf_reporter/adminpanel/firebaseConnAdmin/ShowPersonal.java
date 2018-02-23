package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin;

/**
 * Created by Ignacio-RVF-BBF on 23/02/2018.
 */

public class ShowPersonal {

    private String nombre;
    private String apellidos;

    public ShowPersonal() {
    }

    public ShowPersonal(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    //Para ver el elemento en el List View es necesario retornar el valor toString()
    //Se puede concatenar para sumar datos al ListView
    public String toString(){
        return nombre.toString() + " " + apellidos.toString();

    }



}
