package com.jacaranda.myscrum;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jacaranda.myscrum.data.ProjectFragment;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.data.repo.ProyectoRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

        // Get parameters from previous activity
        Bundle extras = getIntent().getExtras();
        String email = "";
        String accountType = "";
        if (extras != null) {
            email = extras.getString("EMAIL");
            accountType = extras.getString("ACCOUNTTYPE");
        }

        // Get projects associated to user
        ProyectoRepo proyectoRepo = new ProyectoRepo();
        LinkedList<Proyecto> proyectos = proyectoRepo.getProyectos(email, accountType);

        if (savedInstanceState == null) {
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
                Snackbar.make(view, "Replace with your own text", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    // A placeholder fragment containing a simple view.
    public static class PlaceholderFragment  {


    }
}


