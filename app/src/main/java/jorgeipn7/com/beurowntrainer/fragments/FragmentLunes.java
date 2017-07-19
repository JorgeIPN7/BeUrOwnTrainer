package jorgeipn7.com.beurowntrainer.fragments;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.activities.EditarRutinaAgregarEjercicio;
import jorgeipn7.com.beurowntrainer.adapters.AdapterRutina;
import jorgeipn7.com.beurowntrainer.bd.DiaDB;
import jorgeipn7.com.beurowntrainer.bd.RutinaBD;
import jorgeipn7.com.beurowntrainer.models.Dia;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;
import jorgeipn7.com.beurowntrainer.models.Rutina;

/**
 * A simple {@link Fragment} subclass.
 */

//fragment_editar_rutina_lunes

public class FragmentLunes extends Fragment implements View.OnClickListener{

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

    private static final int REQUEST_CODE= 1;

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

    public FragmentLunes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        realm= Realm.getDefaultInstance();
        diaBD= new DiaDB(realm);
        rutinaBD= new RutinaBD(realm);
        dia= diaBD.getDiaById(REQUEST_CODE);


        rutinas= diaBD.getAllRutinaByDia(diaBD.getDiaById(1));


        View v= inflater.inflate(R.layout.fragment_editar_rutina_lunes, container, false);

        recyclerView= v.findViewById(R.id.recyclerview_ejercicios_dia_rutina);

        layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //(int layout, Activity activity, List<Rutina> lista, OnItemClickListener listener)
        adapter= new AdapterRutina(R.layout.card_view_rutina_ejercicio, getActivity(), rutinas, new AdapterRutina.OnItemClickListener() {
            @Override
            public void onItemClick(Rutina rutina, int position) {
                Log.d("Fragment", "Foto");
            }
        }, new AdapterRutina.OnItemClickListener() {
            @Override
            public void onItemClick(Rutina rutina, int position) {
                Log.d("Fragment", "Borrar Rutina");

                diaBD.deleteRutina(dia, rutina);
                rutinaBD.deleteRutina(rutina);
            }
        }, new AdapterRutina.OnItemClickListener() {
            @Override
            public void onItemClick(Rutina rutina, int position) {
                Log.d("Fragment", "Editar Rutina");


                dialogo();
                rutinaAux= rutina;

                iv_dialog_ejercicio_agregar.setImageResource(rutinaBD.getEjercicioFromRutina(rutina).getFoto());
                et_num_series.setText(String.valueOf(rutina.getSeries1()));
                et_num_repeticiones.setText(String.valueOf(rutina.getRepeticiones1()));
                et_peso.setText(String.valueOf(rutina.getPeso1()));
                et_descanso_serie.setText(String.valueOf(rutina.getDescansoSerie()));
                et_descanso_final.setText(String.valueOf(rutina.getDescansoFinal()));

                btn_agregar_ejercicio.setText(getResources().getString(R.string.actualizar));
                dialogUpdateRutina.show();

            }
        });
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
                Log.d("FragmentLunes", "Click: btn agregar 2");
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

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdateRutina.cancel();
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

                    updateEjercicio(rutinaAux);
                    adapter.notifyDataSetChanged();
                    dialogUpdateRutina.cancel();
                }








            }
        });

    }


    private void updateEjercicio(Rutina rutina){
        rutinaBD.updateRutina(rutina, new Rutina( series1, series2, repeticiones1, repeticiones2, peso1, peso2, descansoSerie, descansoFinal, descansoBiSerie) );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            if(requestCode == REQUEST_CODE && resultCode== REQUEST_CODE){
                Log.d("insert", "FragmentLunes: notifyDataSetChanged");
                adapter.notifyDataSetChanged();
            }
    }







}
