package com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter;

/**
 * Created by Ignacio-RVF-BBF on 06/02/2018.
 */

public class ShowPlanta {


    private String nomplanta;

    public ShowPlanta() {
    }

    public String getNomplanta(){return nomplanta;}

    public void setNomplanta(String nomplanta){this.nomplanta = nomplanta;}

    @Override
    public String toString(){
         return nomplanta.toUpperCase();
    }

}
