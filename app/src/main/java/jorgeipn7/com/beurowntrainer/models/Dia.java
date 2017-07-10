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
    private String nombre;
    private RealmList<Rutina> rutinas;

    public Dia() {
    }

    public Dia(int id, String nombre) {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RealmList<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(RealmList<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
}
