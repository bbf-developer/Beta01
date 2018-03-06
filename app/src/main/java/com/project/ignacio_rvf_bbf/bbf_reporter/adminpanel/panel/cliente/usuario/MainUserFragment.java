package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.panel.cliente.usuario;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ignacio_rvf_bbf.bbf_reporter.LoginActivity;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.MainAdminActivity;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin.UserAdd;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainUserFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    DatabaseReference myData;
    private EditText eUser, ePassword;
    private Button addUser;
    private Switch switcher;

    private String atribb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MainUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainUserFragment newInstance(String param1, String param2) {
        MainUserFragment fragment = new MainUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        myData = FirebaseDatabase.getInstance().getReference("user");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_user, container, false);

        eUser = view.findViewById(R.id.editUser);
        ePassword = view.findViewById(R.id.editPassword);
        addUser = view.findViewById(R.id.btnAdduser);
        switcher = view.findViewById(R.id.switchAtributo);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });

        switcher.setChecked(false);
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bCheck) {
                if(bCheck){
                    atribb = "admin";
                }else{
                    atribb = "usuario";
                }

            }
        });


      return view;
    }

    private void registerUser(){

        String user = eUser.getText().toString();
        String password = ePassword.getText().toString();

        if(TextUtils.isEmpty(user)){
            Toast.makeText(getContext(),"Complete mail",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password) && ePassword.getText().length() < 5){
            Toast.makeText(getContext(),"Escriba clave sobre 5 letras",Toast.LENGTH_LONG).show();
            return;
        }


        firebaseAuth.createUserWithEmailAndPassword(user, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    newUser();
                    Toast.makeText(getContext(), "Usuario Registrado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), MainAdminActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getContext(), "Error al Registrar", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private void newUser(){

        String mail = eUser.getText().toString();
        String passw = ePassword.getText().toString();
        String attrib = atribb;

        String userId = myData.push().getKey();

        UserAdd user = new UserAdd(userId,mail,passw,attrib);

        myData.child(userId).setValue(user);

    }

}
