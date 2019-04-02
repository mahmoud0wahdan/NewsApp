package com.example.mahmoudebrahim.newsapp;

import android.app.Application;

import com.example.mahmoudebrahim.Database.MyDatabase;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyDatabase.init(this);
    }
}
