package com.jacaranda.myscrum.sysadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;

public class NewProjectActivity extends AppCompatActivity {
    private Proyecto proyecto;
    private String nombreProyecto;
    private String descripcionProyecto;
    private AutoCompleteTextView mNombreProyecto;
    private AutoCompleteTextView mDescripcionProyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        // Toolbar operations
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

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
                proyectoRepo.insert(proyecto);
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
