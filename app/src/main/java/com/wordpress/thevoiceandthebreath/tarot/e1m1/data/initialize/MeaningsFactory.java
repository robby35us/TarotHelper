package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;

import java.util.ArrayList;
import java.util.List;

class MeaningsFactory {

    private MeaningsFactory() { }


    static MeaningData getUprightMeaning(CardKey key){
        MeaningData meaning = new MeaningData();
        meaning.core = MeaningsBank.getCoreMeaning(key);
        String[] keywords = MeaningsBank.getKeywords(key);
        meaning.populateKeywords(toList(keywords));
        return meaning;
    }

    static MeaningData getReversedMeaning(CardKey key){
        MeaningData meaning = new MeaningData();
        meaning.core = MeaningsBank.getReversedCore(key);
        String[] keywords = MeaningsBank.getReversedKeywords(key);
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
