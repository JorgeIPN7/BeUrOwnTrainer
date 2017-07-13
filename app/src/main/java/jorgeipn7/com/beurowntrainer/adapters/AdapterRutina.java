package jorgeipn7.com.beurowntrainer.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.List;

import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;
import jorgeipn7.com.beurowntrainer.models.Rutina;

/**
 * Created by Jorge on 10/07/2017.
 */

public class AdapterRutina extends RecyclerView.Adapter<AdapterRutina.ViewHolder>{

    List<Rutina> lista;
    int layout;
    Activity activity;
    OnItemClickListener listener;

    public AdapterRutina(int layout, Activity activity, List<Rutina> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_cv_ejercicio_foto;
        TextView tv_cv_ejercicio_titulo,
                tv_cv_ejercicio_utilidad_valor,
                tv_cv_ejercicio_mecanismo_valor,
                tv_cv_ejercicio_fuerza_valor,
                tv_cv_ejercicio_num_series_valor,
                tv_cv_ejercicio_pesot_valor,
                tv_cv_ejercicio_descanso_serie_valor,
                tv_cv_ejercicio_descanso_final_valor;
        ImageView iv_cv_ejercicio_borrar,
                iv_cv_ejercicio_editar;

        public ViewHolder(View v) {
            super(v);

            iv_cv_ejercicio_foto = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_rutina_foto);
            tv_cv_ejercicio_titulo = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_titulo);
            tv_cv_ejercicio_utilidad_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_utilidad_valor);
            tv_cv_ejercicio_mecanismo_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_mecanismo_valor);
            tv_cv_ejercicio_fuerza_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_fuerza_valor);
            tv_cv_ejercicio_num_series_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_num_series);
            tv_cv_ejercicio_pesot_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_pesot_valor);
            tv_cv_ejercicio_descanso_serie_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_descanso_serie_valor);
            tv_cv_ejercicio_descanso_final_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_descanso_final_valor);

            iv_cv_ejercicio_borrar = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_borrar);
            iv_cv_ejercicio_editar = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_editar);

//            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Rutina rutina, final OnItemClickListener listener) {

            /*** Rutina
             private int series1;
             private int series2;
             private int repeticiones1;
             private int repeticiones2;
             private int peso1;
             private int peso2;
             private int descansoSerie;
             private int descansoFinal;
             private int descansoBiSerie;
            private RealmList<Ejercicio> ejercicios; //Max 2.
            */

            if(rutina.getEjercicios().size() == 1){

                Ejercicio ejercicio= rutina.getEjercicios().get(0);
                int numSeries= rutina.getSeries1();
                int pesoTotal= rutina.getRepeticiones1() * rutina.getPeso1();


                iv_cv_ejercicio_foto.setImageResource(ejercicio.getFoto());
                tv_cv_ejercicio_titulo.setText(ejercicio.getNombre());
                tv_cv_ejercicio_utilidad_valor.setText(ejercicio.getUtilidad());
                tv_cv_ejercicio_mecanismo_valor.setText(ejercicio.getMecanismo());
                tv_cv_ejercicio_fuerza_valor.setText(ejercicio.getTipoFuerza());

                tv_cv_ejercicio_num_series_valor.setText(String.valueOf(numSeries));
                tv_cv_ejercicio_pesot_valor.setText(String.valueOf(pesoTotal) + " Kgs");
                tv_cv_ejercicio_descanso_serie_valor.setText(String.valueOf(rutina.getDescansoSerie()) + " Seg");
                tv_cv_ejercicio_descanso_final_valor.setText(String.valueOf(rutina.getDescansoFinal()) + " Seg");

            }else if(rutina.getEjercicios().size() == 2){

            }else{

            }

            iv_cv_ejercicio_foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(rutina, getAdapterPosition());
                }
            });

        }
    }

    @Override
    public AdapterRutina.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterRutina.ViewHolder holder, int position) {
        holder.bind(lista.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Rutina rutina, int position );
    }

}
