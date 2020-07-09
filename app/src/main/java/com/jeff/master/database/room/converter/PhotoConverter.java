package com.jeff.master.database.room.converter;

import androidx.room.TypeConverter;

import com.jeff.master.database.local.Photo;
import com.jeff.master.utilities.ConverterUtil;

public class PhotoConverter {
    private PhotoConverter() { }

    @TypeConverter
    public static String fromPhoto(Photo photo) {
        return ConverterUtil.serialise(photo);
    }

    @TypeConverter
    public static Photo toPhoto(String serialised) {
        return ConverterUtil.deserialise(serialised, Photo.class);
    }
}
