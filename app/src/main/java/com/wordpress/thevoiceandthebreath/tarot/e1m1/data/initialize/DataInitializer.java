package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize;

import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.CardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MajorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MinorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.database.Repository;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Name;
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
        List<CardParams> params = generateCardParamsList();
        List<String> uprightIds = generateAndStoreUprightMeanings(params);
        List<String> reversedIds = generateAndStoreReversedMeanings(params);
        generateAndStoreCards(params, uprightIds, reversedIds);
    }

    private List<CardParams> generateCardParamsList() {
        List<CardParams> params = new ArrayList<>();
        params.addAll(getMajorArcanaParamsList());
        params.addAll(getWandsParamsList());
        params.addAll(getCupsParamsList());
        params.addAll(getSwordsParamsList());
        params.addAll(getPentaclesParamsList());
        return params;
    }

    private List<String> generateAndStoreUprightMeanings(List<CardParams> params) {
        List<String> meaningIdsList = new ArrayList<>(params.size());
        MeaningData[] meaningsArray = new MeaningData[params.size()];
        for(int i = 0; i < params.size(); i++) {
            MeaningData meaning = generateUprightMeaning(params.get(i));
            meaningIdsList.add(meaning.id);
            meaningsArray[i] = meaning;
        }
        repository.insertMeanings(meaningsArray);
        return meaningIdsList;
    }

    private List<String> generateAndStoreReversedMeanings(List<CardParams> params) {
        List<String> meaningIdsList = new ArrayList<>(params.size());
        MeaningData[] meaningsArray = new MeaningData[params.size()];
        for(int i = 0; i < params.size(); i++) {
            MeaningData meaning = generateReversedMeaning(params.get(i));
            meaningIdsList.add(meaning.id);
            meaningsArray[i] = meaning;
        }
        repository.insertMeanings(meaningsArray);
        return meaningIdsList;
    }


    private void generateAndStoreCards(List<CardParams> params, List<String> uprightIds, List<String> reversedIds) {
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

    private List<CardParams> getMajorArcanaParamsList() {
        List<CardParams> cardParams = new ArrayList<>();
        for (Number num : Number.values())
            cardParams.add(new CardParams(num));
        return cardParams;
    }

    private List<CardParams> getWandsParamsList() {
        List<CardParams> cardParams = new ArrayList<>();
        for (Rank rank : Rank.values())
            cardParams.add(new CardParams(Suit.WANDS, rank));
        return cardParams;
    }

    private List<CardParams> getCupsParamsList() {
        List<CardParams> cardParams = new ArrayList<>();
        for (Rank rank : Rank.values())
            cardParams.add(new CardParams(Suit.CUPS, rank));
        return cardParams;
    }

    private List<CardParams> getSwordsParamsList() {
        List<CardParams> cardParams = new ArrayList<>();
        for (Rank rank : Rank.values())
            cardParams.add(new CardParams(Suit.SWORDS, rank));
        return cardParams;
    }

    private List<CardParams> getPentaclesParamsList() {
        List<CardParams> cardParams = new ArrayList<>();
        for (Rank rank : Rank.values())
            cardParams.add(new CardParams(Suit.PENTACLES, rank));
        return cardParams;
    }

    private MeaningData generateUprightMeaning(CardParams ids) {
        if (ids.getArcana() == Arcana.MAJOR)
            return MeaningsFactory.getMajorArcanaMeaning(ids.getNumber());
        else
            return MeaningsFactory.getMinorArcanaMeaning(ids.getSuit(), ids.getRank());
    }

    private MeaningData generateReversedMeaning(CardParams ids) {
        if (ids.getArcana() == Arcana.MAJOR)
            return MeaningsFactory.getReversedMajorArcanaMeaning(ids.getNumber());
        else
            return MeaningsFactory.getReversedMinorArcanaMeaning(ids.getSuit(), ids.getRank());
    }

    private CardData instantiateNewCard(CardParams ids, String upright, String reversed) {
        if (ids.getArcana() == Arcana.MAJOR) {
            return new MajorCardData(ids.getNumber(), upright, reversed);
        } else
            return new MinorCardData(ids.getSuit(), ids.getRank(), upright, reversed);
    }

    static class CardParams {
        private Arcana arcana;
        private Name name;
        private Number number;
        private Suit suit;
        private Rank rank;

        // call for major arcana cards
        CardParams(Number number) {
            this(Arcana.MAJOR, Name.values()[number.ordinal()], number,
                    null, null);
        }

        // Call for minor arcana cards
        CardParams(Suit suit, Rank rank) {
            this(Arcana.MINOR, null, null, suit, rank);
        }

        private CardParams(Arcana arcana, Name name, Number number,
                           Suit suit, Rank rank) {
            this.arcana = arcana;
            this.name = name;
            this.number = number;
            this.suit = suit;
            this.rank = rank;
        }

        Arcana getArcana() {
            return arcana;
        }

        public Name getName() {
            return name;
        }

        Number getNumber() {
            return number;
        }

        Suit getSuit() {
            return suit;
        }

        Rank getRank() {
            return rank;
        }
    }
}
