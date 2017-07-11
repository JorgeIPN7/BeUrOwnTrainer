package jorgeipn7.com.beurowntrainer.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import jorgeipn7.com.beurowntrainer.aplicacion.MyAplication;

/**
 * Created by Jorge on 09/07/2017.
 */

public class Ejercicio extends RealmObject{

    @PrimaryKey
    private int id;

    private int foto;
    private int nombre;
    private boolean favorito;
    //Clasificación
    private int utilidad;
    private int mecanismo;
    private int tipoFuerza;
    //Instrucciones
    private int preparacion;
    private int ejecucion;
    private int comentarios;
    private String urlInstrucciones;



    public Ejercicio() {
    }

    public Ejercicio(int foto, int nombre, int utilidad, int mecanismo, int tipoFuerza, int preparacion, int ejecucion, int comentarios, String urlInstrucciones) {
        this.id = MyAplication.EjercicioId.incrementAndGet();
        this.foto = foto;
        this.nombre = nombre;
        this.utilidad = utilidad;
        this.mecanismo = mecanismo;
        this.tipoFuerza = tipoFuerza;
        this.preparacion = preparacion;
        this.ejecucion = ejecucion;
        this.comentarios = comentarios;
        this.urlInstrucciones = urlInstrucciones;
        this.favorito= false;
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

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    public int getMecanismo() {
        return mecanismo;
    }

    public void setMecanismo(int mecanismo) {
        this.mecanismo = mecanismo;
    }

    public int getTipoFuerza() {
        return tipoFuerza;
    }

    public void setTipoFuerza(int tipoFuerza) {
        this.tipoFuerza = tipoFuerza;
    }

    public int getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(int preparacion) {
        this.preparacion = preparacion;
    }

    public int getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(int ejecucion) {
        this.ejecucion = ejecucion;
    }

    public int getComentarios() {
        return comentarios;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }

    public String getUrlInstrucciones() {
        return urlInstrucciones;
    }

    public void setUrlInstrucciones(String urlInstrucciones) {
        this.urlInstrucciones = urlInstrucciones;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
