package com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.local_sql.DBManager;

/**
 * Created by Ignacio-RVF-BBF on 16/02/2018.
 *
 * Matriz auxiliar que carga las celdas escritas en
 * base de datos a nivel local.
 */

public class MainFragment extends Fragment {

    private Context mContext;
    private DBManager manager;
    Cursor cursor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RelativeLayout fragment_container = (RelativeLayout) view
                .findViewById(R.id.fragment_container);

        manager = new DBManager(mContext);
        //RESOLVER METODO DE CARGA DE DATOS EN CELDA POR SQL LITE

        return view;
    }
}
