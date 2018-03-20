package com.project.ignacio_rvf_bbf.bbf_reporter.popup_medicion;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.MedicionTest;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.RowPosition;
import com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.ShowCliente;
import com.project.ignacio_rvf_bbf.bbf_reporter.local_sql.MatrizSQLHelper;
import com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.prefs.Preferences;


import static com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer.ContinueFragment.KEY_VAL1;
import static com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer.ContinueFragment.KEY_VAL2;
import static com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer.ContinueFragment.KEY_VAL3;
import static com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer.ContinueFragment.KEY_VAL4;
import static com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer.ContinueFragment.KEY_VAL5;
import static com.project.ignacio_rvf_bbf.bbf_reporter.checkpointer.ContinueFragment.SHARED_PREF_CONTINUE;
import static com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener.KEY_NUM1;
import static com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener.KEY_NUM2;
import static com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener.SHARED_PREF_NUM1;

/**
 * Created by Ignacio-RVF-BBF on 23/01/2018.
 * @param
 */

public class PopupContinue extends DialogFragment {

    private static final String TAG = "MyActivity";

    public final static String SHARED_PREF_CELL="paramCell";
    public final static String KEY_CELL1= "KEY_CELL";

    //List<RowPosition> result = new ArrayList<>();
    ArrayList<RowPosition> myResult = new ArrayList<>();
    ArrayList<Integer> myList = new ArrayList<>();
    ArrayList<String> myRow = new ArrayList<>();

    private Toast mssgeToast;
    private Context mContext;
    private TextView tvPutCellName1;
    private TextView tvPutNumCell;

    private EditText etMedicion1;
    private EditText etMedicionAnt1;
    private EditText etValorNom1;
    private EditText etEspesor1;
    private EditText etProyeccion1;
    private EditText etPrecaucion;

    private Button btnForm1;

    private PopupWindow myPopup;

    private AlertDialog changeAlert;

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
    private String nomTipo;
    private String nomLinea;
    private String colTitle;

    //DATE PICKER
    int month, year;
    private String fecha;

    //DATE CELL
    public String putCell;
    private String checkNull;
    public String checking;

    //INDEX ARRAY
    int count = 0;

    int position = -1;

    DatabaseReference databaseReference;

    public PopupContinue() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //GETTING STRING TO SHOW CLIENTE.
        SharedPreferences sharedCont = getContext().getSharedPreferences(SHARED_PREF_CONTINUE,0);
        nomCliente = sharedCont.getString(KEY_VAL1,"").toLowerCase();

        nomPlanta = sharedCont.getString(KEY_VAL2,"").toLowerCase();

        nomZona = sharedCont.getString(KEY_VAL3,"").toLowerCase();

        nomTipo = sharedCont.getString(KEY_VAL4, "").toLowerCase();

        nomLinea = sharedCont.getString(KEY_VAL5,"");


        databaseReference = FirebaseDatabase.getInstance().getReference("medicion").child(nomCliente)
                .child(nomPlanta).child(nomZona).child(nomTipo + nomLinea);

        //Asignacion de escritura de fecha a la tabla.
        Calendar cal = Calendar.getInstance();
        int monthofYear  = cal.get(Calendar.MONTH);
        int month = monthofYear - 1;
        int year = cal.get(Calendar.YEAR);
        fecha = String.valueOf(year + "-" + month);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater linf = getActivity().getLayoutInflater();
        final View dialogview = linf.inflate(R.layout.fragment_popup_cell, null);

