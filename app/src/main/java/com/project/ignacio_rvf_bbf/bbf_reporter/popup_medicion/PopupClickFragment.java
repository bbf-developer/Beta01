package com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.popup.popup_cell;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.Medicion;
import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.MedicionTest;


public class PopupClickFragment extends DialogFragment {

    private TextView tvPutCellName1;

    private EditText etMedicion1;
    private EditText etMedicionAnt1;
    private EditText etValorNom1;
    private EditText etEspesor1;
    private EditText etProyeccion1;

    private Button btnForm1;

    private PopupWindow myPopup;

    private int posCol;
    private int posFila;

    char [] letras = {'A', 'B', 'C', 'D','E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M','N','O','P','Q','R','S','T','U','V','W', 'X','Y','Z'};

    DatabaseReference databaseReference;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference("medicion");

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        Resources res = getActivity().getResources();
        Bundle bundle = getArguments();
        ///
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater linf = getActivity().getLayoutInflater();
        final View dialogview = linf.inflate(R.layout.fragment_popup_cell, null);

        tvPutCellName1 = dialogview.findViewById(R.id.putCellName);
        etMedicion1 = dialogview.findViewById(R.id.etMedicion);
        etMedicionAnt1 = dialogview.findViewById(R.id.etMedicionAnt);
        etValorNom1 = dialogview.findViewById(R.id.etValorNom);
        etEspesor1 = dialogview.findViewById(R.id.etEspesor);
        etProyeccion1 = dialogview.findViewById(R.id.etProyeccion);

        //btnForm1 = (Button) view.findViewById(R.id.btnNew);

        builder.setView(dialogview)
                .setPositiveButton(R.string.guardar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       addMedicion();
                    }
                }).setNegativeButton(R.string.cierra, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        wmlp.gravity = Gravity.RIGHT | Gravity.TOP;
        wmlp.x = 0;   //x position
        wmlp.y = 0;   //y position

        //dialog.show();

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

            databaseReference.child(id).setValue(med);

            Toast.makeText(getContext(), "medicion añadida",
                    Toast.LENGTH_SHORT).show();

        } else{
            Toast.makeText(getContext(), "Falta Medicion",
                    Toast.LENGTH_SHORT).show();
            return;
        }

    }
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // R.layout.my_layout - that's the layout where your textview is placed
        View view = inflater.inflate(R.layout.fragment_popup_cell, container, false);
        return view;
    }
*/

}
