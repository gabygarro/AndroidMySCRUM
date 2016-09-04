package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.Story;

/**
 * Created by Gaby on 03/09/2016.
 */
public class StoryRepo {
    private Story story;

    public StoryRepo(){
        story = new Story();
    }

    public static String createTable() {
        return "CREATE TABLE " + Story.TABLE + " ("
                + Story.KEY_idStory + " INTEGER PRIMARY KEY, "
                + Story.KEY_Proyecto_idProyecto + " INTEGER, "
                + Story.KEY_texto + " VARCHAR(300), "
                + Story.KEY_prioridad + " INTEGER, "
                + "FOREIGN KEY (Proyecto_idProyecto) REFERENCES Proyecto (idProyecto) )";
    }

    public int insert(Story story) {
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Story.KEY_idStory, story.getIdStory());
        values.put(Story.KEY_Proyecto_idProyecto, story.getProyecto_idProyecto());
        values.put(Story.KEY_texto, story.getTexto());
        values.put(Story.KEY_prioridad, story.getPrioridad());

        // Inserting Row
        rowID = (int) db.insert(Story.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Story.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
