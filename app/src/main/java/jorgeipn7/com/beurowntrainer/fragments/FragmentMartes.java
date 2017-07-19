package jorgeipn7.com.beurowntrainer.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmList;
import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.activities.EditarRutinaAgregarEjercicio;
import jorgeipn7.com.beurowntrainer.adapters.AdapterRutina;
import jorgeipn7.com.beurowntrainer.bd.DiaDB;
import jorgeipn7.com.beurowntrainer.bd.RutinaBD;
import jorgeipn7.com.beurowntrainer.models.Rutina;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMartes extends Fragment implements View.OnClickListener{

        private RealmList<Rutina> rutinas;
        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.Adapter adapter;

        DiaDB diaBD;
        RutinaBD rutinaBD;
        Realm realm;

        Button btn_editar_rutina_add1,
                btn_editar_rutina_add2;

        private static final int REQUEST_CODE= 2;

        public FragmentMartes() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            realm= Realm.getDefaultInstance();
            diaBD= new DiaDB(realm);


            rutinas= diaBD.getAllRutinaByDia(diaBD.getDiaById(REQUEST_CODE));

            View v= inflater.inflate(R.layout.fragment_editar_rutina_martes, container, false);

            recyclerView= v.findViewById(R.id.recyclerview_ejercicios_dia_rutina);

            layoutManager= new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            //(int layout, Activity activity, List<Rutina> lista, OnItemClickListener listener)
            adapter= new AdapterRutina(R.layout.card_view_rutina_ejercicio, getActivity(), rutinas, new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {

                }
            }, new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {

                }
            }, new AdapterRutina.OnItemClickListener() {
                @Override
                public void onItemClick(Rutina rutina, int position) {

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
                    Log.d("FragmentMartes", "Click: btn agregar 1");
                    Intent i= new Intent(getActivity(), EditarRutinaAgregarEjercicio.class);
                    i.putExtra("dia", REQUEST_CODE);
                    startActivityForResult(i, REQUEST_CODE);
                    break;
                case R.id.btn_editar_rutina_add2:
                    Log.d("FragmentMartes", "Click: btn agregar 2");
                    break;
                default:break;
            }
        }



        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {

            if(requestCode == REQUEST_CODE && resultCode== REQUEST_CODE){
                Log.d("insert", "FragmentMartes: notifyDataSetChanged");
                adapter.notifyDataSetChanged();
            }
        }







    }

