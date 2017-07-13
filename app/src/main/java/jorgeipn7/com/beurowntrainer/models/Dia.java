package jorgeipn7.com.beurowntrainer.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jorge on 10/07/2017.
 */

public class Dia extends RealmObject{

    @PrimaryKey
    private int id; // Día de la semana  Dom 1-7 Sab
    private int nombre;
    private RealmList<Rutina> rutinas;

    public Dia() {
    }

    public Dia(int id, int nombre) {
        this.id = id;
        this.nombre = nombre;
        this.rutinas = new RealmList<Rutina>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public RealmList<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(RealmList<Rutina> rutinas) {
        this.rutinas = rutinas;
    }


}
