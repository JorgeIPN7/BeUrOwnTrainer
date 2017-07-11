package jorgeipn7.com.beurowntrainer.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import jorgeipn7.com.beurowntrainer.aplicacion.MyAplication;

/**
 * Created by Jorge on 10/07/2017.
 */

public class Musculo extends RealmObject{

    @PrimaryKey
    private int id;
    private int foto;
    private int nombre;
    private RealmList<Ejercicio> ejercicios;

    public Musculo() {
    }

    public Musculo( int foto, int nombre) {
        this.id = MyAplication.MusculoId.incrementAndGet();
        this.foto = foto;
        this.nombre = nombre;
        this.ejercicios = new RealmList<Ejercicio>();
    }

    public int getId() {
        return id;
    }


    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public RealmList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(RealmList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
