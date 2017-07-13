package jorgeipn7.com.beurowntrainer.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmList;
import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.adapters.AdapterEjercicio;
import jorgeipn7.com.beurowntrainer.bd.MusculoBD;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;


public class EditarRutinaAgregarEjercicio extends AppCompatActivity {

    TextView tv;


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

    Realm realm;
    MusculoBD musculoBD;
    private RealmList<Ejercicio> ejercicios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_rutina_agregar_ejercicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showToolbar("Agregar Ejercicio", true);

        Bundle bundle= this.getIntent().getExtras();


        realm= Realm.getDefaultInstance();
        musculoBD= new MusculoBD(realm);

        ejercicios= musculoBD.getAllEjerciciosByMusculo(musculoBD.getMusculoById(1));

        recyclerView= (RecyclerView)findViewById(R.id.recyclerview_agregar_ejercicios);

        layoutManager= new GridLayoutManager(getApplicationContext(), 2);
                            //LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        adapter= new AdapterEjercicio(R.layout.card_view_ejercicio, this, ejercicios, new AdapterEjercicio.OnItemClickListener() {
            @Override
            public void onItemClick(Ejercicio ejercicio, int position) {
                Toast.makeText(getApplicationContext(), "Ejercicio: " + getResources().getString(ejercicio.getNombre()), Toast.LENGTH_LONG).show();


            }
        });
        recyclerView.setAdapter(adapter);




/*
   Intent i= new Intent();
                String nombre= tv.getText().toString();
                i.putExtra("nombre", nombre);
                setResult(1234, i);
                finish();

 */



    }


   private void showToolbar(String titulo, boolean upButton){
       Toolbar toolbar= (Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle(titulo);
       getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
   }





}
