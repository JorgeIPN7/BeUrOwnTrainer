package jorgeipn7.com.beurowntrainer.activities;


import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.adapters.AdapterEjercicio;
import jorgeipn7.com.beurowntrainer.adapters.AdapterMusculo;
import jorgeipn7.com.beurowntrainer.bd.DiaDB;
import jorgeipn7.com.beurowntrainer.bd.EjercicioBD;
import jorgeipn7.com.beurowntrainer.bd.MusculoBD;
import jorgeipn7.com.beurowntrainer.bd.RutinaBD;
import jorgeipn7.com.beurowntrainer.models.Dia;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;
import jorgeipn7.com.beurowntrainer.models.Musculo;
import jorgeipn7.com.beurowntrainer.models.Rutina;


public class EditarRutinaAgregarEjercicio extends AppCompatActivity {

    CoordinatorLayout coordinator_layout_agregar_ejercicio;

    private int REQUEST_CODE;
    private static final int REQUEST_CODE_LUNES= 1;
    private static final int REQUEST_CODE_MARTES= 2;
    private static final int REQUEST_CODE_MIERCOLES= 3;
    private static final int REQUEST_CODE_JUEVES= 4;
    private static final int REQUEST_CODE_VIERNES= 5;
    private static final int REQUEST_CODE_SABADO= 6;
    private static final int REQUEST_CODE_DOMINGO= 7;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    //Recycler Lista de Musculos
    RecyclerView recyclerViewMusculo;
    RecyclerView.LayoutManager layoutManagerMusculo;
    RecyclerView.Adapter adapterMusculo;
    //------
    Realm realm;
    MusculoBD musculoBD;
    DiaDB diaDB;
    RutinaBD rutinaBD;
    Dia dia;
    EjercicioBD ejercicioBD;
    private RealmList<Ejercicio> ejercicios;
    private RealmResults<Ejercicio> allEjercicios;
    private RealmResults<Musculo> allMusculos;
    //Dialog
    Dialog dialogAgregarNewEjercicio;
    ImageView iv_dialog_ejercicio_agregar;
    TextInputEditText et_num_series,
            et_num_repeticiones,
            et_peso,
            et_descanso_serie,
            et_descanso_final;
    Button btn_salir, btn_agregar_ejercicio;
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
    Button btn_salir_info, btn_agregar_ejercicio_info;
    CheckBox cb_dialogo_info_ejercicio_favorito;
    //Insert
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
    Ejercicio ejercicioAUX;
    //Favoritos
    Button btn_ver_favoritos;
    boolean FLAG_FAVORITOS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_rutina_agregar_ejercicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showToolbar("Agregar Ejercicio", true);

        coordinator_layout_agregar_ejercicio = (CoordinatorLayout) findViewById(R.id.coordinator_layout_agregar_ejercicio);
        REQUEST_CODE = getIntent().getIntExtra("dia", 0);
        if (REQUEST_CODE == 0) finish();

        realm = Realm.getDefaultInstance();
        musculoBD = new MusculoBD(realm);
        ejercicioBD = new EjercicioBD(realm);
        diaDB = new DiaDB(realm);
        rutinaBD = new RutinaBD(realm);
        dia = diaDB.getDiaById(REQUEST_CODE);

        dialogo();
        dialogoInfo();

