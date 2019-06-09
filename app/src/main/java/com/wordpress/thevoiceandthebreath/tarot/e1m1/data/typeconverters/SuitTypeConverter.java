package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.typeconverters;

import android.arch.persistence.room.TypeConverter;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

public class SuitTypeConverter {
    @TypeConverter
    public static int getInt(Suit suit){
        return suit.ordinal();
    }

    @TypeConverter
    public static Suit getSuit(int index) {
        return Suit.values()[index];
    }
}
