package com.jacaranda.myscrum.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jacaranda.myscrum.app.App;
import com.jacaranda.myscrum.data.model.*;
import com.jacaranda.myscrum.data.repo.*;

/**
 * Created by Gaby on 03/09/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MyScrumDB";
    private static final String TAG = DBHelper.class.getSimpleName().toString();

    public DBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will be created here
        db.execSQL(ProyectoRepo.createTable());
        db.execSQL(UsuarioRepo.createTable());
        db.execSQL(UsuarioXProyectoRepo.createTable());
        db.execSQL(StoryRepo.createTable());
        db.execSQL(ReleaseXProyectoRepo.createTable());
        db.execSQL(SprintXReleaseRepo.createTable());
        db.execSQL(InStoryXSprintRepo.createTable());
        db.execSQL(OutStoryXSprintRepo.createTable());
        db.execSQL(TareaRepo.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Tarea.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OutStoryXSprint.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + InStoryXSprint.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OutStoryXSprint.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SprintXRelease.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ReleaseXProyecto.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Story.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + UsuarioXProyecto.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Usuario.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Proyecto.TABLE);
        onCreate(db);
    }
}
