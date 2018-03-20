package com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.project.ignacio_rvf_bbf.bbf_reporter.popup_medicion.PopupClickFragment;
import com.project.ignacio_rvf_bbf.bbf_reporter.popup_medicion.PopupContinue;

import java.util.Map;
import java.util.Set;

/**
 * Created by Ignacio-RVF-BBF on 23/01/2018.
 */

public class MyTableViewListener2 implements ITableViewListener{

    public final static String SHARED_PREF_NUM1="posicionCol";
    public final static String KEY_NUM1= "KEY_COL";
    public final static String KEY_NUM2="KEY_ROW";

    private TableView mTableView;

    private Toast mssgeToast;
    private Context ctxContext;

    private static int posCol;
    private static int posFila;

    private String parseCol;
    private String parseFila;

    public MyTableViewListener2(TableView pTableView){

        this.mTableView = pTableView;
        this.ctxContext = pTableView.getContext();

    }

    /**
     * Called when user click any cell item.
     *  Definición de parametros de celda en tabla para el listener
     * @param p_jCellView  : Clicked Cell ViewHolder.
     * @param p_nXPosition : X (Column) position of Clicked Cell item.
     * @param p_nYPosition : Y (Row) position of Clicked Cell item.
     */

    @Override
    public void onCellClicked (@NonNull RecyclerView.ViewHolder p_jCellView,int p_nXPosition,
                               int p_nYPosition){
        //TODO: SE ALMACENAN LOS PARAMETROS DE POSICIONAMIENTO DE TABLA EN SHARED PREF.;
        pushFragment(ctxContext);

        //LOS PARAMETROS SE DEBEN ALMACENAR Y ENVIAR AL FRAGMENTO PARA CONFIGURAR EL NOMBRE DE
        //LA TABLA AL SELECCIONAR LA CELDA.
        parseCol = String.valueOf(posCol = p_nXPosition);
        //ALMACENA PARAMETRO INT PARA IMPRIMIR EL NUMERO DE LA CELDA SELECCIONADA.
        parseFila = String.valueOf(posFila = p_nYPosition);

        //SHARED PREFERENCES METHOD
        SharedPreferences preferences = ctxContext.getSharedPreferences(SHARED_PREF_NUM1,0);
        SharedPreferences.Editor editor = preferences.edit();
        //EDITOR PUT INT PARA COLUMNAS
        editor.putString(KEY_NUM1, parseCol );
        editor.putString(KEY_NUM2, parseFila);
        //EDITOR PUT INT PARA FILAS
        editor.commit();
    }

    /**
     * Called when user click any column header item.
     *Definicion de parametros para la cabecera de la columna
     * @param p_jColumnHeaderView : Clicked Column Header ViewHolder.
     * @param p_nXPosition        : X (Column) position of Clicked Column Header item.
     */

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

       /* if (p_jColumnHeaderView != null && p_jColumnHeaderView instanceof ColumnHeaderViewHolder) {

            // Create Long Press Popup
            ColumnHeaderLongPressPopup popup = new ColumnHeaderLongPressPopup(
                    (ColumnHeaderViewHolder) p_jColumnHeaderView, mTableView);

            // Show
            popup.show();
        }
        */

    }

    /**
     * Called when user click any Row Header item.
     *
     * @param p_jRowHeaderView : Clicked Row Header ViewHolder.
     * @param p_nYPosition     : Y (Row) position of Clicked Row Header item.
     */

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }

    private void showToast(String setMessage){
        if (mssgeToast == null){
            mssgeToast = Toast.makeText(ctxContext, "",Toast.LENGTH_SHORT);
        }

        mssgeToast.setText(setMessage);
        mssgeToast.show();
    }

    public void pushFragment(Context context){
        FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
        PopupContinue pcf = new PopupContinue();

        pcf.show(fm, "");
        // FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();

    }

}
