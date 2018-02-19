package com.project.ignacio_rvf_bbf.bbf_reporter;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.CellModel;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.ColumnHeaderModel;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.RowHeaderModel;
import com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.MyTableViewAdapter;

import com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter.listener.MyTableViewListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 * ***********************************************************
 * Clase Main que carga la tabla para ser vista por el usuario
 */
public class MainFragment extends Fragment {


    char [] abc = {'A', 'B', 'C', 'D','E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M','N','O','P','Q','R','S','T','U','V','W', 'X','Y','Z'};
    char c;

    char letra;

    public static int ROW_SIZE;
    public static int TOPE_COLUMNA;
    public static int COLUMN_SIZE ;

    private String recibeBundle;
    private List<RowHeaderModel> m_jRowHeaderList;
    private List<ColumnHeaderModel> m_jColumnHeaderList;
    private List<List<CellModel>> m_jCellList;

    private AbstractTableAdapter m_iTableViewAdapter;

    private TableView mTableView;
    private MyTableViewAdapter mTableAdapter;

    //SHOW DIALOG PROCESS
    private ProgressDialog mProgressDialog;

    private String letraTope;


    // TODO: COLOREAR TOPE 1COLUMNA y TOPE FILA
    // TODO: https://stackoverflow.com/questions/27992120/fill-arraylist-with-colors-for-android

    int topeCol;
    int topeFila;

    int[] color = new int []{Color.rgb(172, 250, 88), Color.rgb(50, 50, 234)};
    //TENGO QUE PINTAR EL NUMERO DEL LENGHT DE LA LETRA TOPE
    //EJEMPLO: 0-20, MARCA 10 Y SE PINTA CON EL PRIMER (1) INT DEL COLOR
    //MARCA 15 Y SE PINTA CON EL SEGUNDO (2) INT

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
      //  BundleItems args = new BundleItems();
      //  args.putString(ARG_PARAM1, param1);
      //  args.putString(ARG_PARAM2, param2);
      //  fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ROW_SIZE = this.getArguments().getInt("nTUBO");
        letraTope = this.getArguments().getString("LETRA");

        letra = letraTope.charAt(0);

        for (int i=0; i < abc.length; i++){
            if (abc[i] == letra) {
                COLUMN_SIZE = i + 1;
            }
        }

        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RelativeLayout fragment_container = (RelativeLayout) view
                .findViewById(R.id.fragment_container);

        // Create Table view
        mTableView = createTableView();
        fragment_container.addView(mTableView);
        loadData();

