package com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter;

/**
 * Created by Ignacio-RVF-BBF on 12/02/2018.
 */

//TODO: ALMACENAMIENTO DE PARAMETROS DE TABLA CREADA.

public class CreaMedicion {

        private String id;
        private String cliente1;
        private String planta1;
        private String zona1;
        int limiteTubo;
        private String limiteCol;
        //int limiteLetra;

    public CreaMedicion() {
    }


    public CreaMedicion(String id, String cliente1, String planta1, String zona1, int limiteTubo, String limiteCol) {
        this.id = id;
        this.cliente1 = cliente1;
        this.planta1 = planta1;
        this.zona1 = zona1;
        this.limiteTubo = limiteTubo;
        this.limiteCol = limiteCol;

    }



    public String getId() {
        return id;
    }

    public String getCliente1() {
        return cliente1;
    }

    public String getPlanta1() {
        return planta1;
    }

    public String getZona1() {
        return zona1;
    }

    public int getLimiteTubo() {
        return limiteTubo;
    }

    public String getLimiteCol() { return limiteCol; }
    //SETTING PARAMETERS


    public void setId(String id) {
        this.id = id;
    }

    public void setCliente1(String cliente1) {
        this.cliente1 = cliente1;
    }

    public void setPlanta1(String planta1) {
        this.planta1 = planta1;
    }

    public void setZona1(String zona1) {
        this.zona1 = zona1;
    }

    public void setLimiteTubo(int limiteTubo) {
        this.limiteTubo = limiteTubo;
    }
}
