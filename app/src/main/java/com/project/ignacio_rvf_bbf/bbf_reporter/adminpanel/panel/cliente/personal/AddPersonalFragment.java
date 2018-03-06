package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.panel.cliente.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.PersonalUp;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AddPersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPersonalFragment extends Fragment {

    private EditText Rut, Nombre, Apellidos, Categoria, Celular, Mail, Direccion, Fono;
    private Button buttonPersonal;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseReference databaseReference;

    public AddPersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPersonalFragment newInstance(String param1, String param2) {
        AddPersonalFragment fragment = new AddPersonalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference("personal");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_personal, container, false);

        Rut = view.findViewById(R.id.etextNorma);
        Nombre = view.findViewById(R.id.etextNombre);
        Apellidos = view.findViewById(R.id.etextApellido);
        Categoria = view.findViewById(R.id.etextCategoria);
        Celular = view.findViewById(R.id.etextCelular);
        Mail = view.findViewById(R.id.etextMail);
        Direccion = view.findViewById(R.id.etextDireccion);
        Fono = view.findViewById(R.id.etextFono);

        buttonPersonal = view.findViewById(R.id.btnPersonal);

        buttonPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushPersonal();

                MainPersonalFragment mpf = new MainPersonalFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main_admin, mpf)
                        .commit();
            }
        });



        return view;
    }

    //Metodo de almacenamiento firebase
    public void pushPersonal(){
        String rut = Rut.getText().toString().trim();
        String nombre = Nombre.getText().toString().trim();
        String apellidos = Apellidos.getText().toString().trim();
        String categoria = Categoria.getText().toString().trim();
        String celular = Celular.getText().toString().trim();
        String mail = Mail.getText().toString().trim();
        String direccion = Direccion.getText().toString().trim();
        String fono = Fono.getText().toString().trim();

        if(!TextUtils.isEmpty(rut)&& !TextUtils.isEmpty(nombre)&& !TextUtils.isEmpty(apellidos)&& !TextUtils.isEmpty(categoria)&&
                !TextUtils.isEmpty(celular)&&!TextUtils.isEmpty(direccion)&&!TextUtils.isEmpty(mail)&&!TextUtils.isEmpty(fono)) {

            String id = databaseReference.push().getKey();

            PersonalUp personal = new PersonalUp(id, rut, nombre, apellidos, categoria, celular, mail, direccion, fono);

            databaseReference.child(id).setValue(personal);
            Toast.makeText(getContext(), "Personal Añadido",
                    Toast.LENGTH_SHORT).show();

           }else{

            Toast.makeText(getContext(), "Faltan datos por completar", Toast.LENGTH_SHORT).show();

           }
        }
}
