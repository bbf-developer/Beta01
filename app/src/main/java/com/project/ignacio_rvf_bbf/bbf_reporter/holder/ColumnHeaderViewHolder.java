package com.project.ignacio_rvf_bbf.bbf_reporter.holder;

import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;

import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.evrencoskun.tableview.sort.SortState;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.ColumnHeaderModel;

/**
 * Created by Ignacio-RVF-BBF on 23/01/2018.
 */

public class ColumnHeaderViewHolder extends AbstractSorterViewHolder {

    private static final String LOG_TAG = ColumnHeaderViewHolder.class.getSimpleName();

    final LinearLayout column_header_container;
    final TextView column_header_textview;
    final ImageButton column_header_sortButton;
    final ITableView tableView;


    public ColumnHeaderViewHolder(View itemView, ITableView pTableView) {
        super(itemView);
        tableView = pTableView;
        column_header_textview = itemView.findViewById(R.id.column_header_textView);
        column_header_container = itemView.findViewById(R.id.column_header_container);
        column_header_sortButton = itemView.findViewById(R.id.column_header_sortButton);

        //Establecer oyente click en el botón ordenar
        column_header_sortButton.setOnClickListener(mSortButtonClickListener);
    }


    /**
     * This method is calling from onBindColumnHeaderHolder on TableViewAdapter
     */
    public void setColumnHeaderModel(ColumnHeaderModel columnHeader) {
        column_header_textview.setText(String.valueOf(columnHeader.getData()));

        // If your TableView should have auto resize for cells & columns.
        // Then you should consider the below lines. Otherwise, you can ignore them.

        // It is necessary to remeasure itself.
        column_header_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        column_header_sortButton.requestLayout();
        column_header_textview.requestLayout();
        itemView.requestLayout();
    }

    public View.OnClickListener mSortButtonClickListener = (new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (getSortState() == SortState.ASCENDING) {
                tableView.sortColumn(getAdapterPosition(), SortState.DESCENDING);
            } else if (getSortState() == SortState.DESCENDING) {
                tableView.sortColumn(getAdapterPosition(), SortState.ASCENDING);
            } else {
                // Default one
                tableView.sortColumn(getAdapterPosition(), SortState.DESCENDING);
            }
        }
    });


    @Override
    public void onSortingStatusChanged(SortState pSortState){
        Log.e(LOG_TAG, " + onSortingStatusChanged : x:  " + getAdapterPosition() + " old state "
                + getSortState() + " current state : " + pSortState + " visiblity: " +
                column_header_sortButton.getVisibility());

        super.onSortingStatusChanged(pSortState);

        // It is necessary to remeasure itself.
        column_header_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;

        controlSortState(pSortState);

        Log.e(LOG_TAG, " - onSortingStatusChanged : x:  " + getAdapterPosition() + " old state "
                + getSortState() + " current state : " + pSortState + " visiblity: " +
                column_header_sortButton.getVisibility());

        column_header_textview.requestLayout();
        column_header_sortButton.requestLayout();
        column_header_container.requestLayout();
        itemView.requestLayout();

    }

    private void controlSortState(SortState pSortState) {
        if (pSortState == SortState.ASCENDING) {
            column_header_sortButton.setVisibility(View.VISIBLE);
            column_header_sortButton.setImageResource(R.drawable.ic_down);

        } else if (pSortState == SortState.DESCENDING) {
            column_header_sortButton.setVisibility(View.VISIBLE);
            column_header_sortButton.setImageResource(R.drawable.ic_up);
        } else {
            column_header_sortButton.setVisibility(View.GONE);
        }
    }

/**
 * @OLDCODE
 * */

/*
    public static final int[] COLUMN_TEXT_ALIGNS = {
            // Id
            Gravity.CENTER,
            // Name
            Gravity.LEFT,
            // Nickname
            Gravity.LEFT,
            // Email
            Gravity.LEFT,
            // BirthDay
            Gravity.CENTER,
            // Gender (Sex)
            Gravity.CENTER,
            // Age
            Gravity.CENTER,
            // Job
            Gravity.LEFT,
            // Salary
            Gravity.CENTER,
            // CreatedAt
            Gravity.CENTER,
            // UpdatedAt
            Gravity.CENTER,
            // Address
            Gravity.LEFT,
            // Zip Code
            Gravity.RIGHT,
            // Phone
            Gravity.RIGHT,
            // Fax
            Gravity.RIGHT
    };
*/

/*
    public void setColumnHeaderModel(ColumnHeaderModel pColumnHeaderModel, int pColumnPosition){

        //cambiar aliniamiento del textview
        column_header_textview.setGravity(COLUMN_TEXT_ALIGNS[pColumnPosition] | Gravity .CENTER_VERTICAL);
        //set text data
        column_header_textview.setText(pColumnHeaderModel.getData());
        // es necesario volver a medir
        column_header_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        column_header_textview.requestLayout();

    }
*/

    //Agrega color al seleccionar la columna...
    @Override
    public void setSelected(SelectionState p_nSelectionState){
        super.setSelected(p_nSelectionState);

        int nBackgroundColorId;
        int nForegroundColorId;

        if (p_nSelectionState == SelectionState.SELECTED){
            //nBackgroundColorId = R.color.selected_background_color;
            //nForegroundColorId = R.color.selected_text_color;
        }else if (p_nSelectionState == SelectionState.UNSELECTED){
            //nBackgroundColorId = R.color.selected_background_color;
            //nForegroundColorId = R.color.selected_text_color;
        }else { //SHADOWED
            //nBackgroundColorId = R.color.selected_background_color;
            //nForegroundColorId = R.color.selected_text_color;
        }

       /* column_header_container.setBackgroundColor(ContextCompat.getColor(column_header_container
        .getContext(), nBackgroundColorId));
       */
      /* column_header_textview.setTextColor(ContextCompat.getColor(column_header_container
       .getContext(), nForegroundColorId));
      */
    }
 /**
  * @END
  * */
}
