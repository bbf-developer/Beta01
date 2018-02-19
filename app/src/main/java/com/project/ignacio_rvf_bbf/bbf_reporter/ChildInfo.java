package com.project.ignacio_rvf_bbf.bbf_reporter;

import java.util.ArrayList;

/**
 * Created by Ignacio-RVF-BBF on 15/01/2018.
 */

public class ChildInfo extends GroupInfo {

    private String name;

    public ArrayList<GroupInfo> getList(){return list;
    }

    public void setList(ArrayList<GroupInfo> list){this.list = list;
    }

    private ArrayList<GroupInfo> list= new ArrayList<GroupInfo>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
