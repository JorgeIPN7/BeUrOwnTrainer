package jorgeipn7.com.beurowntrainer.bd;

import io.realm.Realm;
import io.realm.RealmList;
import jorgeipn7.com.beurowntrainer.models.Ejercicio;
import jorgeipn7.com.beurowntrainer.models.Rutina;

/**
 * Created by Jorge on 10/07/2017.
 */

public class RutinaBD {
    private Realm realm;

    public RutinaBD(Realm realm) {
        this.realm = realm;
    }

    //CREATE
    public void crearNuevo(Rutina rutina ){
        realm.beginTransaction();
            realm.copyToRealm(rutina);
        realm.commitTransaction();
    }

    //READ
    public Rutina getRutinaByDia(int id){
        return realm.where(Rutina.class).equalTo("id", id).findFirst();
    }


    //UPDATE
    public void updateEjercicioRutina(Rutina rutina, Ejercicio ejercicio){
        realm.beginTransaction();
        rutina.getEjercicios().add(ejercicio);
        realm.commitTransaction();
    }

}
