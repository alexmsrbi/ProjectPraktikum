package com.example.projectpraktikum.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Register.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RegisterDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase initDB(Context context){
        if (appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class,"dbAkun")
                    .allowMainThreadQueries().build();
        return appDatabase;
    }
}
