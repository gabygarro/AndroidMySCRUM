package com.jacaranda.myscrum.data.model;

/**
 * Created by Gaby on 03/09/2016.
 */
public class Usuario {
    public static final String TAG = Usuario.class.getSimpleName();
    public static final String TABLE = "Usuario";

    // Labels Table Columns names
    public static final String KEY_idUsuario = "idUsuario";
    public static final String KEY_correo = "correo";
    public static final String KEY_contrasena = "contrasena";
    public static final String KEY_rol = "rol";

    private int idUsuario;
    private String correo;
    private String contrasena;
    private String rol;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
