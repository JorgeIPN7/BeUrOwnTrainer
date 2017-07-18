package jorgeipn7.com.beurowntrainer.bd;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;

/**
 * Created by Jorge on 10/07/2017.
 */

public class EjercicioBD {
    Realm realm;

    public EjercicioBD(Realm realm) {
        this.realm = realm;
    }

    //CREATE
    public void crearNuevo(Ejercicio ejercicio){
        realm.beginTransaction();
            realm.copyToRealm(ejercicio);
        realm.commitTransaction();
    }

    //READ
    public Ejercicio getEjercicioById(int id){
        return realm.where(Ejercicio.class).equalTo("id", id).findFirst();
    }

    public RealmResults<Ejercicio> getAllEjercicios(){
        return realm.where(Ejercicio.class).findAll();
    }

    public RealmResults<Ejercicio> getEjerciciosFavoritos(){
        return realm.where(Ejercicio.class).equalTo("favorito", true).findAll();
    }

    //UPDATE
    public void updateEjercicioFavortito(Ejercicio ejercicio, boolean flag){
        realm.beginTransaction();
            ejercicio.setFavorito(flag);
        realm.commitTransaction();
    }


}
