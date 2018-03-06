package com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.RepcalderaFrag;

import java.util.ArrayList;


public class SubShowClienteFragment extends Fragment {

    ArrayList<ShowPlanta> myList = new ArrayList<>();

    private ListView newListView;
    private DatabaseReference gDatabase;


    //Shared Preferences Method
    public static final String SHARED_PREF_TEXT = "prefshared";
    public static final String KEY_TEXT1 ="param";

    public SubShowClienteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_show_cliente, container, false);
        newListView = view.findViewById(R.id.lvplanta);
        final ArrayAdapter<ShowPlanta> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, myList);
        newListView.setAdapter(arrayAdapter);

        //TIENE QUE SER LA CONSULTA DE 1 SOLO PARAMETRO EN LISTENER.
        gDatabase.child("cliente").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ShowPlanta show  = dataSnapshot.getValue(ShowPlanta.class);
                //El Array solo crea get para 1 elemento
                myList.add(show);
                //ShowCliente key = dataSnapshot.getKey();
                //mKeys.add(key);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // String data = dataSnapshot.getValue(String.class);
                // String key = dataSnapshot.getKey();
                // int index = mKeys.indexOf(key);
                // arrayAdapter.notifyDataSetChanged();

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

        newListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //IMPLEMENTAR METODO SHARED PREFERENCES
                ShowPlanta select = myList.get(i);
                //bundle.putString();
                SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_TEXT, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_TEXT1, select.getNomplanta().toUpperCase());
                editor.commit();

                RepcalderaFrag rpf = new RepcalderaFrag();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                        .commit();

            }
        });

        return view;
    }

}
