package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.ReleaseXProyecto;
import com.jacaranda.myscrum.data.model.SprintXRelease;

import java.util.LinkedList;

/**
 * Created by Gaby on 03/09/2016.
 */
public class SprintXReleaseRepo {
    private SprintXRelease sprintXRelease;

    public SprintXReleaseRepo(){
        sprintXRelease = new SprintXRelease();
    }

    public static String createTable(){
        return "CREATE TABLE " + SprintXRelease.TABLE + " ("
                + SprintXRelease.KEY_idSprintXRelease + " INTEGER PRIMARY KEY, "
                + SprintXRelease.KEY_ReleaseXProyecto_idRelease + " INTEGER, "
                + SprintXRelease.KEY_numSprint + " INTEGER, "
                + "FOREIGN KEY (ReleaseXProyecto_idRelease) REFERENCES ReleaseXProyecto (idRelease) )";
    }

    public int insert(SprintXRelease sprintXRelease) {
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(SprintXRelease.KEY_idSprintXRelease, sprintXRelease.getIdSprintXRelease());
        values.put(SprintXRelease.KEY_ReleaseXProyecto_idRelease, sprintXRelease.getReleaseXProyecto_idRelease());
        values.put(SprintXRelease.KEY_numSprint, sprintXRelease.getNumSprint());

        // Inserting Row
        rowID = (int) db.insert(SprintXRelease.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(SprintXRelease.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public LinkedList<SprintXRelease> getSprints(int idProyecto){
        LinkedList<SprintXRelease> sprints = new LinkedList<>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT * FROM " + SprintXRelease.TABLE + ", " + ReleaseXProyecto.TABLE +
                " WHERE " + ReleaseXProyecto.KEY_Proyecto_idProyecto + " = " + idProyecto +
                " AND " + ReleaseXProyecto.KEY_idRelease + " = " + SprintXRelease.KEY_ReleaseXProyecto_idRelease;

        Log.d("db", selectQuery);

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (Exception e) {
            Log.d("main", e.toString());
        }

        //Loop through cursor
        if (cursor.moveToFirst()) {
            do {
                SprintXRelease sprintXRelease = new SprintXRelease();
                sprintXRelease.setReleaseXProyecto_idRelease(cursor.getInt(cursor.getColumnIndex(SprintXRelease.KEY_ReleaseXProyecto_idRelease)));
                sprintXRelease.setIdSprintXRelease(cursor.getInt(cursor.getColumnIndex(SprintXRelease.KEY_idSprintXRelease)));
                sprintXRelease.setNumSprint(cursor.getInt(cursor.getColumnIndex(SprintXRelease.KEY_numSprint)));
                sprints.add(sprintXRelease);
            } while (cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return sprints;
    }
}
