package com.jacaranda.myscrum.sysadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.model.Usuario;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;
import com.jacaranda.myscrum.data.repo.UsuarioRepo;
import com.jacaranda.myscrum.data.repo.UsuarioXProyectoRepo;

import java.util.LinkedList;
import java.util.ListIterator;

public class NewProjectActivity extends AppCompatActivity {
    private Proyecto proyecto;
    private String nombreProyecto;
    private String descripcionProyecto;
    private AutoCompleteTextView mNombreProyecto;
    private AutoCompleteTextView mDescripcionProyecto;
    private LinkedList<Usuario> usuarios;
    private LinkedList<Integer> usuariosSeleccionados;
    private Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        global = new Global();

        // Toolbar operations
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        // Set checkboxes
        usuariosSeleccionados = new LinkedList<>();
        UsuarioRepo usuarioRepo = new UsuarioRepo();
        usuarios = usuarioRepo.getUsuarios();
        LinearLayout miembros = (LinearLayout) findViewById(R.id.miembros);
        ListIterator<Usuario> listIterator = usuarios.listIterator();
        while(listIterator.hasNext()) {
            Usuario usuario = listIterator.next();
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setText(usuario.getRol() + " - " + usuario.getCorreo());
            checkBox.setId(usuario.getIdUsuario());
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int id = checkBox.getId();
                    if(isChecked) {
                        Log.d("main", "Checked member id = " + id);
                        usuariosSeleccionados.add((Integer) id);
                    }
                    else if (!isChecked) {
                        Log.d("main", "Unchecked member id = " + checkBox.getId());
                        // Remove the first appearance of the id Integer, not the index
                        usuariosSeleccionados.remove(new Integer(id));
                    }
                }
            });
            miembros.addView(checkBox);
        }

        Button botonGuardar = (Button) findViewById(R.id.boton_guardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProyectoRepo proyectoRepo = new ProyectoRepo();
                // Obtener los campos desde la interfaz aquí y guardarlos en un objeto proyecto
                getCamposTexto();
                // Crear objeto proyecto
                proyecto = new Proyecto();
                proyecto.setNombre(nombreProyecto);
                proyecto.setDescripcion(descripcionProyecto);
                proyecto.setDuracionSprint(8);
                // Correr proyectoRepo para crear esa línea en la bd
                int idProyecto = proyectoRepo.insert(proyecto);
                // Asignar usuarios seleccionados al proyecto
                UsuarioXProyectoRepo usuarioXProyectoRepo = new UsuarioXProyectoRepo();
                usuarioXProyectoRepo.insertLista(usuariosSeleccionados, idProyecto);
                // Devolver a ver los proyectos
                Intent myIntent = new Intent(NewProjectActivity.this,SysAdminActivity.class);
                startActivity(myIntent);

            }
        });

    }

    private void getCamposTexto() {
        // Obtener los objetos de texto
        mNombreProyecto = (AutoCompleteTextView) findViewById(R.id.proyecto_nombre);
        mDescripcionProyecto = (AutoCompleteTextView) findViewById(R.id.proyecto_descripcion);

        // Obtener el texto de los campos al momento de guardar
        nombreProyecto = mNombreProyecto.getText().toString();
        descripcionProyecto = mDescripcionProyecto.getText().toString();
    }

}
