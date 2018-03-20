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
import android.text.InputFilter;
import android.text.Spanned;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.CreaMedicion;
import java.util.Calendar;

import static com.project.ignacio_rvf_bbf.bbf_reporter.RepcalderaFrag.KEY_CALDERA;
import static com.project.ignacio_rvf_bbf.bbf_reporter.RepcalderaFrag.KEY_CLIENTE;
import static com.project.ignacio_rvf_bbf.bbf_reporter.RepcalderaFrag.KEY_LINEA;
import static com.project.ignacio_rvf_bbf.bbf_reporter.RepcalderaFrag.KEY_PLANTA;
import static com.project.ignacio_rvf_bbf.bbf_reporter.RepcalderaFrag.SHARED_PREFS_CALDERA;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NuevaMedicionFragment#newInstance} factory method to
 * create an instance of this fragment.
 * TABLA DE CONFIGURACION PARA MENÚ CALDERA
 */
public class NuevaMedicionFragment extends Fragment {

    public static final String SHARED_PREFS_POPUP_MAIN = "popupMain";
    public static final String KEY_CALDERA1 ="KEY_CALDERA1";
    public static final String KEY_CLIENTE1 ="KEY_CLIENTE1";
    public static final String KEY_PLANTA1 ="KEY_PLANTA1";
    public static final String KEY_ZONA1 ="KEY_ZONA1";
    public static final String KEY_LINEA1 ="KEY_LINEA1";

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
    private TextView muestraTipo;
    private TextView muestraLinea;

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
    private String caldera = "CALDERA";
    private String zona;
    private String tipo;
    private String linea;

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

    //calendar parameters

    private String day1;
    private String month1;
    private String year1;
    private String linea1;

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
        zona = this.getArguments().getString("KEY_ZONA");

        //GUARDAR PARAMETROS PARA SALVAR LA TABLA QUE SE CREO
        databaseReference = FirebaseDatabase.getInstance().getReference("save");

        //Asignacion de escritura en BBDD de fecha a la tabla.
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int monthofYear = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        fecha = String.valueOf(year + "-" + monthofYear);

        day1= String.valueOf(day);
        month1= String.valueOf(monthofYear);
        year1= String.valueOf(year);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_nueva_medicion, container, false);
        getActivity().setTitle("Nueva Medición");

        button = (Button) view.findViewById(R.id.btnNew);

        editCol1 = (EditText) view.findViewById(R.id.etxtCol1);
        editFila = (EditText) view.findViewById(R.id.etxtFila);
        editCol2 = (EditText) view.findViewById(R.id.etxtCol2);

        editCol1.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charset, int i, int i1, Spanned spanned, int i2, int i3) {

                          if(charset.equals("")){
                              return charset;
                          }
                          if(charset.toString().matches("[b-zB-Z]+")){
                              return charset;
                          }

                        return "";
                    }
                }
        });

        editFila.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charset, int i, int i1, Spanned spanned, int i2, int i3) {

                        if(charset.equals("")){
                            return charset;
                        }
                        if(charset.toString().matches("[b-zB-Z]+")){
                            return charset;
                        }

                        return "";
                    }
                }
        });

        editCol2.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charset, int i, int i1, Spanned spanned, int i2, int i3) {

                        if(charset.equals("")){
                            return charset;
                        }
                        if(charset.toString().matches("[b-zB-Z]+")){
                            return charset;
                        }

                        return "";
                    }
                }
        });

        //******************** RECEPCION DE PARAMETROS PARA EL POPUP **************
        SharedPreferences sharedPreferences3 = getContext().getSharedPreferences(SHARED_PREFS_CALDERA, 0);
        tipo = sharedPreferences3.getString(KEY_CALDERA, "");
        muestraTipo = view.findViewById(R.id.textvTipo);
        muestraTipo.setText(tipo);
        cliente = sharedPreferences3.getString(KEY_CLIENTE,"");
        muestraCliente = view.findViewById(R.id.textvCliente);
        muestraCliente.setText(cliente);
        planta = sharedPreferences3.getString(KEY_PLANTA,"");
        muestraPlanta = view.findViewById(R.id.txtZona);
        muestraPlanta.setText(planta);
        //*********************
        muestraZona = view.findViewById(R.id.textvZona);
        muestraZona.setText(zona);
        linea = sharedPreferences3.getString(KEY_LINEA,"");
        muestraLinea = view.findViewById(R.id.textvLinea);
        muestraLinea.setText(linea);

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
               addTabla();
            }
        });

        return view;
    }

    public void addTabla(){
        String verificar = editTubo1.getText().toString();
        String limiteCol = String.valueOf(editCol2.getText().toString());
        //String verfica2 = String.valueOf(editFila.getText().toString());

        if(!TextUtils.isEmpty(verificar) && !TextUtils.isEmpty(limiteCol)) {
            //PARAMETROS DE CONFIG
            String menu1 = caldera;
            String cliente1= cliente;
            String planta1 = planta;
            String zona1 = zona;
            String linea1 = linea;
            int limiteTubo = Integer.parseInt(verificar);
            String getYear = year1;
            String getMonth = month1;
            String getDay = day1;
            String id = databaseReference.push().getKey();
            //SAVING CONFIGURATION PARAMETERS OF TABLE
            CreaMedicion cm = new CreaMedicion(id,cliente1,planta1,zona1,limiteTubo, limiteCol, getYear, getMonth, getDay, linea1, menu1);
            databaseReference.child(id).setValue(cm);

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

            SharedPreferences sharedPrefs = getContext().getSharedPreferences(SHARED_PREFS_POPUP_MAIN, 0);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(KEY_CALDERA1, tipo);
            editor.putString(KEY_CLIENTE1, cliente1);
            editor.putString(KEY_PLANTA1, planta1);
            editor.putString(KEY_ZONA1, zona1);
            editor.putString(KEY_LINEA1, linea1);
            editor.commit();

        }else{
            Toast.makeText(getContext(), "Faltan Parametros", Toast.LENGTH_SHORT).show();
        }

    }

}
