package com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.expandable;

import com.project.ignacio_rvf_bbf.bbf_reporter.GroupInfo;

import java.util.ArrayList;

/**
 * Created by Ignacio-RVF-BBF on 20/02/2018.
 */

public class ChildInf extends GroupInf{

    private String name;

    public ArrayList<GroupInf> getList(){return list;
    }

    public void setList(ArrayList<GroupInf> list){this.list = list;
    }

    private ArrayList<GroupInf> list= new ArrayList<GroupInf>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
