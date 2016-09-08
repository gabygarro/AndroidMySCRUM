package com.jacaranda.myscrum.sysadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.ProjectFragment;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;

import java.util.LinkedList;

public class SysAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("main", "SysAdminActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_admin);

        // Create toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            setSupportActionBar(toolbar);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        // Get parameters from previous activity
        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("EMAIL");
            accountType = extras.getString("ACCOUNTTYPE");
        }*/
        Global global = new Global();
        if (savedInstanceState == null) {
            // Get projects associated to user
            ProyectoRepo proyectoRepo = new ProyectoRepo();
            LinkedList<Proyecto> proyectos = proyectoRepo.getProyectos(global.email, global.accountType);
            ProjectFragment projectFragment = new ProjectFragment();
            projectFragment.sendList(proyectos);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, projectFragment)
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SysAdminActivity.this,NewProjectActivity.class);
                startActivity(myIntent);
            }
        });
    }
}


