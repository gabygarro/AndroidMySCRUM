package com.jacaranda.myscrum.scrummaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.LoginActivity;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.model.ReleaseXProyecto;
import com.jacaranda.myscrum.data.model.SprintXRelease;
import com.jacaranda.myscrum.data.repo.ReleaseXProyectoRepo;
import com.jacaranda.myscrum.data.repo.SprintXReleaseRepo;
import com.jacaranda.myscrum.data.repo.UsuarioXProyectoRepo;
import com.jacaranda.myscrum.sysadmin.NewProjectActivity;

import java.util.LinkedList;
import java.util.ListIterator;

public class NewSprintActivity extends AppCompatActivity {
    private Global global;
    private int idProyecto;
    private LinkedList<ReleaseXProyecto> releases;
    private int idReleaseSeleccionado;
    private Spinner mRelease;
    private EditText mNumSprints;
    private int release;
    private int numSprints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sprint);
        global = new Global();

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        // Retrieve project information
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idProyecto = extras.getInt("idProyecto");
        }

        // Populate the spinner
        ReleaseXProyectoRepo releaseXProyectoRepo = new ReleaseXProyectoRepo();
        releases = releaseXProyectoRepo.getReleases(idProyecto);
        mRelease = (Spinner) findViewById(R.id.release);
        String[] listaReleases = new String[releases.size()];
        ListIterator<ReleaseXProyecto> listIterator = releases.listIterator();
        int i = 0;
        while(listIterator.hasNext()) {
            listaReleases[i]  = "" + listIterator.next().getNumRelease();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaReleases);
        mRelease.setAdapter(adapter);

        Button botonGuardar = (Button) findViewById(R.id.boton_guardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SprintXReleaseRepo sprintXReleaseRepo = new SprintXReleaseRepo();
                // Obtener los campos desde la interfaz aquí y guardarlos en un objeto proyecto
                getCamposTexto();
                // Get idRelease
                ReleaseXProyectoRepo releaseXProyectoRepo = new ReleaseXProyectoRepo();
                idReleaseSeleccionado = releaseXProyectoRepo.getIdRelease(idProyecto, release);
                // Insertar
                for (int i = 1; i <= numSprints; i++) {
                    SprintXRelease sprintXRelease = new SprintXRelease();
                    sprintXRelease.setNumSprint(i);
                    sprintXRelease.setReleaseXProyecto_idRelease(idReleaseSeleccionado);
                    // Crear esa línea en la bd
                    int idSprint = sprintXReleaseRepo.insert(sprintXRelease);
                }

                // Devolver a ver los proyectos
                Intent myIntent = new Intent(NewSprintActivity.this,SMProjectActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void getCamposTexto() {
        // Obtener los objetos de texto
        //mRelease = (Spinner) findViewById(R.id.release);
        mNumSprints = (EditText) findViewById(R.id.numSprints);

        // Obtener el texto de los campos al momento de guardar
        numSprints = Integer.parseInt(mNumSprints.getText().toString());
        release = Integer.parseInt( mRelease.getSelectedItem().toString());
    }

}
