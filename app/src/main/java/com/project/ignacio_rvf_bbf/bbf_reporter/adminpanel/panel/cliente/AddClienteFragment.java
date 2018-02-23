package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.panel.cliente;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.ClienteUp;


public class AddClienteFragment extends Fragment {
    // TODO:FRAGMENT PARA GUARDAR NUEVO CLIENTE

   private Button btnGuardar;

    private EditText txtRut;
    private EditText txtRazonsocial;
    private EditText txtGiro;
    private EditText txtCodplanta;
    private EditText txtPlanta;
    private EditText txtDireccion;
    private EditText txtContacto;
    private EditText txtMail;
    private EditText txtFono;
    private EditText txtLinea;

    DatabaseReference databaseReference;
    ProgressBar progressBar;

    public AddClienteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("cliente");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_cliente, container, false);

        txtRut = view.findViewById(R.id.etextRut);
        txtRazonsocial = view.findViewById(R.id.etextNombre);
        txtGiro = view.findViewById(R.id.etextApellido);
        txtCodplanta = view.findViewById(R.id.etextCategoria);
        txtPlanta = view.findViewById(R.id.etextCelular);
        txtDireccion = view.findViewById(R.id.etextDireccion);
        txtContacto = view.findViewById(R.id.etextFono);
        txtMail = view.findViewById(R.id.etextMail);
        txtFono = view.findViewById(R.id.etextFono);
        txtLinea = view.findViewById(R.id.etLinea);

        btnGuardar = view.findViewById(R.id.btnPersonal);
        //progressBar = view.findViewById(R.id.add_progress);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushAddCliente();
            }
        });

        return view;
    }

    //METODO DE ALMACENAMIENTO EN TIEMPO REAL FIREBASE
    public void pushAddCliente(){
        String rut = txtRut.getText().toString().trim();
        String razonsoc = txtRazonsocial.getText().toString().trim();
        String giro = txtGiro.getText().toString().trim();
        String codplanta = txtCodplanta.getText().toString().trim();
        String nomplanta = txtPlanta.getText().toString().trim();
        String direccion = txtDireccion.getText().toString().trim();
        String contacto = txtContacto.getText().toString().trim();
        String mail = txtMail.getText().toString().trim();
        String fono = txtFono.getText().toString().trim();
        int linea = Integer.parseInt(txtLinea.getText().toString().trim());

        //TESTING UPLOAD DATA TO FIREBASE
        if(!TextUtils.isEmpty(rut)&& !TextUtils.isEmpty(razonsoc)&& !TextUtils.isEmpty(giro)&& !TextUtils.isEmpty(codplanta)&&
                !TextUtils.isEmpty(nomplanta)&&!TextUtils.isEmpty(direccion)&&!TextUtils.isEmpty(contacto)&&!TextUtils.isEmpty(mail)&&
                !TextUtils.isEmpty(fono)){

            String id = databaseReference.push().getKey();

            ClienteUp client = new ClienteUp(id, rut, razonsoc, giro, codplanta,
                    nomplanta, direccion, contacto, mail, fono, linea);

            databaseReference.child(id).setValue(client);
            //showDialog();
            Toast.makeText(getContext(), "Cliente Añadido",
                    Toast.LENGTH_SHORT).show();

            MainClienteFragment rpf = new MainClienteFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main_admin, rpf)
                    .commit();


        }else{
            Toast.makeText(getContext(), "Falta Completar",
                    Toast.LENGTH_SHORT).show();
        }


    }

    public void showDialog(){


        //Dialogo de progreso al logear la app
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("");
        progressDialog.setMessage("Cargando... Espere Porfavor...");
        progressDialog.show();
        progressDialog.dismiss();

    }

}