        return view;
    }

        private TableView createTableView() {
        TableView tableView = new TableView(getContext());

        // Set adapter
        m_iTableViewAdapter = new MyTableViewAdapter(getContext());
        tableView.setAdapter(m_iTableViewAdapter);

        // Set layout params
        FrameLayout.LayoutParams tlp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams
                .MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        tableView.setLayoutParams(tlp);

        // Set TableView listener
        tableView.setTableViewListener(new MyTableViewListener(tableView));
        return tableView;
    }

    private void initData() {
        m_jRowHeaderList = new ArrayList<>();
        m_jColumnHeaderList = new ArrayList<>();
        m_jCellList = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            m_jCellList.add(new ArrayList<CellModel>());
        }

    }

    private void loadData() {
        List<RowHeaderModel> rowHeaders = getRowHeaderList();
        List<List<CellModel>> cellList = getCellListForSorting(); // getCellList();
        List<ColumnHeaderModel> columnHeaders = getColumnHeaderList(); //getRandomColumnHeaderList(); //

        m_jRowHeaderList.addAll(rowHeaders);
        for (int i = 0; i < cellList.size(); i++) {
            m_jCellList.get(i).addAll(cellList.get(i));
        }
        // Load all data
        m_jColumnHeaderList.addAll(columnHeaders);
        m_iTableViewAdapter.setAllItems(m_jColumnHeaderList, m_jRowHeaderList, m_jCellList);

    }

    //GENERA LAS FILAS DE LA TABLA
    private List<RowHeaderModel> getRowHeaderList() {

        List<RowHeaderModel> list = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            RowHeaderModel header = new RowHeaderModel(String.valueOf(i), "" + i);
            list.add(header);
        }

        return list;
    }

    //GENERA LAS COLUMNAS DE LA TABLA
    private List<ColumnHeaderModel> getColumnHeaderList() {
        List<ColumnHeaderModel> list = new ArrayList<>();
         for (int i = 0; i < (COLUMN_SIZE); i++) {
               String colTitle = "AA";
             if (i % 1 == 0 ) {
                 c = abc[i];
                 colTitle = String.valueOf(c);
             }
             ColumnHeaderModel header = new ColumnHeaderModel(String.valueOf(i), colTitle);
             list.add(header);
        }
        return list;
    }

    //GENERA LAS CELDAS DE LA TABLA
    private List<List<CellModel>> getCellListForSorting() {
        List<List<CellModel>> list = new ArrayList<>();
        //BUCLE DEFINE FILAS
        for (int i = 0; i < ROW_SIZE; i++) {
            List<CellModel> cellList = new ArrayList<>();
            //BUCLE DEFINE COLUMNAS
            for (int j = 0; j < COLUMN_SIZE; j++) {
                //TODO: Aqui Ingresa valores estaticos a las celdas
                //Object strText = "0" +","+ "0";
                Object strText =" s/p ";
                String strID = j + "-" + i;

                //SON 2 ENTRADAS DE PARAMETROS UNA DEFINE EL IDENTIFICADOR DE LA CELDA
                //Y EL OBJETO QUE SE QUIERE ALMACENAR EN ESA ID
                CellModel cell = new CellModel(strID, strText);
                cellList.add(cell);
            }
            list.add(cellList);
        }

        return list;
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage("Obteniendo datos, espere porfavor...");
            mProgressDialog.setCancelable(false);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {

        if ((mProgressDialog != null) && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        mProgressDialog = null;
    }

    /**@OLDCODE
     *
     */
        /*
         private List<RowHeaderModel> createRowHeaderList() {
        List<RowHeaderModel> list = new ArrayList<>();
        for (int i = 0; i < mCellList.size(); i++) {
        // In this example, Row headers just shows the index of the TableView List.
        list.add(new RowHeaderModel(String.valueOf(i + 1)));
        }
            return list;
            }
        */
     /*

    public void populatedTableView(List<UserInfo> userInfoList) {
        // create Models
        mColumnHeaderList = createColumnHeaderModelList();
        mCellList = loadCellModelList(userInfoList);
        mRowHeaderList = createRowHeaderList();

        // Set all items to the TableView
        mTableAdapter.setAllItems(mColumnHeaderList, mRowHeaderList, mCellList);
    }
    */

    /*
    private List<ColumnHeaderModel> createColumnHeaderModelList() {
        List<ColumnHeaderModel> list = new ArrayList<>();

        // Create Column Headers
        list.add(new ColumnHeaderModel("Id"));
        list.add(new ColumnHeaderModel("Name"));
        list.add(new ColumnHeaderModel("Nickname"));
        list.add(new ColumnHeaderModel("Email"));
        list.add(new ColumnHeaderModel("Birthday"));
        list.add(new ColumnHeaderModel("Sex"));
        list.add(new ColumnHeaderModel("Age"));
        list.add(new ColumnHeaderModel("Job"));
        list.add(new ColumnHeaderModel("Salary"));
        list.add(new ColumnHeaderModel("CreatedAt"));
        list.add(new ColumnHeaderModel("UpdatedAt"));
        list.add(new ColumnHeaderModel("Address"));
        list.add(new ColumnHeaderModel("Zip Code"));
        list.add(new ColumnHeaderModel("Phone"));
        list.add(new ColumnHeaderModel("Fax"));

        return list;
    }

    /*
    private List<List<CellModel>> loadCellModelList(List<UserInfo> userInfoList) {
        List<List<CellModel>> lists = new ArrayList<>();

        // Creating cell model list from UserInfo list for Cell Items
        // In this example, UserInfo list is populated from web service

        for (int i = 0; i < userInfoList.size(); i++) {
            UserInfo userInfo = userInfoList.get(i);

            List<CellModel> list = new ArrayList<>();

            // The order should be same with column header list;
            list.add(new CellModel("1-" + i, userInfo.getId()));       // "Id"
            list.add(new CellModel("2-" + i, userInfo.getName()));     // "Name"
            list.add(new CellModel("3-" + i, userInfo.getNickName())); // "Nickname"
            list.add(new CellModel("4-" + i, userInfo.getEmail()));    // "Email"
            list.add(new CellModel("5-" + i, userInfo.getBirthDay())); // "BirthDay"
            list.add(new CellModel("6-" + i, userInfo.getGender()));   // "Gender"
            list.add(new CellModel("7-" + i, userInfo.getAge()));      // "Age"
            list.add(new CellModel("8-" + i, userInfo.getJob()));      // "Job"
            list.add(new CellModel("9-" + i, userInfo.getSalary()));   // "Salary"
            list.add(new CellModel("10-" + i, userInfo.getCreatedAt()));// "CreatedAt"
            list.add(new CellModel("11-" + i, userInfo.getUpdatedAt()));// "UpdatedAt"
            list.add(new CellModel("12-" + i, userInfo.getAddress()));  // "Address"
            list.add(new CellModel("13-" + i, userInfo.getZipCode()));  // "Zip Code"
            list.add(new CellModel("14-" + i, userInfo.getMobile()));   // "Phone"
            list.add(new CellModel("15-" + i, userInfo.getFax()));      // "Fax"

            // Add
            lists.add(list);
        }

        return lists;
    }
    */
}
