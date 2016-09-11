package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.Usuario;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Gaby on 03/09/2016.
 */
public class UsuarioRepo {
    private Usuario usuario;

    public UsuarioRepo(){
        usuario = new Usuario();
    }

    public static String createTable(){
        return "CREATE TABLE " + Usuario.TABLE + " ("
                + Usuario.KEY_idUsuario + " INTEGER PRIMARY KEY, "
                + Usuario.KEY_correo + " VARCHAR(100), "
                + Usuario.KEY_contrasena + " VARCHAR(45), "
                + Usuario.KEY_rol + " VARCHAR(20) )";
    }

    public int insert(Usuario usuario) {
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //values.put(Usuario.KEY_idUsuario, usuario.getIdUsuario());
        values.put(Usuario.KEY_correo, usuario.getCorreo());
        values.put(Usuario.KEY_contrasena, usuario.getContrasena());
        values.put(Usuario.KEY_rol, usuario.getRol());

        // Inserting Row
        rowID = (int) db.insert(Usuario.TABLE, null, values);
        Log.d("db", "Inserted user = " + usuario.getCorreo() + ", rol = " + usuario.getRol()
                + ", id = " + rowID);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Usuario.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public LinkedList<Usuario> getUsuarios(){
        return getUsuariosQuery("SELECT * FROM " + Usuario.TABLE);
    }

    public LinkedList<Usuario> getUsuariosMinusSYSADMIN(){
        return getUsuariosQuery("SELECT * FROM " + Usuario.TABLE + " WHERE " +
                Usuario.KEY_rol + " != \"SYSADMIN\"" );
    }
    private LinkedList<Usuario> getUsuariosQuery(String query){
        LinkedList<Usuario> listaUsuarios = new LinkedList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = query;
        Log.d("db", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Loop through cursor
        if (cursor.moveToFirst()){
            do{
                //Log.d("db", cursor.getString( cursor.getColumnIndex(Usuario.KEY_correo)));
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(cursor.getInt( cursor.getColumnIndex(Usuario.KEY_idUsuario)));
                usuario.setCorreo(cursor.getString( cursor.getColumnIndex(Usuario.KEY_correo)));
                usuario.setContrasena(cursor.getString( cursor.getColumnIndex(Usuario.KEY_contrasena)));
                usuario.setRol(cursor.getString( cursor.getColumnIndex(Usuario.KEY_rol)));
                listaUsuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return listaUsuarios;
    }

    public Usuario getUsuario(String email, String password) {
        LinkedList<Usuario> listaUsuarios = getUsuarios();
        ListIterator<Usuario> listIterator = listaUsuarios.listIterator();
        while(listIterator.hasNext()) {
            Usuario usuario = listIterator.next();
            if (usuario.getCorreo().equals(email)) {
                //accountType = usuario.getRol();
                //return usuario.getContrasena().equals(mPassword);
                if(usuario.getContrasena().equals(password)){
                    return usuario;
                }
            }
        }
        return null;
    }
}
