package com.example.mahmoudebrahim.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mahmoudebrahim.Api.Model.NewsSourceItemResponse.ArticlesItem;
import com.example.mahmoudebrahim.Api.Model.NewsSourceResponse.SourcesItem;
import com.example.mahmoudebrahim.Database.DAOs.SoursesDAO;

@Database(entities = {SourcesItem.class,ArticlesItem.class},version = 3,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract SoursesDAO soursesDAO();
    private static MyDatabase myDatabase;

    public static void init(Context context){
        if (myDatabase==null){
            myDatabase=Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class,
                    "NEWS-Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
    }

    public static MyDatabase getInstance(){

        return myDatabase;
    }

}
