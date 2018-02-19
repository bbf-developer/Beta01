package com.project.ignacio_rvf_bbf.bbf_reporter.model;

import com.evrencoskun.tableview.sort.ISortableModel;

/**
 * Created by Ignacio-RVF-BBF on 23/01/2018.
 */

public class CellModel implements ISortableModel {

    //ALMACENA PARAMETRO ID DE LA CELDA ej: 1-1, 0-0
    private String mId;
    //ALMACENA EL DATO QUE SE QUIERE INSERTAR EN LA TABLA
    private Object mData;

    public CellModel(String p_mId, Object p_mData) {
        this.mId = p_mId;
        this.mData = p_mData;}

    public CellModel(String p_mId) {this.mId = p_mId; }

    public Object getData(){
        return mData;}

    @Override
    public String getId() {
        return mId;}

    public void setData(String p_mData) {
        mData = p_mData;
    }

    @Override
    public Object getContent() {return mData;}
}
