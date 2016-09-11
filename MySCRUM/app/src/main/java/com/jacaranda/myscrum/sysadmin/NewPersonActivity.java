package com.jacaranda.myscrum.sysadmin;

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
import android.widget.Spinner;

import com.jacaranda.myscrum.Global;
import com.jacaranda.myscrum.R;
import com.jacaranda.myscrum.data.model.Usuario;
import com.jacaranda.myscrum.data.repo.UsuarioRepo;

public class NewPersonActivity extends AppCompatActivity {
    private Usuario usuario;
    private String correo;
    private String contrasena;
    private String rol;
    private AutoCompleteTextView mCorreo;
    private AutoCompleteTextView mContrasena;
    private Spinner mRol;

    private Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);
        global = new Global();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        // Populate the spinner
        mRol = (Spinner) findViewById(R.id.usuario_rol);
        String[] roles = new String[]{"SYSADMIN", "PRODUCTOWNER", "SCRUMMASTER", "DEVELOPER"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, roles);
        mRol.setAdapter(adapter);

        Button botonGuardar = (Button) findViewById(R.id.boton_guardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioRepo usuarioRepo = new UsuarioRepo();
                // Obtener los campos desde la interfaz aquí y guardarlos en un objeto proyecto
                getCamposTexto();
                // Crear objeto proyecto
                usuario = new Usuario();
                usuario.setCorreo(correo);
                usuario.setContrasena(contrasena);
                usuario.setRol(rol);
                // Correr usuarioRepo para crear esa línea en la bd
                usuarioRepo.insert(usuario);
                // Devolver a ver los proyectos
                Intent myIntent = new Intent(NewPersonActivity.this,SysAdminActivity.class);
                startActivity(myIntent);
            }
        });
    }
    private void getCamposTexto() {
        // Obtener los objetos de texto
        mCorreo = (AutoCompleteTextView) findViewById(R.id.usuario_correo);
        mContrasena = (AutoCompleteTextView) findViewById(R.id.usuario_contrasena);
        //mRol = (Spinner) findViewById(R.id.usuario_rol);

        // Obtener el texto de los campos al momento de guardar
        correo = mCorreo.getText().toString();
        contrasena = mContrasena.getText().toString();
        rol = mRol.getSelectedItem().toString();
    }

}
