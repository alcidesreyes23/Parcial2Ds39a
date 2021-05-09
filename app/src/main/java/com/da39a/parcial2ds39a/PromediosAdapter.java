package com.da39a.parcial2ds39a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PromediosAdapter extends RecyclerView.Adapter<PromediosAdapter.ViewHolder>  implements View.OnClickListener {

    private View.OnClickListener listener;

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo;
        private TextView promKm;
        private TextView promDinero;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.txvTipo);
            promKm = itemView.findViewById(R.id.txvPromKm);
            promDinero = itemView.findViewById(R.id.txvPromDinero);
        }
    }

    public List<ModelProm> listaProm;

    // Cambio
    public Context context;
    public PromediosAdapter(List<ModelProm> listaProm, Context context) {
        this.listaProm = listaProm;
        // Cambio >> se agregó un contexto como parametro para poder acceder al método que está en Promedios
        this.context = context;
    }

    @NonNull
    @Override
    //Inflate: Método para hacer uso de un layout dentro de otro layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        //Cambio
        //view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    //Método que realiza las modificaciones del contenido para cada item
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titulo.setText(listaProm.get(position).getTipoCombustible());
        holder.promKm.setText(listaProm.get(position).getKm());
        holder.promDinero.setText(listaProm.get(position).getMonto());

        // Cambio>> Se invoco al metódo data de la clase Promedios
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Promedios)context).data(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProm.size();
    }

    public void setOnClickListener(AdapterView.OnClickListener listener) {
        this.listener = listener;
    }
}