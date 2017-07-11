package jorgeipn7.com.beurowntrainer.bd;

import io.realm.Realm;
import io.realm.RealmList;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;
import jorgeipn7.com.beurowntrainer.models.Musculo;

/**
 * Created by Jorge on 11/07/2017.
 */

public class MusculoBD {

    Realm realm;

    public MusculoBD(Realm realm) {
        this.realm = realm;
    }

    //CREATE
    public void crearNuevo(Musculo musculo){
        realm.beginTransaction();
        realm.copyToRealm(musculo);
        realm.commitTransaction();
    }



    public Musculo getMusculoById(int id){
        return realm.where(Musculo.class).equalTo("id",id).findFirst();
    }

    public RealmList<Ejercicio> getAllEjerciciosByMusculo(Musculo musculo){
        return musculo.getEjercicios();
    }


    //UPDATE
    public void updateMusculoAddRutina(Musculo musculo, Ejercicio ejercicio){
        realm.beginTransaction();
        musculo.getEjercicios().add(ejercicio);
        realm.commitTransaction();
    }


}