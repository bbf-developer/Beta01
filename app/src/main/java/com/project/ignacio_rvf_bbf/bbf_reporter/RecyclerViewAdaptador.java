package com.project.ignacio_rvf_bbf.bbf_reporter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ignacio-RVF-BBF on 17/01/2018.
 */

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView empresa, mision, fecha;
        ImageView logoEmpresa;

        RecyclerView recyclerViewProximo;

        public ViewHolder(View itemView) {
            super(itemView);

            empresa= itemView.findViewById(R.id.textViewEmpresa);
            mision= itemView.findViewById(R.id.textViewMision);
            fecha= itemView.findViewById(R.id.textViewFecha);

            logoEmpresa= itemView.findViewById(R.id.imageViewEmpresa);
        }
    }

    public List<ProximoModelo> proximoLista;

    public RecyclerViewAdaptador(List<ProximoModelo> proximoLista) {
        this.proximoLista = proximoLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_visita,
               parent, false);

       ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //BinViewHolder esta dejando espacios entre views cards
        //holder.recyclerViewProximo.getLayoutParams().height = 1 ;


        holder.empresa.setText(proximoLista.get(position).getEmpresa());
        holder.mision.setText(proximoLista.get(position).getMision());
        holder.fecha.setText(proximoLista.get(position).getFecha());


        holder.logoEmpresa.setImageResource(proximoLista.get(position).getImgEmpresa());
    }

    @Override
    public int getItemCount() {
        return proximoLista.size();
    }
}
