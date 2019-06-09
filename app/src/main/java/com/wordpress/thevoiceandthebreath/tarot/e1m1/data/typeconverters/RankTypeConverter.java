package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.typeconverters;

import android.arch.persistence.room.TypeConverter;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;

public class RankTypeConverter {
    @TypeConverter
    public static int getInt(Rank rank) {
        return rank.ordinal();
    }

    @TypeConverter
    public static Rank getRank(int index) {
        return Rank.values()[index];
    }
}
