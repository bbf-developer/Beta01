package com.project.ignacio_rvf_bbf.bbf_reporter;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ignacio-RVF-BBF on 19/02/2018.
 */

public class EnabledTrue extends android.app.Application {

    public void onCreate() {
        super.onCreate();
        //Inicializar la persistencia de datos en disco.

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
