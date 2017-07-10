package jorgeipn7.com.beurowntrainer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.adapters.AdapterRutina;
import jorgeipn7.com.beurowntrainer.bd.DiaDB;
import jorgeipn7.com.beurowntrainer.bd.RutinaBD;
import jorgeipn7.com.beurowntrainer.models.Rutina;

/**
 * A simple {@link Fragment} subclass.
 */

//fragment_editar_rutina_lunes

public class FragmentLunes extends Fragment {

    private RealmList<Rutina> rutinas;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    DiaDB diaBD;
    RutinaBD rutinaBD;
    Realm realm;

    public FragmentLunes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        realm= Realm.getDefaultInstance();
        diaBD= new DiaDB(realm);


        rutinas= diaBD.getAllRutinaByDia(diaBD.getRutinaById(1));


        View v= inflater.inflate(R.layout.fragment_editar_rutina_lunes, container, false);

        recyclerView= v.findViewById(R.id.recyclerview_ejercicios_dia_rutina);

        layoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //(int layout, Activity activity, List<Rutina> lista, OnItemClickListener listener)
        adapter= new AdapterRutina(R.layout.card_view_rutina_ejercicio, getActivity(), rutinas, new AdapterRutina.OnItemClickListener() {
            @Override
            public void onItemClick(Rutina rutina, int position) {

            }
        });
        recyclerView.setAdapter(adapter);

        return v;
    }

}
