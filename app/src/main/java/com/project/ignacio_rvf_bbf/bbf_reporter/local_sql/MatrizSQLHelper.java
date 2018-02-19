package com.project.ignacio_rvf_bbf.bbf_reporter.local_sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.ignacio_rvf_bbf.bbf_reporter.popup_medicion.PopupClickFragment;

/**
 * Created by Ignacio-RVF-BBF on 16/02/2018.
 * @Param
 * SQL Helper para guardar matriz local.
 * Recibe solo 2 parametros de la clase PopupClickFragment para almacenar en local.
 *
 */

public class MatrizSQLHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "matriz";
    private static final int DB_SCHEME_VERSION = 1;

    public MatrizSQLHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);

    }

    public MatrizSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBManager.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
