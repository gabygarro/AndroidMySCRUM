package com.jacaranda.myscrum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Proyecto;
import com.jacaranda.myscrum.productowner.POProjectActivity;
import com.jacaranda.myscrum.productowner.ProductOwnerActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Gaby on 04/09/2016.
 */
public class ProjectFragment extends Fragment {
    private Global global;
    private LinkedList<Proyecto> proyectos;
    private ProjectAdapter mProjectAdapter;
    private ListView listView;
    private Context context;
    private Class<?> goal;

    public ProjectFragment(){}

    public void sendList(LinkedList<Proyecto> proyectos){
        this.proyectos = proyectos;
        //mProjectAdapter = new ProjectAdapter(getActivity(), proyectos);
    }

    public void sendContext(Context context, Class<?> goal) {
        this.context = context;
        this.goal = goal;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mProjectAdapter = new ProjectAdapter(getActivity(), proyectos);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        listView = (ListView) rootView.findViewById(R.id.listview_project);
        listView.setAdapter(mProjectAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                int idProyecto = view.getId();
                Log.d("main", "Id proyecto = " + idProyecto);
                if(context != null || goal != null) {
                    Intent myIntent = new Intent(context, goal);
                    myIntent.putExtra("idProyecto", idProyecto);
                    startActivity(myIntent);
                }
            }
        });

        return rootView;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("main", "ProjectFragment.onCreate");
        super.onCreate(savedInstanceState);
        global = new Global();
    }

    public ListView getListView(){
        return listView;
    }

    public class ProjectAdapter extends ArrayAdapter<Proyecto> {
        public ProjectAdapter(Context context, LinkedList<Proyecto> proyectos) {
            super(context, 0, proyectos);
            Log.d("main", "Tamaño de lista de proyectos: " + proyectos.size());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Proyecto proyecto = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_project, parent, false);
            }
            // Lookup view for data population
            TextView nombreProyecto = (TextView) convertView.findViewById(R.id.nombreProyecto);
            TextView descripcionProyecto = (TextView) convertView.findViewById(R.id.descripcionProyecto);
            // Populate the data into the template view using the data object
            nombreProyecto.setText(proyecto.getNombre());
            //Log.d("main", "Agregado proyecto " + proyecto.getNombre());
            descripcionProyecto.setText(proyecto.getDescripcion());
            convertView.setId(proyecto.getIdProyecto());
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
