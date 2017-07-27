package jorgeipn7.com.beurowntrainer.fragments;

        import android.app.Dialog;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.design.widget.TextInputEditText;
        import android.support.design.widget.TextInputLayout;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import io.realm.Realm;
        import io.realm.RealmList;
        import jorgeipn7.com.beurowntrainer.R;
        import jorgeipn7.com.beurowntrainer.activities.EditarRutinaAgregarBiSerie;
        import jorgeipn7.com.beurowntrainer.activities.EditarRutinaAgregarEjercicio;
        import jorgeipn7.com.beurowntrainer.adapters.AdapterRutina;
        import jorgeipn7.com.beurowntrainer.bd.DiaDB;
        import jorgeipn7.com.beurowntrainer.bd.EjercicioBD;
        import jorgeipn7.com.beurowntrainer.bd.RutinaBD;
        import jorgeipn7.com.beurowntrainer.models.Dia;
        import jorgeipn7.com.beurowntrainer.models.Ejercicio;
        import jorgeipn7.com.beurowntrainer.models.Rutina;


    /**
     * A simple {@link Fragment} subclass.
     */
    public class FragmentSabado extends Fragment implements View.OnClickListener{

        private RealmList<Rutina> rutinas;
        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.Adapter adapter;

        DiaDB diaBD;
        RutinaBD rutinaBD;
        Realm realm;
        Dia dia;

        Button btn_editar_rutina_add1,
                btn_editar_rutina_add2;

        private static final int REQUEST_CODE= 6;
        //DialogInformacion
        Dialog dialogoInformacion;
        ImageView go_to_video,
                iv_dialog_ejercicio_info;
        TextView tv_d_ejercicio_informacion_titulo,
                tv_d_ejercicio_informacion_utilidad,
                tv_d_ejercicio_informacion_mecanismo,
                tv_d_ejercicio_informacion_fuerza,
                tv_d_ejercicio_informacion_preparacion,
                tv_d_ejercicio_informacion_ejecucion,
                tv_d_ejercicio_informacion_comentarios;
        TextInputLayout ti_descanso_final;

        Button btn_salir_info, btn_agregar_ejercicio_info;
        CheckBox cb_dialogo_info_ejercicio_favorito;
        //Dialog
        Dialog dialogUpdateRutina;
        ImageView iv_dialog_ejercicio_agregar;
        TextInputEditText et_num_series,
                et_num_repeticiones,
                et_peso,
                et_descanso_serie,
                et_descanso_final;
        Button btn_salir, btn_agregar_ejercicio;
        //Update
        int
                series1,
                series2,
                repeticiones1,
                repeticiones2,
                peso1,
                peso2,
                descansoSerie,
                descansoFinal,
                descansoBiSerie;
        Rutina rutinaAux;
        boolean FLAG_UPDATE_2DO_EJERCICIO;

        public FragmentSabado() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            FLAG_UPDATE_2DO_EJERCICIO=false;
            realm= Realm.getDefaultInstance();
            diaBD= new DiaDB(realm);
            rutinaBD= new RutinaBD(realm);
            dia= diaBD.getDiaById(REQUEST_CODE);


            rutinas= diaBD.getAllRutinaByDia(diaBD.getDiaById(REQUEST_CODE));


            View v= inflater.inflate(R.layout.fragment_editar_rutina, container, false);

            recyclerView= v.findViewById(R.id.recyclerview_ejercicios_dia_rutina);

            layoutManager= new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            //(int layout, Activity activity, List<Rutina> lista, OnItemClickListener listener)
            adapter= new AdapterRutina(R.layout.card_view_rutina_ejercicio, getActivity(), rutinas, new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {
                    Log.d("Fragment", "Foto");

                    dialogoInfo();
                    final EjercicioBD ejercicioBD = new EjercicioBD(realm);
                    final Ejercicio ejercicio = rutinaBD.getEjercicioFromRutina(rutina);

                    Log.d("Fragment", "ejercicio" + getResources().getString(ejercicio.getNombre()));

                    //dialogoInformacion.setTitle(getResources().getString(ejercicio.getNombre()));
                    iv_dialog_ejercicio_info.setImageResource(ejercicio.getFoto());
                    tv_d_ejercicio_informacion_titulo.setText(getResources().getString(ejercicio.getNombre()));
                    tv_d_ejercicio_informacion_utilidad.setText(getResources().getString(ejercicio.getUtilidad()));
                    tv_d_ejercicio_informacion_mecanismo.setText(getResources().getString(ejercicio.getMecanismo()));
                    tv_d_ejercicio_informacion_fuerza.setText(getResources().getString(ejercicio.getTipoFuerza()));
                    tv_d_ejercicio_informacion_preparacion.setText(getResources().getString(ejercicio.getPreparacion()));
                    tv_d_ejercicio_informacion_ejecucion.setText(getResources().getString(ejercicio.getEjecucion()));
                    tv_d_ejercicio_informacion_comentarios.setText(getResources().getString(ejercicio.getComentarios()));



                    go_to_video.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ejercicio.getUrlInstrucciones())));
                        }
                    });

                    cb_dialogo_info_ejercicio_favorito.setChecked(ejercicio.isFavorito());


                    cb_dialogo_info_ejercicio_favorito.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("CheckBoxFav1", "->" + ejercicio.isFavorito());
                            if (ejercicio.isFavorito() == false)
                                ejercicioBD.updateEjercicioFavortito(ejercicio, true);
                            else ejercicioBD.updateEjercicioFavortito(ejercicio, false);
                            adapter.notifyDataSetChanged();
                            Log.d("CheckBoxFav2", "->" + ejercicio.isFavorito());

                        }
                    });


                    dialogoInformacion.show();


                }
            }, new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {
                    Log.d("Fragment", "Borrar Rutina");
                    rutinaBD.deleteRutina(rutina);
                    adapter.notifyDataSetChanged();
                }
            }, new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {
                    Log.d("Fragment", "Editar Rutina");
                    FLAG_UPDATE_2DO_EJERCICIO=false;

                    dialogo();
                    rutinaAux = rutina;

                    iv_dialog_ejercicio_agregar.setImageResource(rutinaBD.getEjercicioFromRutina(rutina).getFoto());
                    et_num_series.setText(String.valueOf(rutina.getSeries1()));
                    et_num_repeticiones.setText(String.valueOf(rutina.getRepeticiones1()));
                    et_peso.setText(String.valueOf(rutina.getPeso1()));
                    et_descanso_serie.setText(String.valueOf(rutina.getDescansoSerie()));
                    et_descanso_final.setText(String.valueOf(rutina.getDescansoFinal()));

                    btn_agregar_ejercicio.setText(getResources().getString(R.string.actualizar));
                    dialogUpdateRutina.show();

                }
            }
                    //-----------------------------------------------------------------------------------------
                    //--------------------------------------------
                    //-------------
                    , new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {
                    Log.d("FragmentMiercoles", "Foto 2");
                    dialogoInfo();
                    final EjercicioBD ejercicioBD = new EjercicioBD(realm);
                    final Ejercicio ejercicio = rutinaBD.getEjercicio2FromRutina(rutina);

                    Log.d("Fragment", "ejercicio" + getResources().getString(ejercicio.getNombre()));

                    //dialogoInformacion.setTitle(getResources().getString(ejercicio.getNombre()));
                    iv_dialog_ejercicio_info.setImageResource(ejercicio.getFoto());
                    tv_d_ejercicio_informacion_titulo.setText(getResources().getString(ejercicio.getNombre()));
                    tv_d_ejercicio_informacion_utilidad.setText(getResources().getString(ejercicio.getUtilidad()));
                    tv_d_ejercicio_informacion_mecanismo.setText(getResources().getString(ejercicio.getMecanismo()));
                    tv_d_ejercicio_informacion_fuerza.setText(getResources().getString(ejercicio.getTipoFuerza()));
                    tv_d_ejercicio_informacion_preparacion.setText(getResources().getString(ejercicio.getPreparacion()));
                    tv_d_ejercicio_informacion_ejecucion.setText(getResources().getString(ejercicio.getEjecucion()));
                    tv_d_ejercicio_informacion_comentarios.setText(getResources().getString(ejercicio.getComentarios()));

                    go_to_video.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ejercicio.getUrlInstrucciones())));
                        }
                    });

                    cb_dialogo_info_ejercicio_favorito.setChecked(ejercicio.isFavorito());


                    cb_dialogo_info_ejercicio_favorito.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("CheckBoxFav1", "->" + ejercicio.isFavorito());
                            if (ejercicio.isFavorito() == false)
                                ejercicioBD.updateEjercicioFavortito(ejercicio, true);
                            else ejercicioBD.updateEjercicioFavortito(ejercicio, false);
                            adapter.notifyDataSetChanged();
                            Log.d("CheckBoxFav2", "->" + ejercicio.isFavorito());

                        }
                    });


                    dialogoInformacion.show();


                }
            }

                    ,new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {
                    Log.d("FragmentMiercoles", "Borrar 2");
                    rutinaBD.deleteRutina(rutina);
                    adapter.notifyDataSetChanged();
                }
            }

                    , new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {
                    Log.d("FragmentMiercoles", "Edit 2");
                    FLAG_UPDATE_2DO_EJERCICIO= true;
                    dialogo();
                    rutinaAux = rutina;

                    iv_dialog_ejercicio_agregar.setImageResource(rutinaBD.getEjercicio2FromRutina(rutina).getFoto());
                    et_num_series.setText(String.valueOf(rutina.getSeries2()));
                    et_num_repeticiones.setText(String.valueOf(rutina.getRepeticiones2()));
                    et_peso.setText(String.valueOf(rutina.getPeso2()));

                    et_descanso_serie.setText(String.valueOf(rutina.getDescansoBiSerie()));
                    ti_descanso_final.setVisibility(View.INVISIBLE);


                    btn_agregar_ejercicio.setText(getResources().getString(R.string.actualizar));
                    dialogUpdateRutina.show();
                }
            }


            );
            recyclerView.setAdapter(adapter);

            btn_editar_rutina_add1= (Button)v.findViewById(R.id.btn_editar_rutina_add1);
            btn_editar_rutina_add2= (Button)v.findViewById(R.id.btn_editar_rutina_add2);
            btn_editar_rutina_add1.setOnClickListener(this);
            btn_editar_rutina_add2.setOnClickListener(this);




            return v;
        }

        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.btn_editar_rutina_add1:
                    Intent i= new Intent(getActivity(), EditarRutinaAgregarEjercicio.class);
                    i.putExtra("dia", REQUEST_CODE);
                    startActivityForResult(i, REQUEST_CODE);
                    break;
                case R.id.btn_editar_rutina_add2:
                    Intent i2= new Intent(getActivity(), EditarRutinaAgregarBiSerie.class);
                    i2.putExtra("dia", REQUEST_CODE);
                    startActivityForResult(i2, REQUEST_CODE);
                    break;
                default:break;
            }
        }

        private void dialogo(){
            dialogUpdateRutina = new Dialog(getContext(),android.R.style.Theme_DeviceDefault_Dialog);
            dialogUpdateRutina.setContentView(R.layout.dialogo_ejercicio_agregar);

            iv_dialog_ejercicio_agregar= (ImageView) dialogUpdateRutina.findViewById(R.id.iv_dialog_ejercicio_agregar);

            et_num_series= (TextInputEditText) dialogUpdateRutina.findViewById(R.id.et_num_series);
            et_num_repeticiones= (TextInputEditText) dialogUpdateRutina.findViewById(R.id.et_num_repeticiones);
            et_peso= (TextInputEditText) dialogUpdateRutina.findViewById(R.id.et_peso);
            et_descanso_serie= (TextInputEditText) dialogUpdateRutina.findViewById(R.id.et_descanso_serie);
            et_descanso_final= (TextInputEditText) dialogUpdateRutina.findViewById(R.id.et_descanso_final);

            btn_salir=(Button)dialogUpdateRutina.findViewById(R.id.btn_salir);
            btn_agregar_ejercicio=(Button)dialogUpdateRutina.findViewById(R.id.btn_agregar_ejercicio);

            ti_descanso_final = (TextInputLayout) dialogUpdateRutina.findViewById(R.id.ti_descanso_final);

            btn_salir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogUpdateRutina.cancel();
                }
            });


            btn_agregar_ejercicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("FragmentMiercoles", "btn_agregar_ejercicio -> Click" );
                    Log.d("FragmentMiercoles", "update2do? -> " + FLAG_UPDATE_2DO_EJERCICIO );


                    if (!FLAG_UPDATE_2DO_EJERCICIO){
                        boolean FLAG= true;

                        if(TextUtils.isEmpty(et_num_series.getText().toString().trim())){
                            et_num_series.setError("Error");
                            FLAG= false;
                        }else series1= Integer.parseInt(et_num_series.getText().toString());

                        series2= 0;

                        if(TextUtils.isEmpty(et_num_repeticiones.getText().toString().trim())){
                            et_num_repeticiones.setError("Error");
                            FLAG= false;
                        }else repeticiones1= Integer.parseInt(et_num_repeticiones.getText().toString());

                        repeticiones2= 0;

                        if(TextUtils.isEmpty(et_peso.getText().toString().trim())){
                            et_peso.setError("Error");
                            FLAG= false;
                        }else peso1= Integer.parseInt(et_peso.getText().toString());

                        peso2= 0;

                        if(TextUtils.isEmpty(et_descanso_serie.getText().toString().trim())){
                            et_descanso_serie.setError("Error");
                            FLAG= false;
                        }else descansoSerie= Integer.parseInt(et_descanso_serie.getText().toString());

                        if(TextUtils.isEmpty(et_descanso_final.getText().toString().trim())){
                            et_descanso_final.setError("Error");
                            FLAG= false;
                        }else descansoFinal= Integer.parseInt(et_descanso_final.getText().toString());

                        descansoBiSerie= 0;

                        if(FLAG){
                            Log.d("Ejercicio", "num series: " + et_num_series.getText().toString() );
                            Log.d("Ejercicio", "num repeticiones: " + et_num_repeticiones.getText().toString() );
                            Log.d("Ejercicio", "peso: " + et_peso.getText().toString() );
                            Log.d("Ejercicio", "descanso serie: " + et_descanso_serie.getText().toString() );
                            Log.d("Ejercicio", "descanso final: " + et_descanso_final.getText().toString() );

                            updateEjercicio(rutinaAux);
                            adapter.notifyDataSetChanged();
                            dialogUpdateRutina.cancel();
                        }
                    }

                    else{
                        boolean FLAG= true;

                        if(TextUtils.isEmpty(et_num_series.getText().toString().trim())){
                            et_num_series.setError("Error");
                            FLAG= false;
                        }else series2= Integer.parseInt(et_num_series.getText().toString());

                        if(TextUtils.isEmpty(et_num_repeticiones.getText().toString().trim())){
                            et_num_repeticiones.setError("Error");
                            FLAG= false;
                        }else repeticiones2= Integer.parseInt(et_num_repeticiones.getText().toString());

                        if(TextUtils.isEmpty(et_peso.getText().toString().trim())){
                            et_peso.setError("Error");
                            FLAG= false;
                        }else peso2= Integer.parseInt(et_peso.getText().toString());

                        if(TextUtils.isEmpty(et_descanso_serie.getText().toString().trim())){
                            et_descanso_serie.setError("Error");
                            FLAG= false;
                        }else descansoBiSerie= Integer.parseInt(et_descanso_serie.getText().toString());


                        if(FLAG){
                            Log.d("Ejercicio", "num series: " + et_num_series.getText().toString() );
                            Log.d("Ejercicio", "num repeticiones: " + et_num_repeticiones.getText().toString() );
                            Log.d("Ejercicio", "peso: " + et_peso.getText().toString() );
                            Log.d("Ejercicio", "descanso serie: " + et_descanso_serie.getText().toString() );
                            Log.d("Ejercicio", "descanso final: " + et_descanso_final.getText().toString() );


                            update2doEjercicio(rutinaAux);
                            adapter.notifyDataSetChanged();
                            dialogUpdateRutina.cancel();

                        }

                    }







                }
            });

        }

        private void dialogoInfo(){

            dialogoInformacion = new Dialog(getContext(),android.R.style.Theme_DeviceDefault_Dialog);
            dialogoInformacion.setContentView(R.layout.dialogo_ejercicio_informacion);

            go_to_video= (ImageView) dialogoInformacion.findViewById(R.id.go_to_video);
            iv_dialog_ejercicio_info= (ImageView) dialogoInformacion.findViewById(R.id.iv_dialog_ejercicio_info);


            tv_d_ejercicio_informacion_titulo = (TextView) dialogoInformacion.findViewById(R.id.tv_d_ejercicio_informacion_titulo);
            tv_d_ejercicio_informacion_utilidad = (TextView) dialogoInformacion.findViewById(R.id.tv_d_ejercicio_informacion_utilidad);
            tv_d_ejercicio_informacion_mecanismo = (TextView) dialogoInformacion.findViewById(R.id.tv_d_ejercicio_informacion_mecanismo);
            tv_d_ejercicio_informacion_fuerza = (TextView) dialogoInformacion.findViewById(R.id.tv_d_ejercicio_informacion_fuerza);
            tv_d_ejercicio_informacion_preparacion = (TextView) dialogoInformacion.findViewById(R.id.tv_d_ejercicio_informacion_preparacion);
            tv_d_ejercicio_informacion_ejecucion = (TextView) dialogoInformacion.findViewById(R.id.tv_d_ejercicio_informacion_ejecucion);
            tv_d_ejercicio_informacion_comentarios = (TextView) dialogoInformacion.findViewById(R.id.tv_d_ejercicio_informacion_comentarios);

            btn_salir_info=(Button)dialogoInformacion.findViewById(R.id.btn_salir_info);
            btn_agregar_ejercicio_info=(Button)dialogoInformacion.findViewById(R.id.btn_agregar_ejercicio_info);

            cb_dialogo_info_ejercicio_favorito= (CheckBox)dialogoInformacion.findViewById(R.id.cb_dialogo_info_ejercicio_favorito);
            btn_agregar_ejercicio_info.setVisibility(View.INVISIBLE);
            btn_agregar_ejercicio_info.setVisibility(View.INVISIBLE);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, 100);
            lp.weight = 0;
            btn_agregar_ejercicio_info.setLayoutParams(lp);


            btn_salir_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogoInformacion.cancel();
                }
            });


        }

        private void updateEjercicio(Rutina rutina){
            rutinaBD.updateRutina(rutina, new Rutina( series1, series2, repeticiones1, repeticiones2, peso1, peso2, descansoSerie, descansoFinal, descansoBiSerie) );
        }

        private void update2doEjercicio(Rutina rutina){

            //Log.d("FragmentMiercoles", "Rutina " + rutina.toString());
            //Log.d("FragmentMiercoles", "RutinaAUX " + rutinaAux.toString());
            //Log.d("FragmentMiercoles", "New Rutina: " + series1 + ", " + series2 + ", " + repeticiones1 + ", " + repeticiones2 + ", " + peso1 + ", " + peso2 + ", " + descansoSerie + ", " + descansoFinal + ", " + descansoBiSerie  );
            rutinaBD.updateRutina2doEjercicio(rutina, new Rutina( series1, series2, repeticiones1, repeticiones2, peso1, peso2, descansoSerie, descansoFinal, descansoBiSerie) );
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {

            if(requestCode == REQUEST_CODE && resultCode== REQUEST_CODE){
                Log.d("insert", "FragmentMiercoles: notifyDataSetChanged");
                adapter.notifyDataSetChanged();
            }
        }







    }

