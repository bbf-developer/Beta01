package com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.ignacio_rvf_bbf.bbf_reporter.*;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.ShowPersonal;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.CreaMedicion;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.ShowTableDate;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.ShowPlantaFragment;
import com.project.ignacio_rvf_bbf.bbf_reporter.local_sql.DBManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContinueFragment#newInstance} factory method to
 * create an instance of this fragment.
 * PASAR PARAMETRO DE LETRA Y NUMERO DE TUBOS AL MAINFRAGMENT DESDE LA BASE DE DATOS
 *
 */
public class ContinueFragment extends Fragment {


    private Context xContext;

    private Cursor cursor;
    private ListView list;
    private TextView empty;

    ArrayList<String> List1 = new ArrayList<>();
    ArrayList<Integer> List2 = new ArrayList<>();

    private int position;

    DatabaseReference myRef;

    ArrayList<ShowTableDate> myList = new ArrayList<>();



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
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myRef = FirebaseDatabase.getInstance().getReference("save");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_continue, container, false);

        list = view.findViewById(R.id.listvContinue);
        empty = view.findViewById(R.id.checkEmpty1);
        final ArrayAdapter<ShowTableDate> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, myList);
        list.setAdapter(arrayAdapter);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                ShowTableDate show = dataSnapshot.getValue(ShowTableDate.class);
                myList.add(show);
                arrayAdapter.notifyDataSetChanged();
                checkEmpty();
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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                position = (i);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snap : dataSnapshot.getChildren()) {
                            HashMap<String, Object> myHash = (HashMap<String, Object>) snap.getValue();

                            Log.e("TAG", String.valueOf( myHash.get("limiteCol")));
                            Log.e("TAG", String.valueOf( myHash.get("limiteTubo")));
                            List1.add(String.valueOf(myHash.get("limiteCol")));
                            List2.add(Integer.parseInt(String.valueOf(myHash.get("limiteTubo"))));

                        }

                        if(List1.size() < 0 && List2.size() < 0) {

                            Toast.makeText(getContext(),"Error al cargar la lista", Toast.LENGTH_SHORT).show();

                        }else{

                            String limiteColumna = List1.get(position);
                            int limiteFila = List2.get(position);
                            Bundle bundle = new Bundle();
                            bundle.putString("LETRA", limiteColumna);
                            bundle.putInt("nTUBO", limiteFila);

                            com.project.ignacio_rvf_bbf.bbf_reporter.MainFragment nmf = new com.project.ignacio_rvf_bbf.bbf_reporter.MainFragment();
                            nmf.setArguments(bundle);
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.main_content, nmf)
                                    .commit();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

        });

        return view;
    }

    private void checkEmpty(){
        if(myList.size() == 0){
            list.setVisibility(View.INVISIBLE);
            empty.setVisibility(View.VISIBLE);
        }else{
            list.setVisibility(View.VISIBLE);
            empty.setVisibility(View.INVISIBLE);
        }
    }

}
