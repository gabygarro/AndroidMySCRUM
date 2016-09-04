package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.Proyecto;

/**
 * Created by Gaby on 03/09/2016.
 */
public class ProyectoRepo {
    private Proyecto proyecto;

    public ProyectoRepo(){
        proyecto = new Proyecto();
    }

    public static String createTable() {
        return "CREATE TABLE " + Proyecto.TABLE + " ("
                + Proyecto.KEY_idProyecto + " INTEGER PRIMARY KEY, "
                + Proyecto.KEY_nombre + " VARCHAR(100), "
                + Proyecto.KEY_descripcion + " VARCHAR(300), "
                + Proyecto.KEY_duracionSprint + " INT )";
                //+ "PRIMARY KEY (idProyecto) )";
    }

    public int insert(Proyecto proyecto) {
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Proyecto.KEY_idProyecto, proyecto.getIdProyecto());
        values.put(Proyecto.KEY_nombre, proyecto.getNombre());
        values.put(Proyecto.KEY_descripcion, proyecto.getDescripcion());
        values.put(Proyecto.KEY_duracionSprint, proyecto.getDuracionSprint());

        // Inserting Row
        rowID = (int) db.insert(Proyecto.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Proyecto.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
