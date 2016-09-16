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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.InStoryXSprint;
import com.jacaranda.myscrum.data.model.SprintXRelease;
import com.jacaranda.myscrum.data.model.Story;
import com.jacaranda.myscrum.data.repo.InStoryXSprintRepo;
import com.jacaranda.myscrum.data.repo.OutStoryXSprintRepo;
import com.jacaranda.myscrum.data.repo.ReleaseXProyectoRepo;
import com.jacaranda.myscrum.data.repo.SprintXReleaseRepo;
import com.jacaranda.myscrum.data.repo.StoryRepo;

import java.util.LinkedList;
import java.util.ListIterator;

public class InOutUserStoryActivity extends AppCompatActivity {
    private Global global;
    private int idProyecto;
    private LinkedList<SprintXRelease> sprints;
    private Spinner mSprintSpinner;
    private int idSprint;
    // Checkboxes attributes
    private LinkedList<Story> stories;
    private LinkedList<Integer> inStoriesId;
    private LinkedList<Integer> outStoriesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out_user_story);
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

        // Populate the spinner
        SprintXReleaseRepo sprintXReleaseRepo = new SprintXReleaseRepo();
        ReleaseXProyectoRepo releaseXProyectoRepo = new ReleaseXProyectoRepo();
        sprints = sprintXReleaseRepo.getSprints(idProyecto);
        mSprintSpinner = (Spinner) findViewById(R.id.sprint_spinner);
        String[] listaSprints = new String[sprints.size()];
        ListIterator<SprintXRelease> listIterator = sprints.listIterator();
        int i = 0;
        while(listIterator.hasNext()) {
            SprintXRelease sprintXRelease = new SprintXRelease();
            sprintXRelease = listIterator.next();
            int numRelease = releaseXProyectoRepo.getNumRelease(sprintXRelease.getReleaseXProyecto_idRelease());
            Log.d("main", "Num release = " + numRelease);
            listaSprints[i]  = numRelease + " - " + sprintXRelease.getNumSprint();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaSprints);
        mSprintSpinner.setAdapter(adapter);

        //Set checkboxes
        inStoriesId = new LinkedList<>();
        outStoriesId = new LinkedList<>();
        StoryRepo storyRepo = new StoryRepo();
        stories = storyRepo.getStories(idProyecto);
        LinearLayout mInStories = (LinearLayout) findViewById(R.id.in_stories);
        LinearLayout mOutStories = (LinearLayout) findViewById(R.id.out_stories);
        ListIterator<Story> listIterator2 = stories.listIterator();
        while(listIterator2.hasNext()) {
            Story story = listIterator2.next();
            final CheckBox checkBoxIn = new CheckBox(this);
            final CheckBox checkBoxOut = new CheckBox(this);
            String checkBoxText = "Prioridad " + story.getPrioridad() + " - " + story.getTexto();
            checkBoxIn.setText(checkBoxText);
            checkBoxIn.setId(story.getIdStory());
            checkBoxIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int id = checkBoxIn.getId();
                    if (isChecked) {
                        Log.d("main", "Checked inStory id = " + id);
                        inStoriesId.add((Integer) id);
                    } else if (!isChecked) {
                        Log.d("main", "Unchecked inStory id = " + id);
                        // Remove the first appearance of the id Integer, not the index
                        inStoriesId.remove(new Integer(id));
                    }
                }
            });
            mInStories.addView(checkBoxIn);
            checkBoxOut.setText(checkBoxText);
            checkBoxOut.setId(story.getIdStory());
            checkBoxOut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int id = checkBoxOut.getId();
                    if (isChecked) {
                        Log.d("main", "Checked inStory id = " + id);
                        outStoriesId.add((Integer) id);
                    } else if (!isChecked) {
                        Log.d("main", "Unchecked member id = " + id);
                        // Remove the first appearance of the id Integer, not the index
                        outStoriesId.remove(new Integer(id));
                    }
                }
            });
            mOutStories.addView(checkBoxOut);
        }

        Button botonGuardar = (Button) findViewById(R.id.boton_guardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SprintXReleaseRepo sprintXReleaseRepo = new SprintXReleaseRepo();
                // Obtener los campos desde la interfaz aquí y guardarlos en un objeto proyecto
                getCamposTexto();
                // Insert in and out stories
                InStoryXSprintRepo inStoryXSprintRepo = new InStoryXSprintRepo();
                OutStoryXSprintRepo outStoryXSprintRepo = new OutStoryXSprintRepo();
                inStoryXSprintRepo.insertLista(inStoriesId, idSprint);
                outStoryXSprintRepo.insertLista(outStoriesId, idSprint);

                // Devolver a ver los proyectos
                Intent myIntent = new Intent(InOutUserStoryActivity.this,SMProjectActivity.class);
                startActivity(myIntent);
            }
        });


    }

    private void getCamposTexto() {
        // Obtener los objetos de texto
        //mRelease = (Spinner) findViewById(R.id.release);
        //mNumSprints = (EditText) findViewById(R.id.numSprints);

        // Obtener el texto de los campos al momento de guardar
        //numSprints = Integer.parseInt(.getText().toString());
        String sprintSeleccionado = mSprintSpinner.getSelectedItem().toString();
        String[] pieces = sprintSeleccionado.split(" - ");
        SprintXReleaseRepo sprintXReleaseRepo = new SprintXReleaseRepo();
        idSprint = sprintXReleaseRepo.getIdSprint(idProyecto, Integer.parseInt(pieces[1]), Integer.parseInt(pieces[0]));
    }

}
