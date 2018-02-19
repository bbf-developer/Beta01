package com.project.ignacio_rvf_bbf.bbf_reporter;

import java.util.ArrayList;

/**
 * Created by Ignacio-RVF-BBF on 16/01/2018.
 */

public class SubChildInfo extends SubGroupInfo {

    private String name;

    public ArrayList<SubGroupInfo> getList(){
        return list;
    }

    public void setList(ArrayList<SubGroupInfo> list){this.list = list;
    }

    private ArrayList<SubGroupInfo> list= new ArrayList<SubGroupInfo>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
