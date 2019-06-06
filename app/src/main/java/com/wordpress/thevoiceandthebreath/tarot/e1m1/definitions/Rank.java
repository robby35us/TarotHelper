// Rank.java
package com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions;

import android.arch.persistence.room.TypeConverter;

// Every minor arcana card has one of the following ranks
public enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, PAGE, KNIGHT, QUEEN, KING;

    @Override
    public String toString() {
        switch (this) {
            case ACE: return "Ace";
            case TWO: return "Two";
            case THREE: return "Three";
            case FOUR: return "Four";
            case FIVE: return "Five";
            case SIX: return "Six";
            case SEVEN: return "Seven";
            case EIGHT: return "Eight";
            case NINE: return "Nine";
            case TEN: return "Ten";
            case PAGE: return "Page";
            case KNIGHT: return "Knight";
            case QUEEN: return "Queen";
            case KING: return "King";
        }

        return super.toString();
    }

    public Mode getMode() {
        switch(this) {
            case ACE:
                return Mode.ACE;
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
            case TEN:
                return Mode.PIP;
            case PAGE:
            case KNIGHT:
            case QUEEN:
            case KING:
                return Mode.COURT;
        }
        return null;
    }

    @TypeConverter
    public static int getInt(Rank rank) { return rank.ordinal();}
    @TypeConverter
    public static Rank getRank(int index) {
        return Rank.values()[index];
    }
}
