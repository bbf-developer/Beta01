package com.project.ignacio_rvf_bbf.bbf_reporter.list;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.ignacio_rvf_bbf.bbf_reporter.NuevaMedicionFragment;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.RepcalderaFrag;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.ShowCliente;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.ShowPlanta;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.SubShowClienteFragment;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */

public class ShowClienteFragment extends Fragment {
    //TODO: SIMPLE LECTOR DE DATOS FIREBASE

    ArrayList<ShowCliente> myList = new ArrayList<>();
    ArrayList<ShowCliente> mKeys = new ArrayList<>();

    private Context mContext;
    private ListView newListView;

    private DatabaseReference gDatabase;

    //Shared Preferences
    public static final String SHARED_PREFS_FILE = "sharedPrefs";
    public static final String KEY_TEXT ="text";

    public ShowClienteFragment() {
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
       View view = inflater.inflate(R.layout.fragment_show_cliente, container, false);
       newListView = view.findViewById(R.id.lvCliente);
       final ArrayAdapter<ShowCliente> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, myList);
       newListView.setAdapter(arrayAdapter);

        gDatabase.child("cliente").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              ShowCliente show  = dataSnapshot.getValue(ShowCliente.class);
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
            ShowCliente select = myList.get(i);
            //bundle.putString();
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS_FILE, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_TEXT, select.getRazonsoc().toUpperCase());
            editor.commit();

            ShowPlantaFragment rpf = new ShowPlantaFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, rpf)
                    .commit();
        }
    });
      return view;
    }


}
