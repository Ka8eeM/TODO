package com.example.karim.todo.DataBaseModels;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Task.class}, version = 1)
public abstract class ProjectDB extends RoomDatabase {

    private static ProjectDB INSTANCE;

    public abstract TaskDao taskDao();

    public static ProjectDB getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProjectDB.class, "TODO-database")
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
