package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.panel.cliente.personal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.PersonalUp;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.ShowPersonal;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.panel.cliente.AddClienteFragment;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.ShowPlanta;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link MainPersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPersonalFragment extends Fragment {

    private FirebaseDatabase database;
    private DatabaseReference reference;

    ArrayList<ShowPersonal> myList2 = new ArrayList<>();
    private ListView myListView;

    private Button btnAddPersonal;

    private TextView empty;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainPersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainPersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainPersonalFragment newInstance(String param1, String param2) {
        MainPersonalFragment fragment = new MainPersonalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("personal");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_personal, container, false);

        btnAddPersonal = view.findViewById(R.id.btnAddPersonal);

        myListView = view.findViewById(R.id.listvPersonal);
        final ArrayAdapter<ShowPersonal> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, myList2);
        myListView.setAdapter(arrayAdapter);

        empty = view.findViewById(R.id.vacio);

        btnAddPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPersonalFragment rpf = new AddPersonalFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main_admin, rpf)
                        .commit();
            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ShowPersonal personal = dataSnapshot.getValue(ShowPersonal.class);
                myList2.add(personal);
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



        return view;
    }

    private void checkEmpty(){
        if(myList2.size() == 0){
            myListView.setVisibility(View.INVISIBLE);
            empty.setVisibility(View.VISIBLE);
        }else{
            myListView.setVisibility(View.VISIBLE);
            empty.setVisibility(View.INVISIBLE);
        }
    }

}
