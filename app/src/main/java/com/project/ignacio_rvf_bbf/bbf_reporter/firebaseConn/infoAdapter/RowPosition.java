package com.project.ignacio_rvf_bbf.bbf_reporter.firebaseConn.infoAdapter;

import java.util.ArrayList;

/**
 * Created by Ignacio-RVF-BBF on 21/02/2018.
 */

public class RowPosition extends ArrayList<RowPosition> {

    private int rowposition;

    public RowPosition() {
    }

    public RowPosition(int rowposition) {

        this.rowposition = rowposition;
    }

    public int getRowposition(){return rowposition; }



}
