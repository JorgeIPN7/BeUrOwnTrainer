package jorgeipn7.com.beurowntrainer.activities;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.fragments.FragmentLunes;

public class EditarRutina extends AppCompatActivity implements View.OnClickListener {


    Button btn_monday,
    btn_tuesday,
    btn_wednesday,
    btn_thursday,
    btn_friday,
    btn_saturday,
    btn_sunday;

    FragmentLunes fragmentLunes;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //contenido_editar_rutina





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_rutina);


        showToolBar("Editar Rutina");

        inicializarElementos();
    }

    private void inicializarElementos(){
        btn_monday= (Button) findViewById(R.id.btn_monday);
        btn_tuesday= (Button) findViewById(R.id.btn_tuesday);
        btn_wednesday= (Button) findViewById(R.id.btn_wednesday);
        btn_thursday= (Button) findViewById(R.id.btn_thursday);
        btn_friday= (Button) findViewById(R.id.btn_friday);
        btn_saturday= (Button) findViewById(R.id.btn_saturday);
        btn_sunday= (Button) findViewById(R.id.btn_sunday);

        btn_monday.setOnClickListener(this);
        btn_tuesday.setOnClickListener(this);
        btn_wednesday.setOnClickListener(this);
        btn_thursday.setOnClickListener(this);
        btn_friday.setOnClickListener(this);
        btn_saturday.setOnClickListener(this);
        btn_sunday.setOnClickListener(this);

        fragmentLunes = new FragmentLunes();

    }




    public void showToolBar(String titulo){
        //Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {

        fragmentManager= getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.btn_monday:
                showToolBar(getResources().getString(R.string.monday));
                fragmentTransaction.replace(R.id.contenido_editar_rutina, fragmentLunes);
                btn_monday.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccentChido));
                break;
            case R.id.btn_tuesday:
                showToolBar(getResources().getString(R.string.tuesday));
                btn_tuesday.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccentChido));
                break;
            case R.id.btn_wednesday:
                showToolBar(getResources().getString(R.string.wednesday));
                btn_wednesday.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccentChido));
                break;
            case R.id.btn_thursday:
                showToolBar(getResources().getString(R.string.thursday));
                btn_thursday.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccentChido));
                break;
            case R.id.btn_friday:
                showToolBar(getResources().getString(R.string.friday));
                btn_friday.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccentChido));
                break;
            case R.id.btn_saturday:
                showToolBar(getResources().getString(R.string.saturday));
                btn_saturday.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccentChido));
                break;
            case R.id.btn_sunday:
                showToolBar(getResources().getString(R.string.sunday));
                btn_sunday.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccentChido));
                break;
        }
        fragmentTransaction.commit();
    }
}
