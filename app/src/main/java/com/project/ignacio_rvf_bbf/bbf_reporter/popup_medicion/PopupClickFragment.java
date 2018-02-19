package com.project.ignacio_rvf_bbf.bbf_reporter.popup_medicion;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.evrencoskun.tableview.TableView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.MedicionTest;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.ShowCliente;
import com.project.ignacio_rvf_bbf.bbf_reporter.local_sql.MatrizSQLHelper;
import com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.prefs.Preferences;

import static com.project.ignacio_rvf_bbf.bbf_reporter.SubHogarFragment.KEY_TEXT_ZONA;
import static com.project.ignacio_rvf_bbf.bbf_reporter.SubHogarFragment.SHARED_PREF_ZONA;
import static com.project.ignacio_rvf_bbf.bbf_reporter.list.ShowClienteFragment.KEY_TEXT;
import static com.project.ignacio_rvf_bbf.bbf_reporter.list.ShowClienteFragment.SHARED_PREFS_FILE;
import static com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.SubShowClienteFragment.KEY_TEXT1;
import static com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.SubShowClienteFragment.SHARED_PREF_TEXT;
import static com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener.KEY_NUM1;
import static com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener.KEY_NUM2;
import static com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener.SHARED_PREF_NUM1;

/**
 * Created by Ignacio-RVF-BBF on 23/01/2018.
 * @param
 */

public class PopupClickFragment extends DialogFragment {

    public final static String SHARED_PREF_CELL="paramCell";
    public final static String KEY_CELL1= "KEY_CELL";

    private TextView tvPutCellName1;
    private TextView tvPutNumCell;

    private EditText etMedicion1;
    private EditText etMedicionAnt1;
    private EditText etValorNom1;
    private EditText etEspesor1;
    private EditText etProyeccion1;

    private Button btnForm1;

    private PopupWindow myPopup;

    char [] abc = {'A', 'B', 'C', 'D','E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M','N','O','P','Q','R','S','T','U','V','W', 'X','Y','Z'};
    char c;

    //VARIABLES DE RECEPCION Shared Preferences.
    private String colPos;
    private String filaPos;



    private int COL_SIZE;
    private int ROW_SIZE;

    //VARIABLES DE ESCRITURA EN BASE DE DATOS
    private String nomCliente;
    private String nomPlanta;
    private String nomZona;

    private String colTitle;

    //DATE PICKER
    int month, year;
    private String fecha;

    //DATE CELL
    public String putCell;

    DatabaseReference databaseReference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //GETTING STRING TO SHOW CLIENTE.
        SharedPreferences sharedCliente = getContext().getSharedPreferences(SHARED_PREFS_FILE,0);
        nomCliente = sharedCliente.getString(KEY_TEXT,"");
        //GETTING STRING TO SUBSHOW PLANTA.
        SharedPreferences sharedPlanta = getContext().getSharedPreferences(SHARED_PREF_TEXT, 0);
        nomPlanta = sharedPlanta.getString(KEY_TEXT1,"");
        //GETTING STRING TO ZONA
        SharedPreferences sharedZona = getContext().getSharedPreferences(SHARED_PREF_ZONA,0);
        nomZona = sharedZona.getString(KEY_TEXT_ZONA,"");

        databaseReference = FirebaseDatabase.getInstance().getReference("medicion").child(nomCliente)
                .child(nomPlanta).child(nomZona);
        //Asignacion de escritura de fecha a la tabla.
        Calendar cal = Calendar.getInstance();
        int monthofYear = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        fecha = String.valueOf(year + "-" + monthofYear);

        //SQLite Communicator
        /**
         * Se envian los parametros de respaldo a la base de datos local.
         * Deben estar en el boton del layer
         * **/
        //MatrizSQLHelper helper = new MatrizSQLHelper(getContext());
        /**
         * Si la BD no existe getWritable la crea, si existe solo escribe
         * */
        //SQLiteDatabase db = helper.getWritableDatabase();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        Resources res = getActivity().getResources();
        Bundle bundle = getArguments();
        ///
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater linf = getActivity().getLayoutInflater();
        View dialogview = linf.inflate(R.layout.fragment_popup_cell, null);