        //IMPRESIÓN DE LETRA EN EL LAYER
        tvPutCellName1 = dialogview.findViewById(R.id.putCellName);
        //RECEPCION DE DATOS DEL LISTENER DE LA TABLA //NO MOVER
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_NUM1, 0);
        colPos = sharedPreferences.getString(KEY_NUM1,"").toLowerCase();
        filaPos = sharedPreferences.getString(KEY_NUM2,"").toLowerCase();
        COL_SIZE = Integer.parseInt(colPos);

        //MAPEA LA LETRA PARA IMPRIMIR //NO MOVER
        c = abc[COL_SIZE];

        tvPutCellName1.setText(String.valueOf(c));

        tvPutNumCell = dialogview.findViewById(R.id.putCellNumber);
        //RECEPCION DE NUMERO EN STRING
        tvPutNumCell.setText(filaPos);

        etMedicion1 = dialogview.findViewById(R.id.etMedicion);
        etMedicionAnt1 = dialogview.findViewById(R.id.etMedicionAnt);
        etValorNom1 = dialogview.findViewById(R.id.etValorNom);
        etEspesor1 = dialogview.findViewById(R.id.etEspesor);
        etProyeccion1 = dialogview.findViewById(R.id.etProyeccion);
        etPrecaucion = dialogview.findViewById(R.id.etPrecaucion);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(dialogview)
                .setPositiveButton(R.string.guardar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference.child(fecha).child(String.valueOf(c)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChildren()) {

                                    //NO ES NULL, PROCEDE A LEER TODOS LOS CHILD DEL NODO.
                                    for (DataSnapshot snap : dataSnapshot.getChildren()) {
                                        HashMap<Integer, Object> myHash = (HashMap<Integer, Object>) snap.getValue();
                                        String check = String.valueOf(myHash.get("rowposition"));
                                        Log.e("TAG", check);
                                        myList.add(Integer.parseInt(String.valueOf(myHash.get("rowposition"))));
                                    }

                                    int rowpos = Integer.parseInt(filaPos);
                                    position = myList.indexOf(rowpos);

                                }else{
                                    //IGUAL A NULL, AGREGA EL PRIMER PARAMETRO

                                    String medicion = etMedicion1.getText().toString().trim();
                                    String medicionAnt = etMedicionAnt1.getText().toString().trim();
                                    String valorNom = etValorNom1.getText().toString().trim();
                                    String espesor = etEspesor1.getText().toString().trim();
                                    String proyeccion = etProyeccion1.getText().toString().trim();
                                    String precaucion = etPrecaucion.getText().toString().trim();


                                    //UPLOAD DATA TO FIREBASE
                                    if(!TextUtils.isEmpty(medicion)&&!TextUtils.isEmpty(medicionAnt)&&!TextUtils.isEmpty(valorNom)&&!TextUtils.isEmpty(espesor)
                                            &&!TextUtils.isEmpty(proyeccion)){

                                        double medicionDouble =Double.parseDouble(medicionAnt);
                                        double medicionantDouble =Double.parseDouble(medicion);
                                        double valorNomDouble =Double.parseDouble(valorNom);
                                        double espesorDouble =Double.parseDouble(espesor);
                                        double precaucionDouble = Double.parseDouble(precaucion);

                                        int proyeccionInt =Integer.parseInt(proyeccion);

                                        int rowposition = Integer.parseInt(filaPos);
                                        int colposition = Integer.parseInt(colPos);

                                        String id = databaseReference.push().getKey();
                                        MedicionTest med = new MedicionTest(id,  medicionDouble, medicionantDouble,valorNomDouble
                                                ,espesorDouble,precaucionDouble,proyeccionInt, rowposition, colposition);

                                        databaseReference.child(fecha).child(String.valueOf(c)).child(id).setValue(med);

                                            /* Toast.makeText(getContext(), "Parametro Ingresado",
                                           Toast.LENGTH_SHORT).show();*/
                                    } else{
                                          /* Toast.makeText(getContext(), "Faltan Parametros",
                                            Toast.LENGTH_SHORT).show();
                                                    */
                                    }

                                }

                                //myList.indexOf(rowpos)
                                if (position == -1) {
                                    Log.e("TAG", "NO EXISTE");
                                    //PROBLEMA: AL RECORRER MUESTRA EL ALERT DIALOG
                                    addMedicion();

                                } else {
                                    //LANZA ALERT DIALOG
                                    Log.e("TAG", "ALERTA");
                                    new AlertDialog.Builder(dialogview.getContext())
                                            .setTitle("Registro existente en " + " " + String.valueOf(c) + "-" + filaPos )
                                            .setMessage("Mantenga pulsada la celda para modificar.")
                                            .setIcon(R.drawable.ic_error_alert)
                                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dismiss();

                                                }

                                            }).show();

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

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

                wmlp.gravity = Gravity.RELATIVE_LAYOUT_DIRECTION | Gravity.LEFT | Gravity.TOP ;

                wmlp.x =  x;   //x position
                wmlp.y =  y;   //y position

                dialog.getWindow().setAttributes(wmlp);

                return true;
            }
        });

        return dialog;
    }


    //OTRO METODO DE MOSTRAT TOAST EN EL LAYER
    private void showToast(String setMessage){
        if (mssgeToast == null){
            mssgeToast = Toast.makeText(getActivity(), "",Toast.LENGTH_SHORT);
        }

        mssgeToast.setText(setMessage);
        mssgeToast.show();
    }


    private void addMedicion(){
        //Checkers de la tabla para evitar enviar informacion de tipo @NULL

        String medicion = etMedicion1.getText().toString().trim();
        String medicionAnt = etMedicionAnt1.getText().toString().trim();
        String valorNom = etValorNom1.getText().toString().trim();
        String espesor = etEspesor1.getText().toString().trim();
        String proyeccion = etProyeccion1.getText().toString().trim();
        String precaucion = etPrecaucion.getText().toString().trim();

        //UPLOAD DATA TO FIREBASE
        if(!TextUtils.isEmpty(medicion)&&!TextUtils.isEmpty(medicionAnt)&&!TextUtils.isEmpty(valorNom)&&!TextUtils.isEmpty(espesor)
                &&!TextUtils.isEmpty(proyeccion)){

            double medicionDouble =Double.parseDouble(medicionAnt);
            double medicionantDouble =Double.parseDouble(medicion);
            double valorNomDouble =Double.parseDouble(valorNom);
            double espesorDouble =Double.parseDouble(espesor);
            double precaucionDouble = Double.parseDouble(precaucion);

            int proyeccionInt =Integer.parseInt(proyeccion);

            int rowposition = Integer.parseInt(filaPos);
            int colposition = Integer.parseInt(colPos);

            String id = databaseReference.push().getKey();
            MedicionTest med = new MedicionTest(id,  medicionDouble, medicionantDouble,valorNomDouble
                    ,espesorDouble, precaucionDouble, proyeccionInt, rowposition, colposition);

            databaseReference.child(fecha).child(String.valueOf(c)).child(id).setValue(med);

           /* Toast.makeText(getContext(), "Parametro Ingresado",
                    Toast.LENGTH_SHORT).show();*/
        } else{
           /* Toast.makeText(getContext(), "Faltan Parametros",
                    Toast.LENGTH_SHORT).show();
                                                    */
        }
    }

}