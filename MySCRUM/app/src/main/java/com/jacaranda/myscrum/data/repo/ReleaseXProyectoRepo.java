package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.ReleaseXProyecto;

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
        values.put(ReleaseXProyecto.KEY_idRelease, releaseXProyecto.getIdRelease());
        values.put(ReleaseXProyecto.KEY_numRelease, releaseXProyecto.getNumRelease());
        values.put(ReleaseXProyecto.KEY_Proyecto_idProyecto, releaseXProyecto.getProyecto_idProyecto());
        values.put(ReleaseXProyecto.KEY_descripcion, releaseXProyecto.getDescripcion());

        // Inserting Row
        rowID = (int) db.insert(ReleaseXProyecto.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(ReleaseXProyecto.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
