package com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter;

/**
 * Created by Ignacio-RVF-BBF on 02/02/2018.
 */

public class ShowCliente {

    private String razonsoc;

    private String planta;

    public ShowCliente() {
    }


    public String getRazonsoc (){
        return razonsoc;
    }

    public void setRazonsoc(String razonsoc) {
        this.razonsoc = razonsoc;
    }

    public String getPlanta(){return planta;}

    public void setPlanta(String planta){this.planta = planta;}

    @Override
    public String toString(){
        return razonsoc.toUpperCase();
    }


}
