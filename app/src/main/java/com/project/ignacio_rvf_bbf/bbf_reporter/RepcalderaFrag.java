package com.project.ignacio_rvf_bbf.bbf_reporter;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


/**
 *
 * A simple {@link Fragment} subclass.
 *
 */

public class RepcalderaFrag extends Fragment {
    private LinkedHashMap<String, ChildInfo> childlist = new LinkedHashMap<>();
    private DataAdapter listaadapter;
    private ArrayList<ChildInfo> deptlist = new ArrayList<>();
    private ExpandableListView explistview;


    //TODO:SHARED PREFS DATA
    public static final String SHARED_PREFS_CALDERA = "tipoCald";
    public static final String KEY_CALDERA = "KEY_CALDERA";
    public static final String KEY_CLIENTE = "KEY_CLIENTE";
    public static final String KEY_PLANTA = "KEY_PLANTA";
    public static final String KEY_LINEA = "KEY_LINEA";

    // TODO: Recepcion de parametros
    private String rCliente;
    private String rPlanta;
    private String rLinea;

    /**
     *
     *
     */
    // TODO: Rename and change types and number of parameters
    public static RepcalderaFrag newInstance(String param1, String param2) {
        RepcalderaFrag fragment = new RepcalderaFrag();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rCliente = this.getArguments().getString("KEY_CLIENTE");
        rPlanta = this.getArguments().getString("KEY_PLANTA");
        rLinea = this.getArguments().getString("KEY_LINEA");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.fragment_repcaldera, container, false);
     getActivity().setTitle("Menú Configuración");

        LoadData();

        explistview = view.findViewById(R.id.explistview);
        listaadapter = new DataAdapter(RepcalderaFrag.this.getActivity(), deptlist);
        explistview.setAdapter(listaadapter);

        explistview.expandGroup(0);
        explistview.expandGroup(1);

        explistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                //Listener del Grupo de elementos del expandable list view
                return false;

            }
        });

        explistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                //sub menú method

                if(groupPosition == 0 && childPosition == 0){

                    SubHogarFragment rpf = new SubHogarFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                            .commit();
                }

                if(groupPosition == 0 && childPosition == 1){
                    SubHogarAltoFragment rpf = new SubHogarAltoFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                            .commit();
                }

                if(groupPosition == 0 && childPosition == 2){
                    SubBancoGenFragment rpf = new SubBancoGenFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                            .commit();
                }

                if(groupPosition == 0 && childPosition == 3){
                    SubEconomizadorFragment rpf = new SubEconomizadorFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                            .commit();
                }

                //RECEPCIONAR 3 ULTIMOS VALORES + 1 DE MENUCALDERA
                ChildInfo info = deptlist.get(groupPosition);

                SharedPreferences sharedPref3 = getContext().getSharedPreferences(SHARED_PREFS_CALDERA, 0);
                SharedPreferences.Editor editor = sharedPref3.edit();
                editor.putString(KEY_CALDERA, info.getName().toUpperCase());
                editor.putString(KEY_CLIENTE, rCliente);
                editor.putString(KEY_PLANTA, rPlanta );
                editor.putString(KEY_LINEA, rLinea);
                editor.commit();


                return false;
            }


        });

        return view;
    }


    private void expand_all(){
        int count = listaadapter.getGroupCount();
        for(int i=0;i<count;i++)
        {
            explistview.expandGroup(i);
        }
    }

    private void collapse_all(){
        int count = listaadapter.getGroupCount();
        for(int i=0;i<count;i++){
            explistview.collapseGroup(i);
        }
    }

    private void LoadData(){
        addProduct("Recuperadora","Hogar");
        addProduct("Recuperadora","Hogar Alto");
        addProduct("Recuperadora","Banco Generador");
        addProduct("Recuperadora","Economizadores");

        addProduct("Poder","");

    }

    /**
     * Main del Expandable List View
     *
     * */

    private int addProduct(String brand, String Car){
        int groupPosition;
        ArrayList<ChildInfo> arrayList;
        ChildInfo headerinfo = childlist.get(brand);
        if(headerinfo==null){
            headerinfo = new ChildInfo();
            headerinfo.setName(brand);
            childlist.put(brand,headerinfo);
            deptlist.add(headerinfo);

        }

        ArrayList<GroupInfo> grouplist = headerinfo.getList();
        int listsize = grouplist.size();
        listsize++;

        GroupInfo detailinfo = new GroupInfo();
        detailinfo.setName(Car);
        detailinfo.setSequence(String.valueOf(listsize));
        grouplist.add(detailinfo);
        headerinfo.setList(grouplist);

        groupPosition = deptlist.indexOf(headerinfo);

        return groupPosition;
    }

}
