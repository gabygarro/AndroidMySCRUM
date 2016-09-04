package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.UsuarioXProyecto;

/**
 * Created by Gaby on 03/09/2016.
 */
public class UsuarioXProyectoRepo {
    private UsuarioXProyecto usuarioXProyecto;

    public UsuarioXProyectoRepo(){
        usuarioXProyecto = new UsuarioXProyecto();
    }

    public static String createTable(){
        return "CREATE TABLE " + UsuarioXProyecto.TABLE + " ("
                + UsuarioXProyecto.KEY_Proyecto_idProyecto + " INTEGER, "
                + UsuarioXProyecto.KEY_Usuario_idUsuario + " INTEGER, "
                + "FOREIGN KEY (Proyecto_idProyecto) REFERENCES Proyecto(idProyecto) "
                + "FOREIGN KEY (Usuario_idUsuario) REFERENCES Usuario (idUsuario) )";
    }

    public int insert(UsuarioXProyecto usuarioXProyecto) {
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(UsuarioXProyecto.KEY_Proyecto_idProyecto, usuarioXProyecto.getProyecto_idProyecto());
        values.put(UsuarioXProyecto.KEY_Usuario_idUsuario, usuarioXProyecto.getUsuario_idUsuario());

        // Inserting Row
        rowID = (int) db.insert(UsuarioXProyecto.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(UsuarioXProyecto.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
