package com.univ_amu.food_scanner.data.database;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.univ_amu.food_scanner.data.Food;
import com.univ_amu.food_scanner.data.Quantity;

@androidx.room.Database(entities = {Food.class, Quantity.class }, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {
    private static volatile Database database = null;

    public static synchronized Database getInstance(Context context) {
        if (database==null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract Dao dao();
}