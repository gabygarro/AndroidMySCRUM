package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.Story;

import java.util.LinkedList;

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
        //values.put(Story.KEY_idStory, story.getIdStory());
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

    public LinkedList<Story> getStories(int idProyecto) {
        LinkedList<Story> stories = new LinkedList<>();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String selectQuery = "SELECT * FROM " + Story.TABLE + " WHERE " +
                Story.KEY_Proyecto_idProyecto + " = " + idProyecto +
                " ORDER BY " + Story.KEY_prioridad;

        Log.d("db", selectQuery);

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (Exception e) {
            Log.d("main", e.toString());
        }

        //Loop through cursor
        if (cursor.moveToFirst()) {
            do {
                Story story = new Story();
                story.setProyecto_idProyecto(idProyecto);
                story.setIdStory(cursor.getInt(cursor.getColumnIndex(Story.KEY_idStory)));
                story.setTexto(cursor.getString(cursor.getColumnIndex(Story.KEY_texto)));
                story.setPrioridad(cursor.getInt(cursor.getColumnIndex(Story.KEY_prioridad)));
                stories.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return stories;
    }
}
