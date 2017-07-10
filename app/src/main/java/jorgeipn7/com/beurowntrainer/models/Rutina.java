package jorgeipn7.com.beurowntrainer.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import jorgeipn7.com.beurowntrainer.aplicacion.MyAplication;

/**
 * Created by Jorge on 10/07/2017.
 */

public class Rutina extends RealmObject{

    @PrimaryKey
    private int id;
    private int series1;
    private int series2;
    private int repeticiones1;
    private int repeticiones2;
    private int peso1;
    private int peso2;
    private int descansoSerie;
    private int descansoFinal;
    private int descansoBiSerie;

    private RealmList<Ejercicio> ejercicios; //Max 2.


    public Rutina() {
    }

    public Rutina(int series1, int series2, int repeticiones1, int repeticiones2, int peso1, int peso2, int descansoSerie, int descansoFinal, int descansoBiSerie) {
        this.id = MyAplication.RutinaId.incrementAndGet();
        this.series1 = series1;
        this.series2 = series2;
        this.repeticiones1 = repeticiones1;
        this.repeticiones2 = repeticiones2;
        this.peso1 = peso1;
        this.peso2 = peso2;
        this.descansoSerie = descansoSerie;
        this.descansoFinal = descansoFinal;
        this.descansoBiSerie = descansoBiSerie;
        this.ejercicios = new RealmList<Ejercicio>();
    }

    public int getId() {
        return id;
    }

    public int getSeries1() {
        return series1;
    }

    public void setSeries1(int series1) {
        this.series1 = series1;
    }

    public int getSeries2() {
        return series2;
    }

    public void setSeries2(int series2) {
        this.series2 = series2;
    }

    public int getRepeticiones1() {
        return repeticiones1;
    }

    public void setRepeticiones1(int repeticiones1) {
        this.repeticiones1 = repeticiones1;
    }

    public int getRepeticiones2() {
        return repeticiones2;
    }

    public void setRepeticiones2(int repeticiones2) {
        this.repeticiones2 = repeticiones2;
    }

    public int getPeso1() {
        return peso1;
    }

    public void setPeso1(int peso1) {
        this.peso1 = peso1;
    }

    public int getPeso2() {
        return peso2;
    }

    public void setPeso2(int peso2) {
        this.peso2 = peso2;
    }

    public int getDescansoSerie() {
        return descansoSerie;
    }

    public void setDescansoSerie(int descansoSerie) {
        this.descansoSerie = descansoSerie;
    }

    public int getDescansoFinal() {
        return descansoFinal;
    }

    public void setDescansoFinal(int descansoFinal) {
        this.descansoFinal = descansoFinal;
    }

    public int getDescansoBiSerie() {
        return descansoBiSerie;
    }

    public void setDescansoBiSerie(int descansoBiSerie) {
        this.descansoBiSerie = descansoBiSerie;
    }

    public RealmList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(RealmList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
