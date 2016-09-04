package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.InStoryXSprint;

/**
 * Created by Gaby on 03/09/2016.
 */
public class InStoryXSprintRepo {
    private InStoryXSprint inStoryXSprint;

    public InStoryXSprintRepo(){
        inStoryXSprint = new InStoryXSprint();
    }

    public static String createTable(){
        return "CREATE TABLE " + InStoryXSprint.TABLE  + "("
                + InStoryXSprint.KEY_Story_idStory  + " INTEGER, "
                + InStoryXSprint.KEY_SprintXRelease_idSprintXRelease + " INTEGER, "
                + "FOREIGN KEY (Story_idStory) REFERENCES Story (idStory) "
                + "FOREIGN KEY (SprintXRelease_idSprintXRelease) REFERENCES SprintXRelease (idSprintXRelease) )";
    }

    public int insert(InStoryXSprint inStoryXSprint) {
        // TODO insert with actual sql code
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(InStoryXSprint.KEY_Story_idStory, inStoryXSprint.getStory_idStory());
        values.put(InStoryXSprint.KEY_SprintXRelease_idSprintXRelease, inStoryXSprint.getSprintXRelease_idSprintXRelease());

        // Inserting Row
        rowID = (int) db.insert(InStoryXSprint.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(InStoryXSprint.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
}