        //IMPRESIÓN DE LETRA EN EL LAYER
        tvPutCellName1 = dialogview.findViewById(R.id.putCellName);
       //RECEPCION DE DATOS DEL LISTENER DE LA TABLA
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NUM1, 0);
        colPos = sharedPreferences.getString(KEY_NUM1,"");
        filaPos = sharedPreferences.getString(KEY_NUM2,"");
        COL_SIZE = Integer.parseInt(colPos);

        //MAPEA LA LETRA PARA IMPRIMIR
        c = abc[COL_SIZE];
        tvPutCellName1.setText(String.valueOf(c));

        tvPutNumCell = dialogview.findViewById(R.id.putCellNumber);
        //RECEPCION DE NUMERO EN STRING
        tvPutNumCell.setText(filaPos);

        //tvPutNumCell.setText(String.valueOf(year + "-" + month));
        etMedicion1 = dialogview.findViewById(R.id.etMedicion);
        etMedicionAnt1 = dialogview.findViewById(R.id.etMedicionAnt);
        etValorNom1 = dialogview.findViewById(R.id.etValorNom);
        etEspesor1 = dialogview.findViewById(R.id.etEspesor);
        etProyeccion1 = dialogview.findViewById(R.id.etProyeccion);


        etMedicion1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //INSCRIPCION DE PARAMETRO EN LA CELDA CORRESPONDIENTE.

                if(etMedicion1.getText().length() >= 0){


                }
            }
        });

        builder.setView(dialogview)
                .setPositiveButton(R.string.guardar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO: GENERAR RECORDATORIO EN CASO QUE SE TRATE DE SOBREESCRIBIR LA MEDICION ANTERIOR
                        //databaseReference.child(fecha).child(String.valueOf(c)).child(String.valueOf(c)+filaPos).setValue(med);
                        addMedicion();

                    }
                }).setNegativeButton(R.string.cierra, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //OBTENER LA POSICION COMPLETA CON getRawX();
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();

                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.RELATIVE_LAYOUT_DIRECTION  | Gravity.LEFT  | Gravity.TOP  ;
                wmlp.x =  x;   //x position
                wmlp.y =  y;   //y position

                dialog.getWindow().setAttributes(wmlp);

                return true;
            }
        });

        return dialog;
    }

    private void addMedicion(){
        //Checkers de la tabla para evitar enviar informacion de tipo @NULL

        String medicion = etMedicion1.getText().toString().trim();
        String medicionAnt = etMedicionAnt1.getText().toString().trim();
        String valorNom = etValorNom1.getText().toString().trim();
        String espesor = etEspesor1.getText().toString().trim();
        String proyeccion = etProyeccion1.getText().toString().trim();

        double medicionDouble =Double.parseDouble(medicionAnt);
        double medicionantDouble =Double.parseDouble(medicion);
        double valorNomDouble =Double.parseDouble(valorNom);
        double espesorDouble =Double.parseDouble(espesor);

        int    proyeccionInt =Integer.parseInt(proyeccion);


        //TESTING UPLOAD DATA TO FIREBASE
        if(!TextUtils.isEmpty(medicion)){

            String id = databaseReference.push().getKey();
            MedicionTest med = new MedicionTest(id,  medicionDouble, medicionantDouble,valorNomDouble
                    ,espesorDouble,proyeccionInt);
            //REFERENCIAR LA TABLA GENERAL CON LA FECHA EN AÑO-DIA
            databaseReference.child(fecha).child(String.valueOf(c)).child(String.valueOf(c)+filaPos).setValue(med);

            Toast.makeText(getContext(), "Parametros Ingresados",
                    Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getContext(), "Faltan Mediciones",
                    Toast.LENGTH_SHORT).show();
            return;
        }

    }

}



