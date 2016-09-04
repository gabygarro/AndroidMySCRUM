package com.jacaranda.myscrum.data.model;

/**
 * Created by Gaby on 03/09/2016.
 */
public class ReleaseXProyecto {
    public static final String TAG = ReleaseXProyecto.class.getSimpleName();
    public static final String TABLE = "ReleaseXProyecto";

    // Labels Table Columns names
    public static final String KEY_idRelease = "idRelease";
    public static final String KEY_numRelease = "numRelease";
    public static final String KEY_Proyecto_idProyecto = "Proyecto_idProyecto";
    public static final String KEY_descripcion = "descripcion";

    private int idRelease;
    private int numRelease;
    private int Proyecto_idProyecto;
    private String descripcion;

    // Setters & Getters

    public int getIdRelease() {
        return idRelease;
    }

    public void setIdRelease(int idRelease) {
        this.idRelease = idRelease;
    }

    public int getNumRelease() {
        return numRelease;
    }

    public void setNumRelease(int numRelease) {
        this.numRelease = numRelease;
    }

    public int getProyecto_idProyecto() {
        return Proyecto_idProyecto;
    }

    public void setProyecto_idProyecto(int proyecto_idProyecto) {
        Proyecto_idProyecto = proyecto_idProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