        allMusculos = musculoBD.getAllMusculos();
        allEjercicios = ejercicioBD.getAllEjercicios();


        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_agregar_ejercicios);
        recyclerViewMusculo = (RecyclerView) findViewById(R.id.recyclerview_ejercicios_dia_rutina_musculos);

        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        //LinearLayoutManager(getApplicationContext());
        layoutManagerMusculo = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerViewMusculo.setLayoutManager(layoutManagerMusculo);


        setRecyclerViewAllEjercicios(allEjercicios);


        adapterMusculo = new AdapterMusculo(this, R.layout.card_view_seleccionar_musculo, allMusculos, new AdapterMusculo.OnItemClickListener() {
            @Override
            public void onItemClick(final Musculo musculo, int position) {




                ejercicios= musculoBD.getAllEjerciciosByMusculo(musculo);
                setRecyclerViewEjercicios(ejercicios);


            }
        });
        recyclerViewMusculo.setAdapter(adapterMusculo);


        //Ver Favoritos
        FLAG_FAVORITOS=false;
        btn_ver_favoritos= (Button) findViewById(R.id.btn_ver_favoritos);
        btn_ver_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("btn_fav", "FLAG: " +  FLAG_FAVORITOS);
                if(FLAG_FAVORITOS == false) {
                    FLAG_FAVORITOS = true;
                    setRecyclerViewAllEjercicios(ejercicioBD.getEjerciciosFavoritos());
                }
                else {
                    FLAG_FAVORITOS= false;
                    setRecyclerViewAllEjercicios(allEjercicios);
                }

            }
        });


    }



    private void setRecyclerViewAllEjercicios(RealmResults<Ejercicio> AllEjercicios){

        adapter = new AdapterEjercicio(R.layout.card_view_ejercicio, this, AllEjercicios, new AdapterEjercicio.OnItemClickListener() {
            @Override
            public void onItemClick(final Ejercicio ejercicio, int position) {


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

                btn_agregar_ejercicio_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogAgregarNewEjercicio.setTitle(getResources().getString(ejercicio.getNombre()));
                        iv_dialog_ejercicio_agregar.setImageResource(ejercicio.getFoto());
                        dialogAgregarNewEjercicio.show();

                        ejercicioAUX = ejercicio;
                    }
                });

                cb_dialogo_info_ejercicio_favorito.setChecked(ejercicio.isFavorito());
                cb_dialogo_info_ejercicio_favorito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("CheckBoxFav1", "->" + ejercicio.isFavorito()  );
                        if(ejercicio.isFavorito() == false)
                            ejercicioBD.updateEjercicioFavortito( ejercicio, true );
                        else ejercicioBD.updateEjercicioFavortito( ejercicio, false );
                        adapter.notifyDataSetChanged();
                        Log.d("CheckBoxFav2", "->" + ejercicio.isFavorito()  );

                    }
                });
                dialogoInformacion.show();


            }
        }, new AdapterEjercicio.OnItemClickListener() {
            @Override
            public void onItemClick(final Ejercicio ejercicio, int position) {

                dialogAgregarNewEjercicio.setTitle(getResources().getString(ejercicio.getNombre()));
                iv_dialog_ejercicio_agregar.setImageResource(ejercicio.getFoto());
                dialogAgregarNewEjercicio.show();

                ejercicioAUX = ejercicio;

            }
        }, new AdapterEjercicio.OnItemClickListener() {
            @Override
            public void onItemClick(Ejercicio ejercicio, int position) {

                Log.d("CheckBoxFav1", "->" + ejercicio.isFavorito()  );
                if(ejercicio.isFavorito() == false)
                    ejercicioBD.updateEjercicioFavortito( ejercicio, true );
                else ejercicioBD.updateEjercicioFavortito( ejercicio, false );
                adapter.notifyDataSetChanged();
                Log.d("CheckBoxFav2", "->" + ejercicio.isFavorito()  );

            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setRecyclerViewEjercicios(RealmList<Ejercicio> ejercicios){

        adapter = new AdapterEjercicio(R.layout.card_view_ejercicio, this, ejercicios, new AdapterEjercicio.OnItemClickListener() {
            @Override
            public void onItemClick(final Ejercicio ejercicio, int position) {


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

                btn_agregar_ejercicio_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogAgregarNewEjercicio.setTitle(getResources().getString(ejercicio.getNombre()));
                        iv_dialog_ejercicio_agregar.setImageResource(ejercicio.getFoto());
                        dialogAgregarNewEjercicio.show();

                        ejercicioAUX = ejercicio;
                    }
                });


                cb_dialogo_info_ejercicio_favorito.setChecked(ejercicio.isFavorito());
                cb_dialogo_info_ejercicio_favorito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("CheckBoxFav1", "->" + ejercicio.isFavorito()  );
                        if(ejercicio.isFavorito() == false)
                            ejercicioBD.updateEjercicioFavortito( ejercicio, true );
                        else ejercicioBD.updateEjercicioFavortito( ejercicio, false );
                        Log.d("CheckBoxFav2", "->" + ejercicio.isFavorito()  );

                    }
                });
                dialogoInformacion.show();


            }
        }, new AdapterEjercicio.OnItemClickListener() {
            @Override
            public void onItemClick(final Ejercicio ejercicio, int position) {

                dialogAgregarNewEjercicio.setTitle(getResources().getString(ejercicio.getNombre()));
                iv_dialog_ejercicio_agregar.setImageResource(ejercicio.getFoto());
                dialogAgregarNewEjercicio.show();

                ejercicioAUX = ejercicio;

            }
        }, new AdapterEjercicio.OnItemClickListener() {
            @Override
            public void onItemClick(Ejercicio ejercicio, int position) {

                Log.d("CheckBoxFav1", "->" + ejercicio.isFavorito()  );
                if(ejercicio.isFavorito() == false)
                    ejercicioBD.updateEjercicioFavortito( ejercicio, true );
                else ejercicioBD.updateEjercicioFavortito( ejercicio, false );
                adapter.notifyDataSetChanged();
                Log.d("CheckBoxFav2", "->" + ejercicio.isFavorito()  );

            }
        });
        recyclerView.setAdapter(adapter);
    }






    private void addEjercicio(Ejercicio ejercicio){
            rutinaBD.crearNuevo(new Rutina( series1, series2, repeticiones1, repeticiones2, peso1, peso2, descansoSerie, descansoFinal, descansoBiSerie));
            //==================================================================================
            rutinaBD.updateRutinaAddEjercicio(rutinaBD.getLastIdInserted(), ejercicio);
            //Log.d("Insert", "Rutina: " +  rutinaBD.getLastIdInserted().toString());
            diaDB.updateDiaAddRutina(dia, rutinaBD.getLastIdInserted());
            //==================================================================================
            Intent i= new Intent();
            setResult(getREQUEST_CODE(REQUEST_CODE), i);
            finish();
    }





    private void dialogo(){
        dialogAgregarNewEjercicio = new Dialog(this,android.R.style.Theme_DeviceDefault_Dialog);
        dialogAgregarNewEjercicio.setContentView(R.layout.dialogo_ejercicio_agregar);

        iv_dialog_ejercicio_agregar= (ImageView) dialogAgregarNewEjercicio.findViewById(R.id.iv_dialog_ejercicio_agregar);

        et_num_series= (TextInputEditText) dialogAgregarNewEjercicio.findViewById(R.id.et_num_series);
        et_num_repeticiones= (TextInputEditText) dialogAgregarNewEjercicio.findViewById(R.id.et_num_repeticiones);
        et_peso= (TextInputEditText) dialogAgregarNewEjercicio.findViewById(R.id.et_peso);
        et_descanso_serie= (TextInputEditText) dialogAgregarNewEjercicio.findViewById(R.id.et_descanso_serie);
        et_descanso_final= (TextInputEditText) dialogAgregarNewEjercicio.findViewById(R.id.et_descanso_final);

        btn_salir=(Button)dialogAgregarNewEjercicio.findViewById(R.id.btn_salir);
        btn_agregar_ejercicio=(Button)dialogAgregarNewEjercicio.findViewById(R.id.btn_agregar_ejercicio);

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAgregarNewEjercicio.cancel();
            }
        });


        btn_agregar_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                     addEjercicio(ejercicioAUX);
                     dialogAgregarNewEjercicio.cancel();
                }








            }
        });

    }

    private void dialogoInfo(){

        dialogoInformacion = new Dialog(this,android.R.style.Theme_DeviceDefault_Dialog);
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


        btn_salir_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoInformacion.cancel();
            }
        });


    }

    private int getREQUEST_CODE(int REQUEST_CODE){
        switch (REQUEST_CODE){
            case 1:
                return REQUEST_CODE_LUNES;
            case 2:
                return REQUEST_CODE_MARTES;
            case 3:
                return REQUEST_CODE_MIERCOLES;
            case 4:
                return REQUEST_CODE_JUEVES;
            case 5:
                return REQUEST_CODE_VIERNES;
            case 6:
                return REQUEST_CODE_SABADO;
            case 7:
                return REQUEST_CODE_DOMINGO;
            default: return 0;
        }
    }

   private void showToolbar(String titulo, boolean upButton){
       Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle(titulo);
       getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
   }

}
