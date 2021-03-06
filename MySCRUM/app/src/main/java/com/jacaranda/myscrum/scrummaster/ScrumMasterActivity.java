package com.jacaranda.myscrum.scrummaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.ProjectFragment;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;

import java.util.LinkedList;

public class ScrumMasterActivity extends AppCompatActivity {
    private Global global;
    LinkedList<Proyecto> proyectos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("main", "ScrumMasterActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrum_master);
        global = new Global();

        //Create toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // Get projects associated to user
            ProyectoRepo proyectoRepo = new ProyectoRepo();
            proyectos = proyectoRepo.getProyectos(global.email, global.accountType);
            ProjectFragment projectFragment = new ProjectFragment();
            projectFragment.sendContext(ScrumMasterActivity.this, SMProjectActivity.class);
            projectFragment.sendList(proyectos);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerSM, projectFragment)
                    .commit();
        }
    }

}
