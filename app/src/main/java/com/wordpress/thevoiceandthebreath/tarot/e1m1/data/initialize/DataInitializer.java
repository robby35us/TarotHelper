package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize;

import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.CardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MajorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MinorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.database.Repository;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MajorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MinorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

import java.util.ArrayList;
import java.util.List;

public class DataInitializer {


    private Repository repository;

    private static DataInitializer factory;

    public static DataInitializer getFactory(Context context) {
        if(factory == null) {
            factory = new DataInitializer(context);
        }
        return factory;
    }

    private DataInitializer(Context context) {
        if (repository == null) {
            repository = Repository.getRepository(context);
        }
    }

    public void populateDatabase(){
        List<CardKey> params = generateCardKeyList();
        List<String> uprightIds = generateAndStoreUprightMeanings(params);
        List<String> reversedIds = generateAndStoreReversedMeanings(params);
        generateAndStoreCards(params, uprightIds, reversedIds);
    }

    private List<CardKey> generateCardKeyList() {
        List<CardKey> params = new ArrayList<>();
        params.addAll(getMajorArcanaParamsList());
        params.addAll(getWandsParamsList());
        params.addAll(getCupsParamsList());
        params.addAll(getSwordsParamsList());
        params.addAll(getPentaclesParamsList());
        return params;
    }

    private List<String> generateAndStoreUprightMeanings(List<CardKey> params) {
        List<String> meaningIdsList = new ArrayList<>(params.size());
        MeaningData[] meaningsArray = new MeaningData[params.size()];
        for(int i = 0; i < params.size(); i++) {
            MeaningData meaning = MeaningsFactory.getUprightMeaning(params.get(i));
            meaningIdsList.add(meaning.id);
            meaningsArray[i] = meaning;
        }
        repository.insertMeanings(meaningsArray);
        return meaningIdsList;
    }

    private List<String> generateAndStoreReversedMeanings(List<CardKey> params) {
        List<String> meaningIdsList = new ArrayList<>(params.size());
        MeaningData[] meaningsArray = new MeaningData[params.size()];
        for(int i = 0; i < params.size(); i++) {
            MeaningData meaning = MeaningsFactory.getReversedMeaning( params.get(i));
            meaningIdsList.add(meaning.id);
            meaningsArray[i] = meaning;
        }
        repository.insertMeanings(meaningsArray);
        return meaningIdsList;
    }


    private void generateAndStoreCards(List<CardKey> params, List<String> uprightIds, List<String> reversedIds) {
        MajorCardData[] majorCards = new MajorCardData[Arcana.MAJOR_ARCANA_SIZE];
        MinorCardData[] minorCards = new MinorCardData[Arcana.MINOR_ARCANA_SIZE];
        for(int i = 0; i < Arcana.MAJOR_ARCANA_SIZE; i++)
            majorCards[i] = (MajorCardData) instantiateNewCard(params.get(i), uprightIds.get(i), reversedIds.get(i));
        repository.insertMajorCards(majorCards);

        for(int i = 0; i < Arcana.MINOR_ARCANA_SIZE; i++)
            minorCards[i] = (MinorCardData) instantiateNewCard(params.get(Arcana.MAJOR_ARCANA_SIZE + i),
                                                           uprightIds.get(Arcana.MAJOR_ARCANA_SIZE + i),
                                                           reversedIds.get(Arcana.MAJOR_ARCANA_SIZE + i));
        repository.insertMinorCards(minorCards);
    }

    private List<CardKey> getMajorArcanaParamsList() {
        List<CardKey> CardKey = new ArrayList<>();
        for (Number num : Number.values())
            CardKey.add(new MajorCardKey(num));
        return CardKey;
    }

    private List<CardKey> getWandsParamsList() {
        List<CardKey> CardKey = new ArrayList<>();
        for (Rank rank : Rank.values())
            CardKey.add(new MinorCardKey(Suit.WANDS, rank));
        return CardKey;
    }

    private List<CardKey> getCupsParamsList() {
        List<CardKey> CardKey = new ArrayList<>();
        for (Rank rank : Rank.values())
            CardKey.add(new MinorCardKey(Suit.CUPS, rank));
        return CardKey;
    }

    private List<CardKey> getSwordsParamsList() {
        List<CardKey> CardKey = new ArrayList<>();
        for (Rank rank : Rank.values())
            CardKey.add(new MinorCardKey(Suit.SWORDS, rank));
        return CardKey;
    }

    private List<CardKey> getPentaclesParamsList() {
        List<CardKey> CardKey = new ArrayList<>();
        for (Rank rank : Rank.values())
            CardKey.add(new MinorCardKey(Suit.PENTACLES, rank));
        return CardKey;
    }

    private CardData instantiateNewCard(CardKey key, String upright, String reversed) {
        if (key.getArcana() == Arcana.MAJOR) {
            return new MajorCardData((MajorCardKey) key, upright, reversed);
        } else
            return new MinorCardData((MinorCardKey) key, upright, reversed);
    }
}
