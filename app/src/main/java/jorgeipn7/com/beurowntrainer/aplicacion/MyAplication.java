package jorgeipn7.com.beurowntrainer.aplicacion;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;
import jorgeipn7.com.beurowntrainer.models.Musculo;
import jorgeipn7.com.beurowntrainer.models.Rutina;

/**
 * Created by Jorge on 10/07/2017.
 */

public class MyAplication extends Application {
    public static AtomicInteger EjercicioId= new AtomicInteger();
    public static AtomicInteger MusculoId= new AtomicInteger();
    public static AtomicInteger RutinaId= new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();

        setDefaultRealmConfig();
        Realm realm= Realm.getDefaultInstance();
        EjercicioId= getIdByTable(realm, Ejercicio.class);
        MusculoId= getIdByTable(realm, Musculo.class);
        RutinaId= getIdByTable(realm, Rutina.class);
        realm.close();
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results= realm.where(anyClass).findAll();

        return (results.size()>0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }


    private void setDefaultRealmConfig() {
        Realm.init(getApplicationContext());

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        RealmConfiguration config= new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

    }
}
