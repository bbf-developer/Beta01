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
        private String limiteCol;
        private String year;
        private String month;
        private String day;
        private String linea;
        private String menu1;

        int limiteLetra;
        int limiteTubo;

    public CreaMedicion() {
    }

    public CreaMedicion(String id, String cliente1, String planta1, String zona1, int limiteTubo, String limiteCol, String year, String month,
                        String day, String linea, String menu1 ) {
        this.id = id;
        this.cliente1 = cliente1;
        this.planta1 = planta1;
        this.zona1 = zona1;
        this.limiteTubo = limiteTubo;
        this.limiteCol = limiteCol;

        this.year = year;
        this.month = month;
        this.day = day;

        this.menu1 = menu1;
        this.linea = linea;
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

    public String getZona1() { return zona1; }

    public int getLimiteTubo() {
        return limiteTubo;
    }

    public String getLimiteCol() { return limiteCol; }

    public String getYear() { return year; }

    public String getMonth() { return month;}

    public String getDay() { return day; }

    public String getLinea() {return linea; }

    public String getMenu1() {return menu1; }

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

    public void setLimiteCol(String limiteCol) { this.limiteCol = limiteCol; }

    public void setYear(String year) { this.year = year; }

    public void setMonth(String month) { this.month = month; }

    public void setDay(String day) { this.day = day; }

    public void setLinea(String linea){this.linea = linea; }

}
