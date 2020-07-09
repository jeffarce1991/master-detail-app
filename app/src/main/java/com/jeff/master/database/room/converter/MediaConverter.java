package com.jeff.master.database.room.converter;

import androidx.room.TypeConverter;

import com.jeff.master.database.local.Media;
import com.jeff.master.utilities.ConverterUtil;

public class MediaConverter {
    private MediaConverter() { }

    @TypeConverter
    public static String fromPhoto(Media media) {
        return ConverterUtil.serialise(media);
    }

    @TypeConverter
    public static Media toPhoto(String serialised) {
        return ConverterUtil.deserialise(serialised, Media.class);
    }
}
