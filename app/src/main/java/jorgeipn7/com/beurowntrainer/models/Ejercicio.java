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
    private String nombre;
    private boolean favorito;
    //Clasificación
    private String utilidad;
    private String mecanismo;
    private String tipoFuerza;
    //Instrucciones
    private String preparacion;
    private String ejecucion;
    private String comentarios;
    private String urlInstrucciones;



    public Ejercicio() {
    }

    public Ejercicio(int foto, String nombre, String utilidad, String mecanismo, String tipoFuerza, String preparacion, String ejecucion, String comentarios, String urlInstrucciones) {
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

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(String ejecucion) {
        this.ejecucion = ejecucion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
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
