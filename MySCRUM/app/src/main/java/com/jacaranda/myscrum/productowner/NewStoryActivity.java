package com.jacaranda.myscrum.productowner;

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
import android.widget.EditText;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Story;
import com.jacaranda.myscrum.data.repo.StoryRepo;

public class NewStoryActivity extends AppCompatActivity {
    private Global global;
    private int idProyecto;
    private Story story;
    private String texto;
    private int prioridad;
    private AutoCompleteTextView mTexto;
    private EditText mPrioridad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);
        global = new Global();

        // Create toolbar
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

        Button botonGuardar = (Button) findViewById(R.id.boton_guardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoryRepo storyRepo = new StoryRepo();
                getCamposTexto();
                // Crear objeto Story
                story = new Story();
                story.setProyecto_idProyecto(idProyecto);
                story.setTexto(texto);
                story.setPrioridad(prioridad);
                // Correr storyRepo para crear esa línea en la bd
                int idStory = storyRepo.insert(story);
                Log.d("db", "Created story id = " + idStory + " on project = " + idProyecto);
                // Devolver a ver los proyectos
                Intent myIntent = new Intent(NewStoryActivity.this, POProjectActivity.class);
                myIntent.putExtra("idProyecto", idProyecto);
                startActivity(myIntent);
            }
        });
    }

    private void getCamposTexto() {
        // Obtener los objetos de texto
        mTexto = (AutoCompleteTextView) findViewById(R.id.texto_story);
        mPrioridad = (EditText) findViewById(R.id.prioridad_story);

        // Obtener el texto de los campos al momento de guardar
        texto = mTexto.getText().toString();
        prioridad = Integer.parseInt(mPrioridad.getText().toString());
    }

}
