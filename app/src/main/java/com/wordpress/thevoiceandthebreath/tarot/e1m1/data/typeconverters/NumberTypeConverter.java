package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.typeconverters;

import android.arch.persistence.room.TypeConverter;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;

public class NumberTypeConverter {

    @TypeConverter
    public static int getInt(Number number) {
        return number.ordinal();
    }

    @TypeConverter
    public static Number getNumber(int index) {
        return Number.values()[index];
    }
}
