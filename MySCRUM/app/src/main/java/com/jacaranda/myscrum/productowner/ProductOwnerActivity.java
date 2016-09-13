package com.jacaranda.myscrum.productowner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.ProjectFragment;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;

import java.util.LinkedList;

public class ProductOwnerActivity extends AppCompatActivity {
    private Global global;
    LinkedList<Proyecto> proyectos;
    ProjectFragment projectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("main", "ProductOwnerActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_owner);
        global = new Global();

        // Create toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // Get projects associated to user
            ProyectoRepo proyectoRepo = new ProyectoRepo();
            proyectos = proyectoRepo.getProyectos(global.email, global.accountType);
            projectFragment = new ProjectFragment();
            projectFragment.sendContext(ProductOwnerActivity.this, POProjectActivity.class);
            projectFragment.sendList(proyectos);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerPO, projectFragment)
                    .commit();
        }
    }

}
