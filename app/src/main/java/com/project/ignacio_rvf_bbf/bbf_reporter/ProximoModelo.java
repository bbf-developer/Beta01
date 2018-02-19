package com.project.ignacio_rvf_bbf.bbf_reporter;

/**
 * Created by Ignacio-RVF-BBF on 17/01/2018.
 */

public class ProximoModelo {
    private int id;
    private String empresa, mision, fecha;
    private int imgEmpresa;

    public ProximoModelo() {

    }



    public ProximoModelo(int id, String empresa, String mision, String fecha, int imgEmpresa) {
        this.id = id;
        this.empresa = empresa;
        this.mision = mision;
        this.fecha = fecha;
        this.imgEmpresa = imgEmpresa;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getImgEmpresa() {
        return imgEmpresa;
    }

    public void setImgEmpresa(int imgEmpresa) {
        this.imgEmpresa = imgEmpresa;
    }
}
