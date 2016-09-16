package com.jacaranda.myscrum.data.repo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.jacaranda.myscrum.data.DatabaseManager;
import com.jacaranda.myscrum.data.model.OutStoryXSprint;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Gaby on 03/09/2016.
 */
public class OutStoryXSprintRepo {
    private OutStoryXSprint outStoryXSprint;

    public OutStoryXSprintRepo(){
        outStoryXSprint = new OutStoryXSprint();
    }

    public static String createTable(){
        return "CREATE TABLE " + OutStoryXSprint.TABLE  + "("
                + OutStoryXSprint.KEY_Story_idStory  + " INTEGER, "
                + OutStoryXSprint.KEY_SprintXRelease_idSprintXRelease + " INTEGER, "
                + "FOREIGN KEY (Story_idStory) REFERENCES Story (idStory) "
                + "FOREIGN KEY (SprintXRelease_idSprintXRelease) REFERENCES SprintXRelease (idSprintXRelease) )";
    }

    public int insert(OutStoryXSprint outStoryXSprint) {
        // TODO insert with actual sql code
        int rowID;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(OutStoryXSprint.KEY_Story_idStory, outStoryXSprint.getStory_idStory());
        values.put(OutStoryXSprint.KEY_SprintXRelease_idSprintXRelease, outStoryXSprint.getSprintXRelease_idSprintXRelease());

        // Inserting Row
        rowID = (int) db.insert(OutStoryXSprint.TABLE, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return rowID;
    }

    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(OutStoryXSprint.TABLE,null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void insertLista(LinkedList<Integer> lista, int idSprint) {
        ListIterator<Integer> listIterator = lista.listIterator();
        outStoryXSprint = new OutStoryXSprint();
        outStoryXSprint.setSprintXRelease_idSprintXRelease(idSprint);
        while(listIterator.hasNext()) {
            int idStory = listIterator.next();
            outStoryXSprint.setStory_idStory(idStory);
            insert(outStoryXSprint);
        }
    }
}
