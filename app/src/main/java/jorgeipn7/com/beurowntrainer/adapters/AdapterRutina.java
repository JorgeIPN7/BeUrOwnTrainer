package jorgeipn7.com.beurowntrainer.adapters;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    OnItemClickListener listenerBorrar;
    OnItemClickListener listenerEditar;
    OnItemClickListener listener2;
    OnItemClickListener listenerBorrar2;
    OnItemClickListener listenerEditar2;

    public AdapterRutina(int layout, Activity activity, List<Rutina> lista, OnItemClickListener listener, OnItemClickListener listenerBorrar, OnItemClickListener listenerEditar, OnItemClickListener listener2, OnItemClickListener listenerBorrar2, OnItemClickListener listenerEditar2) {
        this.lista = lista;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
        this.listenerBorrar = listenerBorrar;
        this.listenerEditar = listenerEditar;
        this.listener2 = listener2;
        this.listenerBorrar2 = listenerBorrar2;
        this.listenerEditar2 = listenerEditar2;
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

        //-------------------------------------------
        ImageView iv_cv_ejercicio_foto2;
        TextView tv_cv_ejercicio_titulo2,
                tv_cv_ejercicio_utilidad_valor2,
                tv_cv_ejercicio_mecanismo_valor2,
                tv_cv_ejercicio_fuerza_valor2,
                tv_cv_ejercicio_num_series_valor2,
                tv_cv_ejercicio_pesot_valor2,
                tv_cv_ejercicio_descanso_serie_valor2;

        ImageView iv_cv_ejercicio_borrar2,
                iv_cv_ejercicio_editar2;

        //---------------------------------------------
        View viewCL, viewCL2, viewCL22;
        TextView tv_cv_ejercicio_clasificacion2,
                tv_cv_ejercicio_rutina_utilidad2,
                tv_cv_ejercicio_rutina_mecanismo2,
                tv_cv_ejercicio_rutina_fuerza2,
                tv_cv_ejercicio_num_series2,
                tv_cv_ejercicio_pesot2,
                tv_cv_ejercicio_descanso_serie2;



        public ViewHolder(View v) {
            super(v);

            iv_cv_ejercicio_foto = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_rutina_foto);
            tv_cv_ejercicio_titulo = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_titulo);
            tv_cv_ejercicio_utilidad_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_utilidad_valor);
            tv_cv_ejercicio_mecanismo_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_mecanismo_valor);
            tv_cv_ejercicio_fuerza_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_fuerza_valor);
            tv_cv_ejercicio_num_series_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_num_series_valor);
            tv_cv_ejercicio_pesot_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_pesot_valor);
            tv_cv_ejercicio_descanso_serie_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_descanso_serie_valor);
            tv_cv_ejercicio_descanso_final_valor = (TextView) v.findViewById(R.id.tv_cv_ejercicio_descanso_final_valor);

            iv_cv_ejercicio_borrar = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_borrar);
            iv_cv_ejercicio_editar = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_editar);

        //--------------------------------------------------------------------------------------

            iv_cv_ejercicio_foto2 = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_rutina_foto2);
            tv_cv_ejercicio_titulo2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_titulo2);
            tv_cv_ejercicio_utilidad_valor2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_utilidad_valor2);
            tv_cv_ejercicio_mecanismo_valor2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_mecanismo_valor2);
            tv_cv_ejercicio_fuerza_valor2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_fuerza_valor2);
            tv_cv_ejercicio_num_series_valor2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_num_series_valor2);
            tv_cv_ejercicio_pesot_valor2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_pesot_valor2);
            tv_cv_ejercicio_descanso_serie_valor2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_descanso_serie_valor2);

            iv_cv_ejercicio_borrar2 = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_borrar2);
            iv_cv_ejercicio_editar2 = (ImageView) v.findViewById(R.id.iv_cv_ejercicio_editar2);

            viewCL= (View) v.findViewById(R.id.viewCL);
            viewCL2= (View) v.findViewById(R.id.viewCL2);
            viewCL22= (View) v.findViewById(R.id.viewCL22);
            tv_cv_ejercicio_clasificacion2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_clasificacion2);
            tv_cv_ejercicio_rutina_utilidad2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_utilidad2);
            tv_cv_ejercicio_rutina_mecanismo2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_mecanismo2);
            tv_cv_ejercicio_rutina_fuerza2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_rutina_fuerza2);
            tv_cv_ejercicio_num_series2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_num_series2);
            tv_cv_ejercicio_pesot2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_pesot2);
            tv_cv_ejercicio_descanso_serie2 = (TextView) v.findViewById(R.id.tv_cv_ejercicio_descanso_serie2);


