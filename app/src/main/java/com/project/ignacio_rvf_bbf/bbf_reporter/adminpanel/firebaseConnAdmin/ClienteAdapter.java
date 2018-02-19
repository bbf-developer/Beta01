package com.project.ignacio_rvf_bbf.bbf_reporter.adminpanel.firebaseConnAdmin;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.ignacio_rvf_bbf.bbf_reporter.R;

import java.util.List;

/**
 * Created by Ignacio-RVF-BBF on 31/01/2018.
 */

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.UserViewHolder>  {

    private List<ClienteTest> list;

    public ClienteAdapter(List<ClienteTest> list){
        this.list = list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cliente_test_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        ClienteTest cliente = list.get(position);

        holder.txtRut.setText(cliente.rut);
        holder.txtRazonsoc.setText(cliente.razonsoc);
        holder.txtGiro.setText(cliente.giro);
        holder.txtCodplanta.setText(cliente.codplanta);
        holder.txtNombreplant.setText(cliente.nomplanta);
        holder.txtDireccion.setText(cliente.direccion);
        holder.txtContacto.setText(cliente.contacto);
        holder.txtMail.setText(cliente.mail);
        holder.txtFono.setText(cliente.fono);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(),0,0,"¿Borrar Cliente?");
                //Sigue con 1,0,"" - 2,0,""..
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        public TextView txtRut;
        public TextView txtRazonsoc;
        public TextView txtGiro;

        public TextView txtCodplanta;
        public TextView txtNombreplant;
        public TextView txtDireccion;

        public TextView txtContacto;
        public TextView txtMail;
        public TextView txtFono;

        //public Button ButtonBorrar;

        public UserViewHolder(View itemView) {
            super(itemView);

            txtRut = itemView.findViewById(R.id.textRut);
            txtRazonsoc = itemView.findViewById(R.id.textRazonsoc);
            txtGiro = itemView.findViewById(R.id.textGiro);

            txtCodplanta = itemView.findViewById(R.id.textCodplanta);
            txtNombreplant = itemView.findViewById(R.id.textPlanta);
            txtDireccion= itemView.findViewById(R.id.textDireccion);

            txtContacto = itemView.findViewById(R.id.textContacto);
            txtMail = itemView.findViewById(R.id.textMail);
            txtFono = itemView.findViewById(R.id.textFono);

           // ButtonBorrar = itemView.findViewById(R.id.btnSupr);
        }
    }

}
