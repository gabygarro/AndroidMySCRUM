package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.ReleaseXProyecto;

import java.util.LinkedList;

/**
 * Created by Gaby on 03/09/2016.
 */
public class ReleaseXProyectoRepo {
    private ReleaseXProyecto releaseXProyecto;

    public ReleaseXProyectoRepo(){
        releaseXProyecto = new ReleaseXProyecto();
    }

    public static String createTable() {
        return "CREATE TABLE " + ReleaseXProyecto.TABLE + " ("
                + ReleaseXProyecto.KEY_idRelease + " INTEGER PRIMARY KEY, "
                + ReleaseXProyecto.KEY_numRelease + " INTEGER, "
                + ReleaseXProyecto.KEY_Proyecto_idProyecto + " INTEGER, "
                + ReleaseXProyecto.KEY_descripcion + " VARCHAR(100), "
                + "FOREIGN KEY (Proyecto_idProyecto) REFERENCES Proyecto (idProyecto) )";
    }

    public int insert(ReleaseXProyecto releaseXProyecto) {
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(ReleaseXProyecto.KEY_idRelease, releaseXProyecto.getIdRelease());
        values.put(ReleaseXProyecto.KEY_numRelease, releaseXProyecto.getNumRelease());
        values.put(ReleaseXProyecto.KEY_Proyecto_idProyecto, releaseXProyecto.getProyecto_idProyecto());
        values.put(ReleaseXProyecto.KEY_descripcion, releaseXProyecto.getDescripcion());

        // Inserting Row
        rowID = (int) db.insert(ReleaseXProyecto.TABLE, null, values);

        Log.d("db", "Inserted release id = " + rowID + " num = " + releaseXProyecto.getNumRelease()
                + " project = " + releaseXProyecto.getProyecto_idProyecto());
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(ReleaseXProyecto.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public LinkedList<ReleaseXProyecto> getReleases(int idProyecto) {
        LinkedList<ReleaseXProyecto> releases = new LinkedList<>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT * FROM " + ReleaseXProyecto.TABLE + " WHERE " +
                ReleaseXProyecto.KEY_Proyecto_idProyecto + " = " + idProyecto;
                //" ORDER BY " + ReleaseXProyecto.KEY_idRelease + ", " + ReleaseXProyecto.KEY_numRelease;

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
                ReleaseXProyecto releaseXProyecto = new ReleaseXProyecto();
                releaseXProyecto.setProyecto_idProyecto(idProyecto);
                releaseXProyecto.setNumRelease(cursor.getInt(cursor.getColumnIndex(releaseXProyecto.KEY_numRelease)));
                releaseXProyecto.setDescripcion(cursor.getString(cursor.getColumnIndex(releaseXProyecto.KEY_descripcion)));
                releaseXProyecto.setIdRelease(cursor.getInt(cursor.getColumnIndex(releaseXProyecto.KEY_idRelease)));
                releases.add(releaseXProyecto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return releases;
    }

    public int getIdRelease(int idProyecto, int numRelease) {
        int idRelease = 1;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT * FROM " + ReleaseXProyecto.TABLE + " WHERE " +
                ReleaseXProyecto.KEY_Proyecto_idProyecto + " = " + idProyecto +
                " AND " + ReleaseXProyecto.KEY_numRelease + " = " + numRelease;
        //" ORDER BY " + ReleaseXProyecto.KEY_idRelease + ", " + ReleaseXProyecto.KEY_numRelease;

        Log.d("db", selectQuery);

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (Exception e) {
            Log.d("main", e.toString());
        }

        //Loop through cursor
        if (cursor.moveToFirst()) {
            idRelease = cursor.getInt(cursor.getColumnIndex(releaseXProyecto.KEY_idRelease));
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return idRelease;
    }

    public int getNumRelease(int idRelease){
        int numRelease = 1;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT " + ReleaseXProyecto.KEY_numRelease + " FROM " + ReleaseXProyecto.TABLE +
                " WHERE " + ReleaseXProyecto.KEY_idRelease + " = " + idRelease;

        Log.d("db", selectQuery);

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (Exception e) {
            Log.d("main", e.toString());
        }

        //Loop through cursor
        if (cursor.moveToFirst()) {
            numRelease = cursor.getInt(cursor.getColumnIndex(releaseXProyecto.KEY_numRelease));
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return numRelease;
    }
}
