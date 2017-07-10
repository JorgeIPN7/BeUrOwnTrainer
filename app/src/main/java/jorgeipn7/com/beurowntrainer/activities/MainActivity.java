package jorgeipn7.com.beurowntrainer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import jorgeipn7.com.beurowntrainer.R;
import jorgeipn7.com.beurowntrainer.bd.DiaDB;
import jorgeipn7.com.beurowntrainer.bd.EjercicioBD;
import jorgeipn7.com.beurowntrainer.bd.RutinaBD;
import jorgeipn7.com.beurowntrainer.models.Dia;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;
import jorgeipn7.com.beurowntrainer.models.Rutina;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {




    Button btn_comenzar,
    btn_editar_rutina,
    btn_progreso,
    btn_avances,
    btn_accesorios;
    TextView tv_mensaje_pp;

    //BD
    Realm realm;
    EjercicioBD ejercicioBD;
    RutinaBD rutinaBD;
    DiaDB diaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        inicializarElementos();

        realm= Realm.getDefaultInstance();
        ejercicioBD= new EjercicioBD(realm);
        rutinaBD= new RutinaBD(realm);
        diaDB= new DiaDB(realm);

        /*
        ejercicioBD.crearNuevo(new Ejercicio(R.drawable.deadlift, "Dead Lift","Básico", "Espalda", "Pull", "Preparación", "Ejecución...", "Comentarios...", "https://www.youtube.com/watch?v=RyJbvWAh6ec"));

        rutinaBD.crearNuevo(new Rutina(5,0,5,0,80,0,60,90,0 ));


        Rutina r= rutinaBD.getRutinaByDia(1);
        Ejercicio e= ejercicioBD.getEjercicioById(1);

        rutinaBD.updateEjercicioRutina(r, e);

        diaDB.crearNuevo(new Dia(2 , "Martes"));

        diaDB.updateDiaAddRutina(diaDB.getRutinaById(1), rutinaBD.getRutinaByDia(1));
*/







        /*
        *
        * me falta crear los dias y relacionar la rutina con el dia
        * */



    }

    private void inicializarElementos(){
        btn_comenzar= (Button) findViewById(R.id.btn_comenzar);
        btn_editar_rutina= (Button) findViewById(R.id.btn_editar_rutina);
        btn_progreso= (Button) findViewById(R.id.btn_progreso);
        btn_avances= (Button) findViewById(R.id.btn_avances);
        btn_accesorios= (Button) findViewById(R.id.btn_accesorios);
        tv_mensaje_pp= (TextView) findViewById(R.id.tv_mensaje_pp);


        btn_comenzar.setOnClickListener(this);
        btn_editar_rutina.setOnClickListener(this);
        btn_progreso.setOnClickListener(this);
        btn_avances.setOnClickListener(this);
        btn_accesorios.setOnClickListener(this);

    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_editar_rutina:
                startActivity(new Intent(MainActivity.this, EditarRutina.class));
                break;
        }
    }
}
