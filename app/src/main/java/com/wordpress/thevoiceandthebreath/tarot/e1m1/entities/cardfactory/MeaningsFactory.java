package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardfactory;

import android.support.annotation.NonNull;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.Meaning;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Suit;

public class MeaningsFactory {

    private MeaningsFactory() { }

    /*
     * Pass a Number enum to get the stored Meaning object for a Major Arcana card
     */
    public static Meaning getMajorArcanaMeaning(@NonNull  Number number){
        String core = MeaningsBank.getCoreMeaning(number, null, null);
        String[] keywords = MeaningsBank.getKeywords(number, null, null);
        return new Meaning(core, keywords);
    }

    /*
     * Pass a Suit and Rank enum to get the stored Meaning object for a Minor Arcana card
     */
    public static Meaning getMinorArcanaMeaning(@NonNull Suit suit, @NonNull Rank rank) {
        String core = MeaningsBank.getCoreMeaning(null, suit, rank);
        String[] keywords = MeaningsBank.getKeywords(null, suit, rank);
        return new Meaning(core, keywords);
    }

    public static Meaning getReversedMajorArcanaMeaning(@NonNull Number number){
        String core = MeaningsBank.getReversedCore(number, null, null);
        String[] keywords = MeaningsBank.getReversedKeywords(number, null, null);
        return new Meaning(core, keywords);
    }

    public static Meaning getReversedMinorArcanaMeaning(@NonNull Suit suit, @NonNull Rank rank) {
        String core = MeaningsBank.getReversedCore(null, suit, rank);
        String[] keywords = MeaningsBank.getReversedKeywords(null, suit, rank);
        return new Meaning(core, keywords);
    }

}
