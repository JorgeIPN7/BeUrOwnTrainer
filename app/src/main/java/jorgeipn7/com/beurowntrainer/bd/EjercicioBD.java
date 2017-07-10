package jorgeipn7.com.beurowntrainer.bd;

import io.realm.Realm;
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

}
