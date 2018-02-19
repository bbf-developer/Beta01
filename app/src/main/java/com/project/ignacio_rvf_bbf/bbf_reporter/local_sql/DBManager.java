package com.project.ignacio_rvf_bbf.bbf_reporter.local_sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter.MedicionTest;

/**
 * Created by Ignacio-RVF-BBF on 16/02/2018.
 */

public class DBManager {
    public static final String TABLE_NAME = "2018-01";

    public static final String CELL_ID = "_id";
    public static final String CELL_DATA = "CELL_DATA";

    public static final String CREATE_TABLE = "create table" +TABLE_NAME+"("
            + CELL_ID + "integer primary key autoincrement,"
            + CELL_DATA + "text not null);";

    private MatrizSQLHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context){
        helper = new MatrizSQLHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generateContentValues (String cell_id, String cell_data){
        ContentValues valor = new ContentValues();
        valor.put(CELL_ID, cell_id);
        valor.put(CELL_DATA, cell_data);

        return valor;
    }


    public void insert(String cell_id, String cell_data ){

    db.insert(TABLE_NAME, null, generateContentValues(cell_id, cell_data));

    }

    public void insertData(String cell_id, String cell_data){
        db.execSQL("insert into" + TABLE_NAME + " values (null, '"+cell_id+"',"+cell_data+")");
    }

    public Cursor loadCells(){
        String[] col = new String[]{CELL_ID,CELL_DATA};

        return db
           .query(TABLE_NAME, col, null,null,null,null,null);
    }

    public Cursor loadTable(){
        String[] table = new String[]{TABLE_NAME};

        return db
                .query(TABLE_NAME, table, null,null,null,null,null);
    }
}
