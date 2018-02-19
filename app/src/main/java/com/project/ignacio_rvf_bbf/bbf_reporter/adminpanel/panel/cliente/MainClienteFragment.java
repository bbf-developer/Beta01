package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.panel.cliente;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.SubHogarFragment;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.ClienteAdapter;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.ClienteTest;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.ShowClienteFragment;

import java.util.ArrayList;
import java.util.List;


public class MainClienteFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<ClienteTest> result;
    private ClienteAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main_cliente, container, false);

    database = FirebaseDatabase.getInstance();
    reference = database.getReference("cliente");

    result = new ArrayList<>();

    recyclerView = (RecyclerView) view.findViewById(R.id.rvcliente_list);
    recyclerView.setHasFixedSize(true);

    btn = view.findViewById(R.id.btnAddcliente);

    LinearLayoutManager linear = new LinearLayoutManager(getActivity());
    linear.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(linear);

    //createResult();
        updateList();

    adapter = new ClienteAdapter(result);
    recyclerView.setAdapter(adapter);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AddClienteFragment rpf = new AddClienteFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main_admin, rpf)
                    .commit();
        }
    });

    return view;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case 0:
                removeCliente(item.getGroupId());
                break;
            case 1:
        }

        return super.onContextItemSelected(item);
    }


    /**
     * RECYCLER TESTING
     * */
    /* private void createResult(){

        for(int i=0; i<5;i++){
            result.add(new ClienteTest("rut","razonsoc","giro","codplanta","planta","direccion","contacto","mail","555"));
        }

    }*/

    private void updateList(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                result.add(dataSnapshot.getValue(ClienteTest.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ClienteTest cliente = dataSnapshot.getValue(ClienteTest.class);
                int index = getItemIndex(cliente);
                result.set(index, cliente);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ClienteTest cliente = dataSnapshot.getValue(ClienteTest.class);
                int index = getItemIndex(cliente);
                result.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Indexa o cuenta segun cantidad de keys que tiene la tabla
     * ver más
     * https://www.youtube.com/watch?v=PJTs8vZKlWw
     * 19:56
     * */
     private int getItemIndex(ClienteTest cliente){
        int index = -1;
        for(int i=0; i < result.size(); i++){
            if(result.get(i).id.equals(cliente.id)){
                index = i;
                break;
              }
             }
             return index;
        }

     private void removeCliente(int position){
         reference.child(result.get(position).id).removeValue();
     }

     private void changeCliente(int position){
         ClienteTest cliente = result.get(position);

     }
}
