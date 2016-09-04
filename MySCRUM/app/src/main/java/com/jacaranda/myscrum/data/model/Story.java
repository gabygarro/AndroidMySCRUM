package com.jacaranda.myscrum.data.model;

/**
 * Created by Gaby on 03/09/2016.
 */
public class Story {
    public static final String TAG = Story.class.getSimpleName();
    public static final String TABLE = "Story";

    // Labels Table Columns names
    public static final String KEY_idStory = "idStory";
    public static final String KEY_Proyecto_idProyecto = "Proyecto_idProyecto";
    public static final String KEY_texto = "texto";
    public static final String KEY_prioridad = "prioridad";

    private int idStory;
    private int Proyecto_idProyecto;
    private String texto;
    private int prioridad;

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public int getProyecto_idProyecto() {
        return Proyecto_idProyecto;
    }

    public void setProyecto_idProyecto(int proyecto_idProyecto) {
        Proyecto_idProyecto = proyecto_idProyecto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
