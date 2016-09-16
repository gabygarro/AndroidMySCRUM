package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.Tarea;

import java.util.LinkedList;

/**
 * Created by Gaby on 03/09/2016.
 */
public class TareaRepo {
    private Tarea tarea;

    public TareaRepo(){
        tarea = new Tarea();
    }

    public static String createTable() {
        return "CREATE TABLE " + Tarea.TABLE + " ("
                + Tarea.KEY_idTarea + " INTEGER PRIMARY KEY, "
                + Tarea.KEY_Story_idStory + " INTEGER, "
                + Tarea.KEY_descripcion + " VARCHAR(200), "
                + Tarea.KEY_horas + " INTEGER, "
                + "FOREIGN KEY (Story_idStory) REFERENCES Story (idStory) )";
    }

    public int insert(Tarea tarea) {
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(Tarea.KEY_idTarea, tarea.getIdTarea());
        values.put(Tarea.KEY_Story_idStory, tarea.getStory_idStory());
        values.put(Tarea.KEY_descripcion, tarea.getDescripcion());
        values.put(Tarea.KEY_horas, tarea.getHoras());

        // Inserting Row
        rowID = (int) db.insert(Tarea.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Tarea.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public LinkedList<Tarea> getTareas(int idStory) {
        LinkedList<Tarea> tareas = new LinkedList<>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT * FROM " + Tarea.TABLE + " WHERE " +
                Tarea.KEY_Story_idStory + " = " + idStory ;

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
                Tarea tarea = new Tarea();
                tarea.setStory_idStory(idStory);
                tarea.setIdTarea(cursor.getInt(cursor.getColumnIndex(Tarea.KEY_idTarea)));
                tarea.setDescripcion(cursor.getString(cursor.getColumnIndex(Tarea.KEY_descripcion)));
                tarea.setHoras(cursor.getInt(cursor.getColumnIndex(Tarea.KEY_horas)));
                tareas.add(tarea);
            } while (cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return tareas;
    }
}
