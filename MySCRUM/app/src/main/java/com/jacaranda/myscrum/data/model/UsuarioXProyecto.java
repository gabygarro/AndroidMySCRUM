package com.jacaranda.myscrum.data.model;

/**
 * Created by Gaby on 03/09/2016.
 */
public class UsuarioXProyecto {
    public static final String TAG = UsuarioXProyecto.class.getSimpleName();
    public static final String TABLE = "UsuarioXProyecto";

    // Labels Table Columns names
    public static final String KEY_Proyecto_idProyecto = "Proyecto_idProyecto";
    public static final String KEY_Usuario_idUsuario = "Usuario_idUsuario";

    private int Proyecto_idProyecto;
    private int Usuario_idUsuario;

    public int getProyecto_idProyecto() {
        return Proyecto_idProyecto;
    }

    public void setProyecto_idProyecto(int proyecto_idProyecto) {
        Proyecto_idProyecto = proyecto_idProyecto;
    }

    public int getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(int usuario_idUsuario) {
        Usuario_idUsuario = usuario_idUsuario;
    }
}
