package com.jacaranda.myscrum.developer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.model.SprintXRelease;
import com.jacaranda.myscrum.data.model.Story;
import com.jacaranda.myscrum.data.model.Tarea;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;
import com.jacaranda.myscrum.data.repo.ReleaseXProyectoRepo;
import com.jacaranda.myscrum.data.repo.SprintXReleaseRepo;
import com.jacaranda.myscrum.data.repo.StoryRepo;
import com.jacaranda.myscrum.data.repo.TareaRepo;

import java.util.LinkedList;
import java.util.ListIterator;

public class DProjectActivity extends AppCompatActivity {
    private Global global;
    private Proyecto proyecto;
    private int idProyecto;
    private TextView mNombreProyecto;
    private TextView mDescripcionProyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_project);
        global = new Global();

        // Create toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Retrieve project information
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idProyecto = extras.getInt("idProyecto");
            global.idProyecto = idProyecto;
        }
        else {
            idProyecto = global.idProyecto;
        }
        ProyectoRepo proyectoRepo = new ProyectoRepo();
        proyecto = proyectoRepo.getProyecto(idProyecto);

        // Send project information
        mNombreProyecto = (TextView) findViewById(R.id.titulo_proyecto);
        mDescripcionProyecto = (TextView) findViewById(R.id.descripcion_proyecto);
        mNombreProyecto.setText(proyecto.getNombre());
        mDescripcionProyecto.setText(proyecto.getDescripcion());

        // Display story information
        StoryRepo storyRepo = new StoryRepo();
        TareaRepo tareaRepo = new TareaRepo();
        LinkedList<Story> stories = storyRepo.getStories(idProyecto);
        LinearLayout storiesLayout = (LinearLayout) findViewById(R.id.tareas_layout);
        ListIterator<Story> listIterator = stories.listIterator();
        while(listIterator.hasNext()) {
            Story story = listIterator.next();
            final TextView textView = new TextView(this);
            textView.setText(story.getPrioridad() + " - " + story.getTexto());
            textView.setId(story.getIdStory());
            textView.setTextSize(14);
            textView.setTextColor(Color.parseColor("#000000"));
            storiesLayout.addView(textView);

            // Display tareas
            LinkedList<Tarea> tareas = tareaRepo.getTareas(story.getIdStory());
            ListIterator<Tarea> listIterator2 = tareas.listIterator();
            while(listIterator2.hasNext()) {
                Tarea tarea = listIterator2.next();
                final TextView textView2 = new TextView(this);
                String textTarea = "\t(" + tarea.getHoras() + " horas) " + tarea.getDescripcion();
                textView2.setText(textTarea);
                textView2.setId(tarea.getIdTarea());
                storiesLayout.addView(textView2);
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(DProjectActivity.this,NewTaskActivity.class);
                myIntent.putExtra("idProyecto", idProyecto);
                startActivity(myIntent);
            }
        });

    }

}
