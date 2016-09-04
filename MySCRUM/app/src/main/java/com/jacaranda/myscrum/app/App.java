package com.jacaranda.myscrum.app;

import android.app.Application;
import android.content.Context;

import com.jacaranda.myscrum.data.DBHelper;
import com.jacaranda.myscrum.data.DatabaseManager;

/**
 * Created by Gaby on 03/09/2016.
 */
public class App extends Application{
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);
    }

    public static Context getContext(){
        return context;
    }
}
