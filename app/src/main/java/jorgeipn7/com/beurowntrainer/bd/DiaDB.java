package jorgeipn7.com.beurowntrainer.bd;

import io.realm.Realm;
import io.realm.RealmList;
import jorgeipn7.com.beurowntrainer.models.Dia;
import jorgeipn7.com.beurowntrainer.models.Rutina;

/**
 * Created by Jorge on 10/07/2017.
 */

public class DiaDB {

    Realm realm;

    public DiaDB(Realm realm) {
        this.realm = realm;
    }

    //CREATE
    public void crearNuevo(Dia dia){
        realm.beginTransaction();
        realm.copyToRealm(dia);
        realm.commitTransaction();
    }

    //READ
    public Dia getDiaById(int id){
        return realm.where(Dia.class).equalTo("id",id).findFirst();
    }

    public RealmList<Rutina> getAllRutinaByDia(Dia dia){
        return dia.getRutinas();
    }


    //UPDATE
    public void updateDiaAddRutina(Dia dia, Rutina rutina){
        realm.beginTransaction();
            dia.getRutinas().add(rutina);
        realm.commitTransaction();
    }


    //DELETE
    public void deleteRutina(Dia dia, Rutina rutina){
        realm.beginTransaction();
            dia.getRutinas().remove(rutina);
        realm.commitTransaction();
    }


}
