package com.project.ignacio_rvf_bbf.bbf_reporter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.CreaMedicion;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.ShowClienteFragment;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.ShowCliente;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.SubShowClienteFragment;
import com.project.ignacio_rvf_bbf.bbf_reporter.popup_medicion.PopupClickFragment;

import java.util.Calendar;

import static com.project.ignacio_rvf_bbf.bbf_reporter.SubHogarFragment.KEY_TEXT_ZONA;
import static com.project.ignacio_rvf_bbf.bbf_reporter.SubHogarFragment.SHARED_PREF_ZONA;
import static com.project.ignacio_rvf_bbf.bbf_reporter.list.ShowClienteFragment.KEY_TEXT;
import static com.project.ignacio_rvf_bbf.bbf_reporter.list.ShowClienteFragment.SHARED_PREFS_FILE;
import static com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.SubShowClienteFragment.KEY_TEXT1;
import static com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.SubShowClienteFragment.SHARED_PREF_TEXT;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NuevaMedicionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NuevaMedicionFragment extends Fragment {

    private Button button;
    private Context mContext;
    private EditText editEmpresa;

    private EditText editCol1;
    private EditText editFila;
    private EditText editCol2;
    private EditText editTubo1;

    private TextView paramFila1;
    private TextView paramCol;
    private TextView muestraCliente;
    private TextView muestraPlanta;
    private TextView muestraZona;

    private String changeParam1;
    private String changeParam2;
    private String valor3;
    private String valorParam1;

    public static int nTubo;
    public static int checkTubo;

    //TODO: TEST***************
    private Button btnPopup1;

    //TODO: VARIABLES DE LA RECEPCIÓN
    private String cliente;
    private String planta;
    private String caldera;
    private String zona;
    private String zona2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //TODO: Almacenar datos de reconfiguracion de tabla para continuar con el trabajo si es pausado.
    DatabaseReference databaseReference;
    private String fecha;

    //TODO: MEDICION DE LONGITUD DE CARACTERES PARA FILTRO DE LA CONFIGURACIÓN EN COLORES
    char [] abc = {'A', 'B', 'C', 'D','E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M','N','O','P','Q','R','S','T','U','V','W', 'X','Y','Z'};
    char c;

    char letra = 'A';
    char letra2;

    int paramSize1;
    int paramSize2 = 1;

    //checker en base de datos
    private String paramCheck;

    public NuevaMedicionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NuevaMedicionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NuevaMedicionFragment newInstance(String param1, String param2) {
        NuevaMedicionFragment fragment = new NuevaMedicionFragment();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //GUARDAR PARAMETROS PARA SALVAR LA TABLA QUE SE CREO

        databaseReference = FirebaseDatabase.getInstance().getReference("config");

        //Asignacion de escritura en BBDD de fecha a la tabla.
        Calendar cal = Calendar.getInstance();
        int monthofYear = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        fecha = String.valueOf(year + "-" + monthofYear);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_nueva_medicion, container, false);

        button = (Button) view.findViewById(R.id.btnNew);

        editCol1 = (EditText) view.findViewById(R.id.etxtCol1);
        editFila = (EditText) view.findViewById(R.id.etxtFila);
        editCol2 = (EditText) view.findViewById(R.id.etxtCol2);

        //MOSTRAR CLIENTE SELECCIONADO
        muestraCliente = view.findViewById(R.id.textvCliente);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS_FILE, 0);
        cliente = sharedPreferences.getString(KEY_TEXT,"");
        muestraCliente.setText(cliente);

        //MUESTRA PLANTA SELECCIONADA
        muestraPlanta = view.findViewById(R.id.txtZona);
        SharedPreferences sharedPreferences1 = getContext().getSharedPreferences(SHARED_PREF_TEXT, 0);
        planta = sharedPreferences1.getString(KEY_TEXT1,"");
        muestraPlanta.setText(planta);

        //MUESTRA ZONA SELECCIONADA
        muestraZona = view.findViewById(R.id.textvZona);
        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences(SHARED_PREF_ZONA,0);
        zona = sharedPreferences2.getString(KEY_TEXT_ZONA,"");
        muestraZona.setText(zona);

        //PARAMETROS DE REFLEJO
        paramFila1 = (TextView) view.findViewById(R.id.paramFila);
        paramCol = (TextView) view.findViewById(R.id.paramCol);

        //INGRESO DE NUMEROS DE TUBO A INSPECCIONAR.
        editTubo1 = view.findViewById(R.id.editTubo);


        //TODO: FALTA FILTRO DE INGRESO DE PARAMETROS.
        //Automatizar aparicion del item del inicio de la fila
        editCol1.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

         }

         @Override
         public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


         }

         @Override
         public void afterTextChanged(Editable editable) {

             //CAMBIA EL TEXTVIEW SI HAY MAS DE 1 LONGITUD DE CARACTER EN
             if(editCol1.getText().length() >= 0) {
                 paramFila1.setText(editCol1.getText().toString());

             }else{
                 //letra = 'A';
             }

            // Toast.makeText(getContext(), "PARAMETROA" + " " +paramSize1,Toast.LENGTH_LONG).show();

         }
     });

        //Automatizar cambio de parametro del fin de la fila
        editFila.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i1, int ii1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editFila.getText().length() >= 0) {
                    paramCol.setText(editFila.getText().toString());

                }

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String verificar = editTubo1.getText().toString();
                final String checkCol = editCol2.getText().toString();

                //VERIFICA SI HAY CAMPOS VACIOS
                if (!TextUtils.isEmpty(verificar) && !TextUtils.isEmpty(checkCol)) {

                    if (editTubo1 != null) {

                        checkTubo = Integer.parseInt(editTubo1.getText().toString());
                    }
                    //Single Value Listener PARA COMPROBAR CONFIGURACIONES PASADAS O GUIAR A EQUIPO DE TRABAJO.
                      if (paramCheck != null) {
                        //SI EL PARAMETRO NO ES NULL DEJA PASAR, SI LO ES PEGA EL ELSE

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("config")
                                .child(fecha).child(cliente).child(planta).child(zona);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                CreaMedicion cm = dataSnapshot.getValue(CreaMedicion.class);
                                assert cm != null;
                                final String check1 = cm.getCliente1();
                                paramCheck = check1;
                                String check2 = cm.getPlanta1();
                                String check3 = cm.getZona1();
                                final int check4 = cm.getLimiteTubo();
                                final String check5 = cm.getLimiteCol();

                                if (check1.equals(cliente) && check2.equals(planta) && check3.equals(zona) && check4 == (checkTubo) && check5.equals(checkCol)) {

                                    final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                                    alertDialog.setTitle("!Matriz Registrada¡");
                                    alertDialog.setMessage("Esta configuración de matriz ya existe. ¿Desea continuar la actividad?");
                                    alertDialog.setIcon(R.drawable.ic_report_alert);
                                    alertDialog.setButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(getContext(), "SE TRASPASAN PARAMETROS CONFIGURADOS", Toast.LENGTH_LONG).show();
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("nTUBO", check4);
                                            bundle.putString("LETRA", check5);
                                            MainFragment nmf = new MainFragment();
                                            nmf.setArguments(bundle);
                                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                            fragmentManager.beginTransaction().replace(R.id.main_content, nmf)
                                                    .commit();
                                        }
                                    });
                                    alertDialog.show();

                                } else {
                                    Toast.makeText(getContext(), "MEDICION CREADA", Toast.LENGTH_SHORT).show();
                                    addTabla();
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                //ONCLICK BUTTON ALERT DIALOG CANCEL
                            }
                        });

                    }else{
                        //paramCheck si es null crea tabla
                          addTabla();
                          Toast.makeText(getContext(), "MEDICION AÑADIDA", Toast.LENGTH_SHORT).show();

                      }

                } else {

                    Toast.makeText(getContext(), "Faltan Parametros", Toast.LENGTH_SHORT).show();
                }


            }

        });

        return view;
    }

    public void addTabla(){

        String verificar = editTubo1.getText().toString();
        //PARAMETROS DE CONFIG
        String cliente1= cliente;
        String planta1 = planta;
        String zona1 = zona;
        String limiteCol = String.valueOf(editCol2.getText().toString());

        int limiteTubo = Integer.parseInt(verificar);

        String id = databaseReference.push().getKey();

        //FALTA CONFIGURACION DE LA LETRA.
        CreaMedicion cm = new CreaMedicion(id,cliente1,planta1,zona1,limiteTubo, limiteCol);
        databaseReference.child(fecha).child(cliente).child(planta).child(zona).setValue(cm);

        if(!TextUtils.isEmpty(verificar) && !TextUtils.isEmpty(limiteCol)) {
            Bundle bundle = new Bundle();
            if (editTubo1 != null) {
                nTubo = Integer.parseInt(editTubo1.getText().toString());
            }
            bundle.putInt("nTUBO", nTubo);
            bundle.putString("LETRA", String.valueOf(editCol2.getText().toString()));

            MainFragment nmf = new MainFragment();
            nmf.setArguments(bundle);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, nmf)
                    .commit();

        }else{
            Toast.makeText(getContext(), "Faltan Parametros", Toast.LENGTH_SHORT).show();
        }


    }

}
