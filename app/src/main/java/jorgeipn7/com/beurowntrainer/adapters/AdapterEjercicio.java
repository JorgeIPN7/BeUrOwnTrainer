package jorgeipn7.com.beurowntrainer.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;

/**
 * Created by Jorge on 12/07/2017.
 */

public class AdapterEjercicio extends RecyclerView.Adapter<AdapterEjercicio.ViewHolder>{

        List<Ejercicio> lista;
        int layout;
        Activity activity;
        OnItemClickListener listener;
        OnItemClickListener listenerFoto;


    public AdapterEjercicio(int layout, Activity activity, List<Ejercicio> lista, OnItemClickListener listener, OnItemClickListener listenerFoto) {
            this.lista = lista;
            this.layout = layout;
            this.activity = activity;
            this.listener = listener;
            this.listenerFoto = listenerFoto;
        }


    public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView iv_cv_ejercicio_foto,
                    iv_cv_ejercicio_informacion;
            CheckBox cb_cv_ejercicio_favorito;
            TextView tv_cv_ejercicio_titulo,
                    tv_cv_ejercicio_utilidad,
                    tv_cv_ejercicio_mecanismo,
                    tv_cv_ejercicio_fuerza;



            public ViewHolder(View v) {
                super(v);

                iv_cv_ejercicio_foto = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_foto);
                iv_cv_ejercicio_informacion = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_informacion);
                tv_cv_ejercicio_titulo = (TextView) v.findViewById(R.id.tv_cv_ejercicio_titulo);
                tv_cv_ejercicio_utilidad = (TextView) v.findViewById(R.id.tv_cv_ejercicio_utilidad);
                tv_cv_ejercicio_mecanismo = (TextView) v.findViewById(R.id.tv_cv_ejercicio_mecanismo);
                tv_cv_ejercicio_fuerza = (TextView) v.findViewById(R.id.tv_cv_ejercicio_fuerza);
                cb_cv_ejercicio_favorito= (CheckBox) v.findViewById(R.id.cb_cv_ejercicio_favorito);

//            itemView.setOnCreateContextMenuListener(this);
            }

            public void bind(final Ejercicio ejercicio, final OnItemClickListener listener, final OnItemClickListener listenerFoto ) {

                /*** Ejercicio
                 private int id;

                 private int foto;
                 private int nombre;
                 private boolean favorito;
                 //Clasificación
                 private int utilidad;
                 private int mecanismo;
                 private int tipoFuerza;
                 //Instrucciones
                 private int preparacion;
                 private int ejecucion;
                 private int comentarios;
                 private String urlInstrucciones;
                 */


                iv_cv_ejercicio_foto.setImageResource(ejercicio.getFoto());
                tv_cv_ejercicio_titulo.setText(ejercicio.getNombre());
                tv_cv_ejercicio_utilidad.setText(ejercicio.getUtilidad());
                tv_cv_ejercicio_mecanismo.setText(ejercicio.getMecanismo());
                tv_cv_ejercicio_fuerza.setText(ejercicio.getTipoFuerza());

                /*
                cb_cv_ejercicio_favorito.setOnClickListener(this);
                iv_cv_ejercicio_informacion.setOnClickListener(this);
                iv_cv_ejercicio_foto.setOnClickListener(this);
                */


                iv_cv_ejercicio_informacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(ejercicio, getAdapterPosition());
                    }
                });

                iv_cv_ejercicio_foto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listenerFoto.onItemClick(ejercicio, getAdapterPosition());
                    }
                });


            }


        }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(lista.get(position), listener, listenerFoto);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Ejercicio ejercicio, int position );
    }

}

