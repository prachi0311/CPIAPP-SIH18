package com.example.prachisingh.cpi_ur.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.prachisingh.cpi_ur.models.Item;

/**
 * Created by YourFather on 08-02-2018.
 */

@Database(entities = {Item.class}, version = 1)
public abstract class CPIDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    private static CPIDatabase database;

    public static CPIDatabase getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context, CPIDatabase.class, CPIContract.CPI_DATABASE_NAME).build();
        }

        return database;
    }
}
