package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.initialize;

import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.Repository;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.Card;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Name;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Suit;

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
        Meaning[] meaningsArray = new Meaning[params.size()];
        for(int i = 0; i < params.size(); i++) {
            Meaning meaning = generateUprightMeaning(params.get(i));
            meaningIdsList.add(meaning.id);
            meaningsArray[i] = meaning;
        }
        repository.insertMeanings(meaningsArray);
        return meaningIdsList;
    }

    private List<String> generateAndStoreReversedMeanings(List<CardParams> params) {
        List<String> meaningIdsList = new ArrayList<>(params.size());
        Meaning[] meaningsArray = new Meaning[params.size()];
        for(int i = 0; i < params.size(); i++) {
            Meaning meaning = generateReversedMeaning(params.get(i));
            meaningIdsList.add(meaning.id);
            meaningsArray[i] = meaning;
        }
        repository.insertMeanings(meaningsArray);
        return meaningIdsList;
    }


    private void generateAndStoreCards(List<CardParams> params, List<String> uprightIds, List<String> reversedIds) {
        MajorCard[] majorCards = new MajorCard[Arcana.MAJOR_ARCANA_SIZE];
        MinorCard[] minorCards = new MinorCard[Arcana.MINOR_ARCANA_SIZE];
        for(int i = 0; i < Arcana.MAJOR_ARCANA_SIZE; i++)
            majorCards[i] = (MajorCard) instantiateNewCard(params.get(i), uprightIds.get(i), reversedIds.get(i));
        repository.insertMajorCards(majorCards);

        for(int i = 0; i < Arcana.MINOR_ARCANA_SIZE; i++)
            minorCards[i] = (MinorCard) instantiateNewCard(params.get(Arcana.MAJOR_ARCANA_SIZE + i),
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

    private Meaning generateUprightMeaning(CardParams ids) {
        if (ids.getArcana() == Arcana.MAJOR)
            return MeaningsFactory.getMajorArcanaMeaning(ids.getNumber());
        else
            return MeaningsFactory.getMinorArcanaMeaning(ids.getSuit(), ids.getRank());
    }

    private Meaning generateReversedMeaning(CardParams ids) {
        if (ids.getArcana() == Arcana.MAJOR)
            return MeaningsFactory.getReversedMajorArcanaMeaning(ids.getNumber());
        else
            return MeaningsFactory.getReversedMinorArcanaMeaning(ids.getSuit(), ids.getRank());
    }

    private Card instantiateNewCard(CardParams ids, String upright, String reversed) {
        if (ids.getArcana() == Arcana.MAJOR) {
            return new MajorCard(ids.getNumber(), upright, reversed);
        } else
            return new MinorCard(ids.getSuit(), ids.getRank(), upright, reversed);
    }

    static class CardParams {
        private Arcana arcana;
        private Name name;
        private Number number;
        private Suit suit;
        private Rank rank;

        // call for major arcana cards
        CardParams(Number number) {
            this(Arcana.MAJOR, number.getMatchingName(), number,
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
