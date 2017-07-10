package jorgeipn7.com.beurowntrainer.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Jorge on 09/07/2017.
 */

public class Ejercicio extends RealmObject{

    @PrimaryKey
    private int id;

    private int foto;
    private String nombre;

    private String utilidad;
    private String mecanismo;
    private String tipoFuerza;

    private int descansoSerie;
    private int descansoFinal;

    private int series;
    private int peso;

    private Musculo tipoMusculo;

    public Ejercicio() {
    }

    public Ejercicio(int foto, String nombre, String utilidad, String mecanismo, String tipoFuerza, int descansoSerie, int descansoFinal, int series, int peso, Musculo tipoMusculo) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.utilidad = utilidad;
        this.mecanismo = mecanismo;
        this.tipoFuerza = tipoFuerza;
        this.descansoSerie = descansoSerie;
        this.descansoFinal = descansoFinal;
        this.series = series;
        this.peso = peso;
        this.tipoMusculo = tipoMusculo;
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

    public String getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(String utilidad) {
        this.utilidad = utilidad;
    }

    public String getMecanismo() {
        return mecanismo;
    }

    public void setMecanismo(String mecanismo) {
        this.mecanismo = mecanismo;
    }

    public String getTipoFuerza() {
        return tipoFuerza;
    }

    public void setTipoFuerza(String tipoFuerza) {
        this.tipoFuerza = tipoFuerza;
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

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Musculo getTipoMusculo() {
        return tipoMusculo;
    }

    public void setTipoMusculo(Musculo tipoMusculo) {
        this.tipoMusculo = tipoMusculo;
    }
}
