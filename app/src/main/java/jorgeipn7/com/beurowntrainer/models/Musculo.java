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
    private String nombre;
    private RealmList<Ejercicio> ejercicios;

    public Musculo() {
    }

    public Musculo( int foto, String nombre, RealmList<Ejercicio> ejercicios) {
        this.id = MyAplication.MusculoId.incrementAndGet();
        this.foto = foto;
        this.nombre = nombre;
        this.ejercicios = ejercicios;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RealmList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(RealmList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
