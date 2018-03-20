package com.project.ignacio_rvf_bbf.bbf_reporter;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubHogarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubHogarFragment extends Fragment {

    public static final String SHARED_PREF_ZONA="nombreZona";
    public static final String KEY_TEXT_ZONA="KEY_ZONA";

    private LinkedHashMap<String, SubChildInfo> subchildlist = new LinkedHashMap<>();
    private SubDataAdapter sublistaadapter;
    private ArrayList<SubChildInfo> subdeptlist = new ArrayList<>();
    private ExpandableListView subexplistview;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String parametro1;

    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubHogarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubHogarFragment newInstance(String param1, String param2) {
        SubHogarFragment fragment = new SubHogarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_hogar, container, false);

        LoadData();

        subexplistview = view.findViewById(R.id.subexplistview);
        sublistaadapter = new SubDataAdapter(SubHogarFragment.this.getActivity(), subdeptlist);
        subexplistview.setAdapter(sublistaadapter);

        subexplistview.expandGroup(0);
        subexplistview.expandGroup(1);

        subexplistview.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                //Listener del grupo de elementos del exp list view para agregar SubGrupos
                // hacia otro fragment o activity dentro de este grupo
                return false;
            }
        });

        subexplistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {

                //GETTING ZONA NAME
                //BUNDLE METHOD
                SubChildInfo info = subdeptlist.get(childPosition);
                Bundle bundle = new Bundle();
                bundle.putString("KEY_ZONA",info.getName().toUpperCase() );

                if(groupPosition == 0 && childPosition == 0){
                    NuevaMedicionFragment rpf = new NuevaMedicionFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    rpf.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                            .commit();
                }

                return false;
            }
        });

        return view;
    }

    private void expand_all(){
        int count = sublistaadapter.getGroupCount();
        for(int i=0;i<count;i++){
            subexplistview.expandGroup(i);
        }
    }

    private void collapse_all(){
        int count = sublistaadapter.getGroupCount();
        for(int i=0;i<count;i++){
            subexplistview.collapseGroup(i);
        }
    }

    private void LoadData(){
        addProduct("Piso","Piso");
        addProduct("Piso","Abertura Canales de Fundido");

        addProduct("Niveles","Entradas Aire Primario");
        addProduct("Niveles","Entradas Aire Secundario");
        addProduct("Niveles","Quemadores de Licor");
        addProduct("Niveles","Quemadores de Partida");
        addProduct("Niveles","Entradas de Aire Terciario");
        addProduct("Niveles","Mirillas de Inspección");
        addProduct("Niveles","Quemadores de Carga");
        addProduct("Niveles","Entrada Gases No Condensados");
        addProduct("Niveles","Escotillas de Ingreso (MANHOLE)");

    }

    private int addProduct(String brand, String Car){
        int groupPosition;
        ArrayList<SubChildInfo> arrayList;
        SubChildInfo headerinfo = subchildlist.get(brand);
        if(headerinfo==null){
            headerinfo = new SubChildInfo();
            headerinfo.setName(brand);
            subchildlist.put(brand, headerinfo);
            subdeptlist.add(headerinfo);
        }

        ArrayList<SubGroupInfo> grouplist = headerinfo.getList();
        int listsize = grouplist.size();
        listsize++;

        SubGroupInfo detailinfo = new SubGroupInfo();
        detailinfo.setName(Car);
        detailinfo.setSequence(String.valueOf(listsize));
        grouplist.add(detailinfo);
        headerinfo.setList(grouplist);

        groupPosition = subdeptlist.indexOf(headerinfo);

        return groupPosition;
    }


}
