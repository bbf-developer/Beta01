package com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter;

/**
 * Created by Ignacio-RVF-BBF on 02/03/2018.
 * Clase retorno de datos
 * Muestra las tablas creadas anteriormente
 */

public class ShowTableDate {

    private String year;
    private String month;
    private String day;
    private String cliente1;
    private String planta1;
    private String zona1;
    private String menu1;
    private String linea;

    //DATOS A EMPAQUETAR PARA CONFIGURAR TABLA
    private String limiteCol;
    private int limiteTubo;


    public ShowTableDate() {
    }

    public ShowTableDate(String year, String month, String day, String limiteCol, String cliente1, String planta1, String zona1, String menu1
                         ,String linea,int limiteTubo) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.limiteCol = limiteCol;
        this.cliente1 = cliente1;
        this.planta1 = planta1;
        this.zona1 = zona1;
        this.menu1 = menu1;
        this.linea = linea;
        this.limiteTubo = limiteTubo;

    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
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

    public String getMenu1() {
        return menu1;
    }

    public String getLinea() {
        return linea;
    }

    public String getLimiteCol() {
        return limiteCol;
    }

    public int getLimiteTubo() {
        return limiteTubo;
    }

    public String toString(){

        return year.toString() + " - "  + month.toString() + " - " + day.toString()
                + "  |  " + cliente1.toString() + " - " + planta1.toString()
                + "  |  " + menu1.toString() + " - " + zona1.toString() + " -  Linea: " + linea.toString();
    }

}
