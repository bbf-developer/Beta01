package com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.local_sql.DBManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContinueFragment#newInstance} factory method to
 * create an instance of this fragment.
 * AQUI SE INSTANCIA LISTVIEW CON LAS TABLAS CREADAS EN LA BASE DE DATOS LOCAL.
 */
public class ContinueFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //TODO: Variables para cargar datos de las tablas almacenadas localmente
    private Context xContext;

    private DBManager manager;
    private Cursor cursor;
    private ListView list;
    SimpleCursorAdapter adapter;

    public ContinueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContinueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContinueFragment newInstance(String param1, String param2) {
        ContinueFragment fragment = new ContinueFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
       // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_continue, container, false);

        manager = new DBManager(xContext);
        //lista = view.findViewById(R.id.ListView);

        String[] from = new String[]{manager.TABLE_NAME};
        int[] to = new int[] {android.R.id.text1};

        cursor = manager.loadTable();
        //adapter = new SimpleCursorAdapter(xContext, android.R.layout.two_line_list_item, from, to);
        //lista.setAdapter(adapter)

        return view;
    }
}
