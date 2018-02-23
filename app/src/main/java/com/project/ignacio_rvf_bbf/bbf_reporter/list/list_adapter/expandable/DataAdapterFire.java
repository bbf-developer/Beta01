package com.project.ignacio_rvf_bbf.bbf_reporter.list.list_adapter.expandable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.project.ignacio_rvf_bbf.bbf_reporter.ChildInfo;
import com.project.ignacio_rvf_bbf.bbf_reporter.GroupInfo;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ignacio-RVF-BBF on 20/02/2018.
 * @Param
 * Adaptador de expandable list view en ShowPlantaFragment.class
 */

public class DataAdapterFire extends BaseExpandableListAdapter {
    private Context xContext;
    private ArrayList<ChildInf> deptlist;


    public DataAdapterFire(Context context, ArrayList<ChildInf> deptlist){
        this.xContext = context;
        this.deptlist = deptlist;
    }


    //EXPANDABLE SHOW DATA
    //groupPosition = int i
    //childPosition = int i1
    @Override
    public int getGroupCount() {
      return deptlist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        ArrayList<GroupInf> productlist = deptlist.get(i).getList();
        return productlist.size();
    }

    @Override
    public Object getGroup(int i) {
        return deptlist.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        ArrayList<GroupInf> productlist= deptlist.get(i).getList();
        return productlist.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //Alzamiento de vista del expandable
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        //String headerTitle = (String) getGroup(i);
        ChildInf headerinfo = (ChildInf)getGroup(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) xContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_items, null);
        }
        TextView textListHeader = view.findViewById(R.id.heading);
        textListHeader.setText(headerinfo.getName().trim());

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        //final String childText = (String) getChild(i, i1);
        GroupInf detailinfo = (GroupInf)getChild(i, i1);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)xContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_items, null);
        }

        TextView textListChild = view.findViewById(R.id.list_items);
        textListChild.setText(detailinfo.getName().trim());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}


