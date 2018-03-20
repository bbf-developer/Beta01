package com.project.ignacio_rvf_bbf.bbf_reporter.tableadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.project.ignacio_rvf_bbf.bbf_reporter.R;
import com.project.ignacio_rvf_bbf.bbf_reporter.holder.CellViewHolder;
import com.project.ignacio_rvf_bbf.bbf_reporter.holder.ColumnHeaderViewHolder;
import com.project.ignacio_rvf_bbf.bbf_reporter.holder.RowHeaderViewHolder;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.CellModel;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.ColumnHeaderModel;
import com.project.ignacio_rvf_bbf.bbf_reporter.model.RowHeaderModel;

/**
 * Created by Ignacio-RVF-BBF on 23/01/2018.
 * @param
 */

public class MyTableViewAdapter extends AbstractTableAdapter<ColumnHeaderModel,RowHeaderModel,CellModel> {

    public MyTableViewAdapter(Context p_jContext) { super(p_jContext); }

    @Override
    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {

        //Obtener la celda de cell xml layout
        View layout = LayoutInflater.from(m_jContext).inflate(R.layout.tableview_cell_layout, parent, false);

        //Crear a Cell ViewHolder
        return new CellViewHolder(layout);
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition, int p_nYPosition) {
        //CREAR CLASE DE PRUEBA PARA SETEAR LOS DATOS EN EL BINDHOLDER DE LA CELDA

        CellModel cell = (CellModel) p_jValue;

        //Actualiza el elemento que contiene la celda (SIN CONFIRMAR).
        // Get the holder to update cell item text.
        //INSTANCIA PARA DIBUJAR LAS CELDAS CORRESPONDIENTES
        CellViewHolder viewHolder = (CellViewHolder) holder;

        //PONE PARAMETRO POR DEFECTO EN LAS CELDAS
        viewHolder.cell_textview.setText(String.valueOf(cell.getData()));

        /**Evrenkoskun:
         TableView debería tener el cambio de tamaño automático para celdas y columnas.
         Entonces deberías considerar las siguientes líneas. De lo contrario, puede ignorarlos.
         */
        viewHolder.cell_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        viewHolder.cell_textview.requestLayout();

        if (holder instanceof CellViewHolder){

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        //Obtener la columna en XML layout
        View layout = LayoutInflater.from(m_jContext).inflate(R.layout
        .tableview_column_header_layout, parent, false);
        //Crea columna viewholder
        return new ColumnHeaderViewHolder(layout, getTableView());
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nXPosition) {
        ColumnHeaderModel columnHeader = (ColumnHeaderModel) p_jValue;
        //obtener el texto para cargar la celda
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;
        columnHeaderViewHolder.setColumnHeaderModel(columnHeader);
    }

    @Override
    public RecyclerView.ViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        //Obtener la fila del header XML layout
        View layout = LayoutInflater.from(m_jContext).inflate(R.layout
                    .tableview_row_header_layout, parent, false);

        //crear fila de cabecera viewholder
        return new RowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int p_nYPosition) {

        RowHeaderModel rowHeaderModel = (RowHeaderModel) p_jValue;

        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText(String.valueOf(rowHeaderModel.getData()));
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(m_jContext).inflate(R.layout.tableview_corner_layout, null, false);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        /**@deprecated
         La ID única para este tipo de elemento de encabezado de columna
         Si tiene diferentes elementos para la vista de celda por posición X (columna),
         entonces deberías completar este método para poder crear diferentes
         tipo de CellViewHolder en "onCreateCellViewHolder"
         * */
        return 0; }

    @Override
    public int getRowHeaderItemViewType(int position) {
        /**@deprecated
         *La ID única para este tipo de elemento de encabezado de columna
         Si tiene diferentes elementos para la vista de celda por posición X (columna),
         entonces deberías completar este método para poder crear diferentes
         tipo de CellViewHolder en "onCreateCellViewHolder"
         * */
        return 0; }

    @Override
    public int getCellItemViewType(int position) {
        /**@deprecated
         *La ID única para este tipo de elemento de encabezado de columna
         Si tiene diferentes elementos para la vista de celda por posición X (columna),
         entonces deberías completar este método para poder crear diferentes
         tipo de CellViewHolder en "onCreateCellViewHolder"
         * */
        return 0; }

}

