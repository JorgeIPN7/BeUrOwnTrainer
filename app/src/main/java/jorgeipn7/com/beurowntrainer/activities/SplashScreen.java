package jorgeipn7.com.beurowntrainer.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.realm.Realm;
import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.bd.DiaDB;
import jorgeipn7.com.beurowntrainer.bd.EjercicioBD;
import jorgeipn7.com.beurowntrainer.bd.MusculoBD;
import jorgeipn7.com.beurowntrainer.models.Dia;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;
import jorgeipn7.com.beurowntrainer.models.Musculo;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preferences=getSharedPreferences("PREFERENCIAS", Context.MODE_PRIVATE);
        if( !preferences.getBoolean("loadDatabase", false)){
            cargarPreferencias();
            guardarPreferencias();
        }

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                //codigo
            }

            public void onFinish() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }.start();
    }


    public void cargarPreferencias(){
        Realm realm= Realm.getDefaultInstance();
        DiaDB diaDB= new DiaDB(realm);
        EjercicioBD ejercicioBD= new EjercicioBD(realm);
        MusculoBD musculoBD= new MusculoBD(realm);

        //DIAS
        diaDB.crearNuevo(new Dia(1 , R.string.monday  ));
        diaDB.crearNuevo(new Dia(2 , R.string.tuesday  ));
        diaDB.crearNuevo(new Dia(3 , R.string.wednesday  ));
        diaDB.crearNuevo(new Dia(4 , R.string.thursday  ));
        diaDB.crearNuevo(new Dia(5 , R.string.friday  ));
        diaDB.crearNuevo(new Dia(6 , R.string.saturday  ));
        diaDB.crearNuevo(new Dia(7 , R.string.sunday  ));
        //EJERCICIOS
        //=PIERNA
        ejercicioBD.crearNuevo(new Ejercicio(R.drawable.pierna_cable_abduction, R.string.pierna_cable_abduction_nombre, R.string.utilidad_basico  , R.string.mecanismo_isolated, R.string.push, R.string.preparacion, R.string.ejecucion, R.string.comentarios, "https://www.youtube.com/watch?v=pvnR8CDb4BU"));
        ejercicioBD.crearNuevo(new Ejercicio(R.drawable.pierna_smith_kneeling_rear_kick, R.string.pierna_smith_kneeling_rear_kick_nombre, R.string.utilidad_auxiliar  , R.string.mecanismo_isolated, R.string.push, R.string.preparacion, R.string.ejecucion, R.string.comentarios, "https://www.youtube.com/watch?v=X_s8qQYZsfo"));



        //MUSCULOS
        musculoBD.crearNuevo(new Musculo(R.drawable.pierna, R.string.musculo_pierna));

        //MUSCULO-EJERCICIO
        musculoBD.updateMusculoAddRutina(musculoBD.getMusculoById(1), ejercicioBD.getEjercicioById(1));
        musculoBD.updateMusculoAddRutina(musculoBD.getMusculoById(1), ejercicioBD.getEjercicioById(2));
    }

    public void guardarPreferencias(){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putBoolean("loadDatabase", true);
        editor.commit();
    }
}
