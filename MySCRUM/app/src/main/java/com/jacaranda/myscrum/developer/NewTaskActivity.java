package com.jacaranda.myscrum.developer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Story;
import com.jacaranda.myscrum.data.model.Tarea;
import com.jacaranda.myscrum.data.repo.SprintXReleaseRepo;
import com.jacaranda.myscrum.data.repo.StoryRepo;
import com.jacaranda.myscrum.data.repo.TareaRepo;

import java.util.LinkedList;
import java.util.ListIterator;

public class NewTaskActivity extends AppCompatActivity {
    private Global global;
    private int idProyecto;
    private LinkedList<Story> stories;
    private Spinner mStory;
    private AutoCompleteTextView mTarea;
    private AutoCompleteTextView mHoras;
    private int idStory;
    private String nombreTarea;
    private int horas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        global = new Global();

        //Create toolbar
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

        // Populate spinner
         mStory = (Spinner) findViewById(R.id.user_story);
        StoryRepo storyRepo = new StoryRepo();
        stories = storyRepo.getStories(idProyecto);
        String[] listaStories = new String[stories.size()];
        ListIterator<Story> listIterator = stories.listIterator();
        int i = 0;
        while(listIterator.hasNext()) {
            Story story = listIterator.next();
            listaStories[i]  = story.getPrioridad() + " - " + story.getTexto();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaStories);
        mStory.setAdapter(adapter);


        Button botonGuardar = (Button) findViewById(R.id.boton_guardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TareaRepo tareaRepo = new TareaRepo();
                Tarea tarea = new Tarea();
                // Obtener los campos desde la interfaz aquí y guardarlos en un objeto proyecto
                getCamposTexto();
                // Get idRelease
                tarea.setStory_idStory(idStory);
                tarea.setDescripcion(nombreTarea);
                tarea.setHoras(horas);
                tareaRepo.insert(tarea);

                // Devolver a ver los proyectos
                Intent myIntent = new Intent(NewTaskActivity.this,DProjectActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void getCamposTexto() {
        // Obtener los objetos de texto
        mTarea = (AutoCompleteTextView) findViewById(R.id.tarea);
        mHoras = (AutoCompleteTextView) findViewById(R.id.horas);

        // Obtener el texto de los campos al momento de guardar
        nombreTarea = mTarea.getText().toString();
        horas = Integer.parseInt(mHoras.getText().toString());
        //idStory = Integer.parseInt(mStory.getSelectedItem().toString());
        int storyIndex = mStory.getSelectedItemPosition();
        idStory = stories.get(storyIndex).getIdStory();
    }

}
