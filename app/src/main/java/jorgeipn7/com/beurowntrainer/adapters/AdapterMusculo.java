package jorgeipn7.com.beurowntrainer.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.models.Musculo;

/**
 * Created by Jorge on 17/07/2017.
 */

public class AdapterMusculo extends RecyclerView.Adapter<AdapterMusculo.ViewHolder>{

    List<Musculo> lista;
    int layout;
    Activity activity;
    OnItemClickListener listener;

    public AdapterMusculo( Activity activity, int layout, List<Musculo> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_cv_seleccionar_ejercicio_foto;

        public ViewHolder(View v) {
            super(v);

            iv_cv_seleccionar_ejercicio_foto= (ImageView) v.findViewById(R.id.iv_cv_seleccionar_ejercicio_foto);
        }

        public void bind(final Musculo musculo, final OnItemClickListener listener){
            iv_cv_seleccionar_ejercicio_foto.setImageResource(musculo.getFoto());
            iv_cv_seleccionar_ejercicio_foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(musculo, getAdapterPosition());
                }
            });
        }

    }


    @Override
    public AdapterMusculo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterMusculo.ViewHolder holder, int position) {
        holder.bind(lista.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Musculo musculo, int position);
    }
}
