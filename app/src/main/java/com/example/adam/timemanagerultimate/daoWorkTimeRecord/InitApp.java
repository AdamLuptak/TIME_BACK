package com.example.adam.timemanagerultimate.daoWorkTimeRecord;

import android.app.Application;

/**
 * Created by aluptak on 02/02/2016.
 */
public class InitApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        //initial database creation..
        new DbHelper(getApplicationContext()).getWritableDatabase();

    }
}