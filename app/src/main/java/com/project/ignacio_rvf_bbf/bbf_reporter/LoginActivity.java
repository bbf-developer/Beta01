package com.project.ignacio_rvf_bbf.bbf_reporter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.MainAdminActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private ProgressDialog progressDialog;

    private Button btnAdmin;
    private Button boton;

    private Button btnIngresar;
    private int countDialog=50;

    private TextView textEmail;
    private TextView textPassword;

    private FirebaseAuth mAuth;

    DatabaseReference myData;

    ArrayList<String> myList1 = new ArrayList<>();
    ArrayList<String> myList2 = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
        myData = FirebaseDatabase.getInstance().getReference("user");

        myData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        HashMap<String, Object> myMap = (HashMap<String, Object>) data.getValue();
                        Log.e("TAG", String.valueOf(myMap.get("mail")));
                        Log.e("TAG", String.valueOf(myMap.get("atributo")));

                        myList1.add(String.valueOf(myMap.get("mail")));
                        myList2.add(String.valueOf(myMap.get("atributo")));
                    }

                }else{
                    Toast.makeText(getBaseContext(), "Sin Usuarios Añadidos", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),"Error en Base de datos", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        /** Boton de prueba para pasar un activity a otro
         * */
        boton = findViewById(R.id.loginbutton);
        btnAdmin = findViewById(R.id.btnAdmin);

        // FIREBASE LOGIN BUTTON
        btnIngresar = findViewById(R.id.btnIngresa);

        /**
         * Text Views
         * */
        textEmail = findViewById(R.id.etextMail);
        textPassword = findViewById(R.id.etPassword);


        //INGRESO AUXILIAR

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, MainAdminActivity.class);
                        startActivity(intent);
                 showDialog();
            }
        });


        //INGRESO REAL
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();

            }
        });




    }

    private void userLogin(){
        final String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Olvido el e-mail",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Olvido la clave",Toast.LENGTH_LONG).show();
            return;
        }

        showDialog();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                        for(int i=0; i < myList1.size(); i++){
                            if(myList1.get(i).contains(email)){
                                 if(myList2.get(i).contains("admin")){
                                        Intent intent = new Intent(LoginActivity.this, MainAdminActivity.class);
                                        startActivity(intent);
                                        finish();

                                  }else{

                                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                        startActivity(intent);
                                        finish();
                                     }

                            }

                         }

                        }else{
                            //Log.w(TAG, "singInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Clave o Email Erroneo",
                                    Toast.LENGTH_LONG).show();

                        }
                        hideDialog();
                    }
                });
    }

    private void userLogOn(){



    }

    public void showDialog(){

            //Dialogo de progreso al logear la app
            ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setTitle("Bienvenido");
            progressDialog.setMessage("Cargando... Espere Porfavor...");
            progressDialog.show();
    }

    public void hideDialog(){
        //Dialogo de progreso al logear la app
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.dismiss();
    }

}