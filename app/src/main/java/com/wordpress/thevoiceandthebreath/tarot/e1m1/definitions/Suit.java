package com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions;

import android.arch.persistence.room.TypeConverter;

public enum Suit{
    WANDS, CUPS, SWORDS, PENTACLES;

    @Override
    public String toString() {
        switch (this) {
            case WANDS: return "Wands";
            case CUPS: return "Cups";
            case SWORDS: return "Swords";
            case PENTACLES: return "Pentacles";
        }

        return super.toString();
    }

    public static int size(){
        return Suit.values().length;
    }

    @TypeConverter
    public static int getInt(Suit suit) { return suit.ordinal(); }
    @TypeConverter
    public static Suit getSuit(int index) {
        return Suit.values()[index];
    }
}
