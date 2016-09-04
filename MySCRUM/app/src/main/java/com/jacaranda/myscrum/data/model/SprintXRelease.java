package com.jacaranda.myscrum.data.model;

/**
 * Created by Gaby on 03/09/2016.
 */
public class SprintXRelease {
    public static final String TAG = SprintXRelease.class.getSimpleName();
    public static final String TABLE = "SprintXRelease";

    // Labels Table Columns names
    public static final String KEY_idSprintXRelease = "idSprintXRelease";
    public static final String KEY_ReleaseXProyecto_idRelease = "ReleaseXProyecto_idRelease";
    public static final String KEY_numSprint = "numSprint";

    private int idSprintXRelease;
    private int ReleaseXProyecto_idRelease;
    private int numSprint;

    //Getters & Setters
    public int getIdSprintXRelease() {
        return idSprintXRelease;
    }

    public void setIdSprintXRelease(int idSprintXRelease) {
        this.idSprintXRelease = idSprintXRelease;
    }

    public int getReleaseXProyecto_idRelease() {
        return ReleaseXProyecto_idRelease;
    }

    public void setReleaseXProyecto_idRelease(int releaseXProyecto_idRelease) {
        ReleaseXProyecto_idRelease = releaseXProyecto_idRelease;
    }

    public int getNumSprint() {
        return numSprint;
    }

    public void setNumSprint(int numSprint) {
        this.numSprint = numSprint;
    }
}
