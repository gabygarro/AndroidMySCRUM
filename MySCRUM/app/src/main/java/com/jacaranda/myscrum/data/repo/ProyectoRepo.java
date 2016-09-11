package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.model.Usuario;
import com.jacaranda.myscrum.data.model.UsuarioXProyecto;

import java.util.LinkedList;

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
        //values.put(Proyecto.KEY_idProyecto, proyecto.getIdProyecto());
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

    public LinkedList<Proyecto> getProyectos(String correo, String accountType) {
        LinkedList<Proyecto> proyectos = new LinkedList<>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "";
        if (accountType.equals("SYSADMIN")) {
            selectQuery = "SELECT * FROM " + Proyecto.TABLE;
        } else {
            /*selectQuery = "SELECT " + Proyecto.KEY_idProyecto + ", " + Proyecto.KEY_nombre + ", "
                    + ", " + Proyecto.KEY_descripcion + ", " + Proyecto.KEY_duracionSprint
                    + " FROM " + Proyecto.TABLE + ", " + Usuario.TABLE + ", " + UsuarioXProyecto.TABLE
                    + " WHERE " + Usuario.TABLE
            ;*/
            selectQuery = "SELECT idProyecto, nombre, descripcion, duracionSprint " +
                    "FROM Proyecto, Usuario, UsuarioXProyecto " +
                    "WHERE Usuario.correo = \"" + correo +
                    "\" AND Usuario.idUsuario = UsuarioXProyecto.Usuario_idUsuario" +
                    " AND UsuarioXProyecto.Proyecto_idProyecto = Proyecto.idProyecto";
        }

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
                Proyecto proyecto = new Proyecto();
                proyecto.setIdProyecto(cursor.getInt(cursor.getColumnIndex(Proyecto.KEY_idProyecto)));
                proyecto.setNombre(cursor.getString(cursor.getColumnIndex(Proyecto.KEY_nombre)));
                proyecto.setDescripcion(cursor.getString(cursor.getColumnIndex(Proyecto.KEY_descripcion)));
                proyecto.setDuracionSprint(cursor.getInt(cursor.getColumnIndex(Proyecto.KEY_duracionSprint)));
                proyectos.add(proyecto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return proyectos;
    }
}
