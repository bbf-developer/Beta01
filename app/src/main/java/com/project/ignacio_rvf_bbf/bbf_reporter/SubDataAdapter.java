package com.project.ignacio_rvf_bbf.bbf_reporter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ignacio-RVF-BBF on 16/01/2018.
 */

public class SubDataAdapter extends BaseExpandableListAdapter{

    private Context context;
    private ArrayList<SubChildInfo> deptlist;

    public SubDataAdapter(Context ctx, ArrayList<SubChildInfo> deptlist){
        this.context = ctx;
        this.deptlist = deptlist;
    }

    @Override
    public int getGroupCount() {
        return deptlist.size(); }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<SubGroupInfo> productlist = deptlist.get(groupPosition).getList();
        return productlist.size(); }

    @Override
    public Object getGroup(int groupPosition) {return deptlist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<SubGroupInfo> productlist= deptlist.get(groupPosition).getList();
        return productlist.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {return groupPosition;}

    @Override
    public long getChildId(int groupPosition, int childPosition) {return 0;}

    @Override
    public boolean hasStableIds() {return true;}

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        SubChildInfo headerinfo = (SubChildInfo)getGroup(groupPosition);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.subbranditems, null);

        }

        TextView heading = view.findViewById(R.id.subheading);
        heading.setText(headerinfo.getName().trim());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        SubGroupInfo detailinfo = (SubGroupInfo)getChild(groupPosition, childPosition);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.subcaritems, null);
        }
        TextView sequence = view.findViewById(R.id.subbranditems);
        sequence.setText(detailinfo.getSequence().trim()+".");

        TextView childitem = view.findViewById(R.id.subbranditems);
        childitem.setText(detailinfo.getName().trim());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}