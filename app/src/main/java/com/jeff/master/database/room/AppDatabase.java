package com.jeff.master.database.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.jeff.master.database.local.Photo;
import com.jeff.master.database.local.Media;
import com.jeff.master.database.room.converter.MediaConverter;
import com.jeff.master.database.room.converter.PhotoConverter;
import com.jeff.master.database.room.dao.MediaDao;
import com.jeff.master.database.room.dao.PhotoDao;

@Database(
        entities = {
                Photo.class,
                Media.class
        },
        version = 9,
        exportSchema = false
)

@TypeConverters(
        {
                PhotoConverter.class,
                MediaConverter.class
        })
public abstract class AppDatabase extends RoomDatabase {
        public abstract PhotoDao photoDao();
        public abstract MediaDao mediaDao();
}
