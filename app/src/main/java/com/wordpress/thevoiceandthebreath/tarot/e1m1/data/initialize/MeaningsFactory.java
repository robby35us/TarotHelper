package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize;

import android.support.annotation.NonNull;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;

import java.util.ArrayList;
import java.util.List;

class MeaningsFactory {

    private MeaningsFactory() { }


    static MeaningData getMajorArcanaMeaning(@NonNull  Number number){
        MeaningData meaning = new MeaningData();
        meaning.core = MeaningsBank.getCoreMeaning(number, null, null);
        String[] keywords = MeaningsBank.getKeywords(number, null, null);
        meaning.populateKeywords(toList(keywords));
        return meaning;
    }

    static MeaningData getMinorArcanaMeaning(@NonNull Suit suit, @NonNull Rank rank) {
        MeaningData meaning = new MeaningData();
        meaning.core = MeaningsBank.getCoreMeaning(null, suit, rank);
        String[] keywords = MeaningsBank.getKeywords(null, suit, rank);
        meaning.populateKeywords(toList(keywords));
        return meaning;
    }

    static MeaningData getReversedMajorArcanaMeaning(@NonNull Number number){
        MeaningData meaning = new MeaningData();
        meaning.core = MeaningsBank.getReversedCore(number, null, null);
        String[] keywords = MeaningsBank.getReversedKeywords(number, null, null);
        meaning.populateKeywords(toList(keywords));
        return meaning;
    }

    static MeaningData getReversedMinorArcanaMeaning(@NonNull Suit suit, @NonNull Rank rank) {
        MeaningData meaning = new MeaningData();
        meaning.core = MeaningsBank.getReversedCore(null, suit, rank);
        String[] keywords = MeaningsBank.getReversedKeywords(null, suit, rank);
        meaning.populateKeywords(toList(keywords));
        return meaning;
    }

    private static List<String> toList(String[] array) {
        List<String> list = new ArrayList<>(4);
        list.add(array[0]);
        list.add(array[1]);
        list.add(array[2]);
        list.add(array[3]);
        return list;
    }

}
