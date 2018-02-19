package com.project.ignacio_rvf_bbf.bbf_reporter.holder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.CellModel;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.ColumnHeaderModel;

import static com.project.ignacio_rvf_bbf.bbf_reporter.popup_medicion.PopupClickFragment.KEY_CELL1;
import static com.project.ignacio_rvf_bbf.bbf_reporter.popup_medicion.PopupClickFragment.SHARED_PREF_CELL;
import static com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener.KEY_NUM1;
import static com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener.SHARED_PREF_NUM1;

/**
 * Created by Ignacio-RVF-BBF on 23/01/2018.
 * @param
 */

public class CellViewHolder extends AbstractViewHolder{

    public final TextView cell_textview;
    public final LinearLayout cell_container;
    public Context mContext;
    private String showParam;


    /*PRINCIPAL*/
    public CellViewHolder(View itemView) {
        super(itemView);
        cell_textview = itemView.findViewById(R.id.cell_data);
        cell_container = itemView.findViewById(R.id.cell_container);
    }

    /*MODIFICA LAS CELDAS CREADAS*/
    @Override
    public void setSelected(SelectionState p_nSelectionState){
        super.setSelected(p_nSelectionState);

        if(p_nSelectionState == SelectionState.SELECTED) {
            cell_textview.setTextColor(ContextCompat.getColor(cell_textview.getContext(), R.color.cell_background_color));

        }
        else{

            cell_textview.setTextColor(ContextCompat.getColor(cell_textview.getContext(), R.color.cell_text_color));

        }

        if(p_nSelectionState != SelectionState.UNSELECTED){
            cell_textview.setText("CHK");
        }

    }

}
