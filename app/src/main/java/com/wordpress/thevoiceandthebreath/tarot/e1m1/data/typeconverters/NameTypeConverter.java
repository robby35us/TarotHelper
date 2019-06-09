package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.typeconverters;

import android.arch.persistence.room.TypeConverter;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Name;

public class NameTypeConverter {
    @TypeConverter
    public static int getInt(Name name) {
        return name.ordinal();
    }

    @TypeConverter
    public static Name getName(int index) {
        return Name.values()[index];
    }
}
