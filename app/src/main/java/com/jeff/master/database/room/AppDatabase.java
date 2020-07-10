package com.jeff.master.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.jeff.master.database.local.Media;
import com.jeff.master.database.room.converter.MediaConverter;
import com.jeff.master.database.room.dao.MediaDao;

@Database(
        entities = {
                Media.class
        },
        version = 11,
        exportSchema = false
)

@TypeConverters(
        {
                MediaConverter.class
        })
public abstract class AppDatabase extends RoomDatabase {
        public abstract MediaDao mediaDao();
}
