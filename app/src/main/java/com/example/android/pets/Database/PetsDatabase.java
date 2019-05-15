package com.example.android.pets.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.pets.Entities.Pets;
import com.example.android.pets.dao.PetsDao;

@Database(entities = {Pets.class}, version = 1)
public abstract class PetsDatabase extends RoomDatabase {

    private static PetsDatabase INSTANCE;

    public abstract PetsDao petsDao();

    public static PetsDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), PetsDatabase.class, "pets-database")
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
