package com.jacaranda.myscrum.data.model;

/**
 * Created by Gaby on 03/09/2016.
 */
public class Tarea {
    public static final String TAG = Tarea.class.getSimpleName();
    public static final String TABLE = "Tarea";

    // Labels Table Columns names
    public static final String KEY_idTarea = "idTarea";
    public static final String KEY_Story_idStory = "Story_idStory";
    public static final String KEY_descripcion = "descripcion";
    public static final String KEY_horas = "horas";

    private int idTarea;
    private int Story_idStory;
    private String descripcion;
    private int horas;

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getStory_idStory() {
        return Story_idStory;
    }

    public void setStory_idStory(int story_idStory) {
        Story_idStory = story_idStory;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