//          itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Rutina rutina, final OnItemClickListener listener, final OnItemClickListener listenerBorrar, final OnItemClickListener listenerEditar, final OnItemClickListener listener2, final OnItemClickListener listenerBorrar2, final OnItemClickListener listenerEditar2) {

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



                iv_cv_ejercicio_foto2.setVisibility(View.INVISIBLE);
                tv_cv_ejercicio_titulo2.setHeight(0);
                tv_cv_ejercicio_utilidad_valor2.setHeight(0);
                tv_cv_ejercicio_mecanismo_valor2.setHeight(0);
                tv_cv_ejercicio_fuerza_valor2.setHeight(0);

                tv_cv_ejercicio_num_series_valor2.setHeight(0);
                tv_cv_ejercicio_pesot_valor2.setHeight(0);

                tv_cv_ejercicio_descanso_serie_valor2.setHeight(0);
//-----
                viewCL.setVisibility(View.INVISIBLE);
                viewCL2.setVisibility(View.INVISIBLE);
                viewCL22.setVisibility(View.INVISIBLE);

                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
                iv_cv_ejercicio_editar2.setLayoutParams(layoutParams);
                iv_cv_ejercicio_editar2.setVisibility(View.INVISIBLE);

                iv_cv_ejercicio_borrar2.setLayoutParams(layoutParams);
                iv_cv_ejercicio_borrar2.setVisibility(View.INVISIBLE);


                tv_cv_ejercicio_clasificacion2.setHeight(0);
                tv_cv_ejercicio_rutina_utilidad2.setHeight(0);
                tv_cv_ejercicio_rutina_mecanismo2.setHeight(0);
                tv_cv_ejercicio_rutina_fuerza2.setHeight(0);
                tv_cv_ejercicio_num_series2.setHeight(0);
                tv_cv_ejercicio_pesot2.setHeight(0);
                tv_cv_ejercicio_descanso_serie2.setHeight(0);




            }else if(rutina.getEjercicios().size() == 2){

                Ejercicio ejercicio= rutina.getEjercicios().get(0);
                Ejercicio ejercicio2= rutina.getEjercicios().get(1);

                int numSeries= rutina.getSeries1();
                int pesoTotal= rutina.getRepeticiones1() * rutina.getPeso1();

                int numSeries2= rutina.getSeries2();
                int pesoTotal2= rutina.getRepeticiones2() * rutina.getPeso2();


                iv_cv_ejercicio_foto.setImageResource(ejercicio.getFoto());
                tv_cv_ejercicio_titulo.setText(ejercicio.getNombre());
                tv_cv_ejercicio_utilidad_valor.setText(ejercicio.getUtilidad());
                tv_cv_ejercicio_mecanismo_valor.setText(ejercicio.getMecanismo());
                tv_cv_ejercicio_fuerza_valor.setText(ejercicio.getTipoFuerza());

                tv_cv_ejercicio_num_series_valor.setText(String.valueOf(numSeries));
                tv_cv_ejercicio_pesot_valor.setText(String.valueOf(pesoTotal) + " Kgs");
                tv_cv_ejercicio_descanso_serie_valor.setText(String.valueOf(rutina.getDescansoSerie()) + " Seg");
                tv_cv_ejercicio_descanso_final_valor.setText(String.valueOf(rutina.getDescansoFinal()) + " Seg");

                iv_cv_ejercicio_foto2.setImageResource(ejercicio2.getFoto());
                tv_cv_ejercicio_titulo2.setText(ejercicio2.getNombre());
                tv_cv_ejercicio_utilidad_valor2.setText(ejercicio2.getUtilidad());
                tv_cv_ejercicio_mecanismo_valor2.setText(ejercicio2.getMecanismo());
                tv_cv_ejercicio_fuerza_valor2.setText(ejercicio2.getTipoFuerza());

                tv_cv_ejercicio_num_series_valor2.setText(String.valueOf(numSeries2));
                tv_cv_ejercicio_pesot_valor2.setText(String.valueOf(pesoTotal2) + " Kgs");

                tv_cv_ejercicio_descanso_serie_valor2.setText(String.valueOf(rutina.getDescansoBiSerie()) + " Seg");


                //---
                iv_cv_ejercicio_borrar.setVisibility(View.INVISIBLE);
                iv_cv_ejercicio_borrar.setEnabled(false);



            }


            iv_cv_ejercicio_borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerBorrar.onItemClick(rutina, getAdapterPosition());
                }
            });

            iv_cv_ejercicio_editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerEditar.onItemClick(rutina, getAdapterPosition());
                }
            });

            iv_cv_ejercicio_foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(rutina, getAdapterPosition());
                }
            });


            //--------------------------------
            iv_cv_ejercicio_borrar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerBorrar2.onItemClick(rutina, getAdapterPosition());
                }
            });

            iv_cv_ejercicio_editar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerEditar2.onItemClick(rutina, getAdapterPosition());
                }
            });

            iv_cv_ejercicio_foto2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener2.onItemClick(rutina, getAdapterPosition());
                }
            });
            //--------------------------------


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
        holder.bind(lista.get(position), listener, listenerBorrar, listenerEditar, listener2, listenerBorrar2, listenerEditar2);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Rutina rutina, int position );
    }

}
