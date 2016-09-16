package com.jacaranda.myscrum.scrummaster;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.model.ReleaseXProyecto;
import com.jacaranda.myscrum.data.model.SprintXRelease;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;
import com.jacaranda.myscrum.data.repo.ReleaseXProyectoRepo;
import com.jacaranda.myscrum.data.repo.SprintXReleaseRepo;

import java.util.LinkedList;
import java.util.ListIterator;

public class SMProjectActivity extends AppCompatActivity implements View.OnClickListener {
    private Global global;
    private Proyecto proyecto;
    private int idProyecto;
    private TextView mNombreProyecto;
    private TextView mDescripcionProyecto;
    // Interface features
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm_project);
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

        // Display sprint information
        SprintXReleaseRepo sprintXReleaseRepo = new SprintXReleaseRepo();
        ReleaseXProyectoRepo releaseXProyectoRepo = new ReleaseXProyectoRepo();
        LinkedList<SprintXRelease> sprints = sprintXReleaseRepo.getSprints(idProyecto);
        LinearLayout storiesLayout = (LinearLayout) findViewById(R.id.sprints_layout);
        ListIterator<SprintXRelease> listIterator = sprints.listIterator();
        while(listIterator.hasNext()) {
            SprintXRelease sprintXRelease = listIterator.next();
            final TextView textView = new TextView(this);
            int numRelease = releaseXProyectoRepo.getNumRelease(sprintXRelease.getReleaseXProyecto_idRelease());
            textView.setText("Release " + numRelease + " - Sprint " + sprintXRelease.getNumSprint());
            textView.setId(sprintXRelease.getIdSprintXRelease());
            storiesLayout.addView(textView);
        }

        // Get fab's objects
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        // Get animations
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        // Set listeners on fabs
        fab.setOnClickListener(this);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SMProjectActivity.this,NewSprintActivity.class);
                myIntent.putExtra("idProyecto", idProyecto);
                startActivity(myIntent);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SMProjectActivity.this,InOutUserStoryActivity.class);
                myIntent.putExtra("idProyecto", idProyecto);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab1:
                Log.d("main", "Fab 1");
                break;
            case R.id.fab2:
                Log.d("main", "Fab 2");
                break;
        }
    }

    public void animateFAB(){
        if(isFabOpen){
            fab.startAnimation(rotate_backward);
            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;

        } else {
            fab.startAnimation(rotate_forward);
            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorGray)));
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

}
