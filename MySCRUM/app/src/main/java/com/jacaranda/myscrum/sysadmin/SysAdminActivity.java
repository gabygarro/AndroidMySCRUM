package com.jacaranda.myscrum.sysadmin;

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

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.ProjectFragment;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;

import java.util.LinkedList;

public class SysAdminActivity extends AppCompatActivity implements View.OnClickListener {
    private Global global;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2, fab3;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

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
        global = new Global();
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
        // Get fab's objects
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        // Get animations
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        // Set listeners on fabs
        fab.setOnClickListener(this);
        //fab1.setOnClickListener(this);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(SysAdminActivity.this,NewProjectActivity.class);
                startActivity(myIntent);
            }
        });
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);


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
            case R.id.fab3:
                Log.d("main", "Fab3");
                break;
        }
    }

    public void animateFAB(){
        if(isFabOpen){
            fab.startAnimation(rotate_backward);
            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;
            Log.d("main", "close");

        } else {
            fab.startAnimation(rotate_forward);
            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorGray)));
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;
            Log.d("main","open");
        }
    }
}


