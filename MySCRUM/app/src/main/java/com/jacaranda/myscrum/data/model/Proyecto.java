package com.jacaranda.myscrum.data.model;

/**
 * Created by Gaby on 03/09/2016.
 */
public class Proyecto {
    public static final String TAG = Proyecto.class.getSimpleName();
    public static final String TABLE = "Proyecto";

    // Labels Table Columns names
    public static final String KEY_idProyecto = "idProyecto";
    public static final String KEY_nombre = "nombre";
    public static final String KEY_descripcion = "descripcion";
    public static final String KEY_duracionSprint = "duracionSprint"; // Mal nombre, debería de ser numReleases

    private int idProyecto;
    private String nombre;
    private String descripcion;
    private int duracionSprint;

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionSprint() {
        return duracionSprint;
    }

    public void setDuracionSprint(int duracionSprint) {
        this.duracionSprint = duracionSprint;
    }
}
