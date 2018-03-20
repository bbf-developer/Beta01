package com.project.ignacio_rvf_bbf.bbf_reporter.list;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.RepcalderaFrag;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.ClienteUp;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.expandable.ChildInf;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.expandable.DataAdapterFire;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.expandable.GroupInf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowPlantaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowPlantaFragment extends Fragment {

    public static final String SHARED_PREFS_LINEA="sharedLinea";
    public static final String KEY_LINEA="text";
    public static final String KEY_PLANTA="text2";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String checkNull;

    public int setGroup;

    //TODO: VARIABLES DEL LAYER.
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ArrayList<String> myList = new ArrayList<>();
    //*************************************************
    private LinkedHashMap<String, ChildInf> childList = new LinkedHashMap<>();
    private DataAdapterFire myAdapter;
    private ArrayList<ChildInf> arrayChild = new ArrayList<>();
    private ExpandableListView expandableList;

    private ArrayList<ClienteUp> arrayCliente = new ArrayList<>();

    private FirebaseDatabase firebase;
    private DatabaseReference dataRef;

    private TextView empty;

    //ALMACENAMIENTO DE PARAMETROS PARA BUNDLE.
    private String cliente1;

    public ShowPlantaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowPlantaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowPlantaFragment newInstance(String param1, String param2) {
        ShowPlantaFragment fragment = new ShowPlantaFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataRef = FirebaseDatabase.getInstance().getReference();

        cliente1 = this.getArguments().getString("KEY_CLIENTE");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_planta, container, false);
        load();
        //testLoad();
        expandableList = view.findViewById(R.id.expandPlanta);
        empty = view.findViewById(R.id.checkEmpty);

        //Obtener el adapter del expandable
        myAdapter = new DataAdapterFire(ShowPlantaFragment.this.getActivity(), arrayChild);
        expandableList.setAdapter(myAdapter);

       expandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
           int previous = -1;
           @Override
           public void onGroupExpand(int groupPosition) {
               if(groupPosition != previous){
                   expandableList.collapseGroup(previous);
                   previous = groupPosition;
               }

           }
       });

      final Bundle bundle = new Bundle();
      bundle.putString("KEY_CLIENTE", cliente1);
       expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
           @Override
           public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
               final ClienteUp select = arrayCliente.get(i);
               bundle.putString("KEY_PLANTA", select.getNomplanta().toUpperCase());
               return false;
           }
       });

       expandableList.collapseGroup(setGroup);

       expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
           @Override
           public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
               String position = String.valueOf(i1 + 1);
               //editor.putString(KEY_LINEA, position);
                bundle.putString("KEY_LINEA", position);

               RepcalderaFrag rpf = new RepcalderaFrag();
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               rpf.setArguments(bundle);
               fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                       .commit();

               return false;
           }
       });

        return view;
    }



    private void expand_all(){
        int count = myAdapter.getGroupCount();
        for(int i=0;i<count;i++)
        {
            expandableList.expandGroup(i);
        }
    }

    private void collapse_all(){
        int count = myAdapter.getGroupCount();
        for(int i=0;i<count;i++){
            expandableList.collapseGroup(i);
        }
    }

    private int addProduct(String header, String child){
        int groupPosition;
        ArrayList<ChildInf> arrayList;
        ChildInf headerinfo = childList.get(header);
        if(headerinfo==null){
            headerinfo = new ChildInf();
            headerinfo.setName(header);
            childList.put(header ,headerinfo);
            arrayChild.add(headerinfo);
        }

        ArrayList<GroupInf> grouplist = headerinfo.getList();
        int listsize = grouplist.size();
        listsize++;

        GroupInf detailinfo = new GroupInf();
        detailinfo.setName(child);
        detailinfo.setSequence(String.valueOf(listsize));
        grouplist.add(detailinfo);
        headerinfo.setList(grouplist);
        groupPosition = arrayChild.indexOf(headerinfo);

        return groupPosition;
    }

    public void testLoad(){
        addProduct("CHOLGUAN","Linea 1");
        addProduct("ITATA","Linea 2");
    }

    //CARGAR DATOS DE FIREBASE
    public void load(){

        dataRef.child("cliente").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                  ClienteUp cup = dataSnapshot.getValue(ClienteUp.class);
                  arrayCliente.add(cup);
                  String planta = cup.getNomplanta().toUpperCase();
                  int linea = cup.getLinea();
                  //checkNull = String.valueOf(linea);
                  for (int i = 0; i < linea; i++) {
                      addProduct(planta, "Linea" + " " + String.valueOf(i + 1));
                  }
                  myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //END READ CHILD FIREBASE
    }

}
