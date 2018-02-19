package com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter;

/**
 * Created by Ignacio-RVF-BBF on 30/01/2018.
 */

public class MedicionTest {

       String medicionId;

       double medicion;
       double medicionAnt;
       double valorNom;
       double espesor;

       int proyeccion;


    public MedicionTest() {
    }

    public MedicionTest(String medicionId, double medicion, double medicionAnt, double valorNom, double espesor, int proyeccion) {
        this.medicionId = medicionId;
        this.medicion = medicion;
        this.medicionAnt = medicionAnt;
        this.valorNom = valorNom;
        this.espesor = espesor;
        this.proyeccion = proyeccion;
    }

    public String getMedicionId() {
        return medicionId;
    }

    public double getMedicion() {
        return medicion;
    }

    public double getMedicionAnt() {
        return medicionAnt;
    }

    public double getValorNom() {
        return valorNom;
    }

    public double getEspesor() {
        return espesor;
    }

    public int getProyeccion() {
        return proyeccion;
    }
}
