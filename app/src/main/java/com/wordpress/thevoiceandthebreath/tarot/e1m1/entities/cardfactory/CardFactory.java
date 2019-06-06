package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardfactory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.Repository;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.Card;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.MeaningsFactory;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.callback.CardCountRetriever;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.callback.CardRetriever;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Name;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Suit;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    private static final int NUM_CARDS_TO_BUFFER = 5 ;

    private SparseArray<MajorCardWithMeanings> majorCache;
    private SparseArray<MinorCardWithMeanings> minorCache;
    private boolean[] marked;
    private Repository repository;
    private static int majorCardCount = 0;
    private static int minorCardCount = 0;


    private static CardFactory factory;

    public static CardFactory getFactory(Context context) {
        if(factory == null) {
            factory = new CardFactory(context);
        }
        return factory;
    }

    private CardFactory(Context context) {
        majorCache = new SparseArray<>();
        minorCache = new SparseArray<>();
        marked = new boolean[Arcana.MAJOR_ARCANA_SIZE + Arcana.MINOR_ARCANA_SIZE];
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

    public void retrieveCardByIndex(final int index, final CardRetriever retriever, Context context) {
        if (index < 22 && majorCache.get(index) != null) {
            retriever.receiveCard(majorCache.get(index));
            return;
        }
        if (index >= 22 && minorCache.get(index) != null) {
            retriever.receiveCard(minorCache.get(index));
            return;
        }

        int min = 0;
        int max = majorCardCount + minorCardCount - 1;
        int expectedLower = index - NUM_CARDS_TO_BUFFER >= min ? index - NUM_CARDS_TO_BUFFER : min;
        for(int i = expectedLower ; i < index; i ++) {
            if(marked[i])
                expectedLower++;
            else
                marked[i] = true;
        }
        int expectedUpper = index + NUM_CARDS_TO_BUFFER <= max ? index + NUM_CARDS_TO_BUFFER : max;
        for(int i = expectedUpper ; i > index; i--) {
            if(marked[i])
               expectedUpper--;
             else
                marked[i] = true;
        }

        final int actualLower = expectedLower;
        final int actualUpper = expectedUpper;

        final LiveData<List<MajorCardWithMeanings>> majorCards;
        final LiveData<List<MinorCardWithMeanings>> minorCards;

        if (actualLower < 22 && actualUpper < 22) {
            majorCards = repository.queryMajorCards(actualLower, actualUpper);
            minorCards = null;
        } else if (actualLower >= 22 && actualUpper >= 22) {
            majorCards = null;
            minorCards = repository.queryMinorCards(actualLower, actualUpper);
        } else {
            majorCards = repository.queryMajorCards(actualLower, 22 - 1);
            minorCards = repository.queryMinorCards(22, actualUpper);
        }
        if(majorCards != null)
            majorCards.observe((AppCompatActivity) context, new Observer<List<MajorCardWithMeanings>>() {
                @Override
                public void onChanged(@Nullable List<MajorCardWithMeanings> cards) {
                    if(cards == null)
                        return;
                    majorCards.removeObserver(this);
                    if(index < 22)
                        retriever.receiveCard(cards.get(index - actualLower));
                    for (int i = actualLower; i <= Math.min(actualUpper, 22 - 1); i++)
                        majorCache.put(i, cards.get(i - actualLower));
                }
            });
        if (minorCards != null)
            minorCards.observe((AppCompatActivity) context, new Observer<List<MinorCardWithMeanings>>() {
                @Override
                public void onChanged(@Nullable List<MinorCardWithMeanings> cards) {
                    if(cards == null)
                        return;
                    minorCards.removeObserver(this);
                    if(index >= 22)
                        retriever.receiveCard(cards.get((index - actualUpper) + cards.size()-1));
                    for (int i = Math.max(actualLower, 22); i <= actualUpper; i++)
                        minorCache.put(i, cards.get ((i - actualUpper) + cards.size() -1 )) ;
                }
            });
    }

    public void retrieveCardCount ( final CardCountRetriever retriever, Context context) {
        if (majorCardCount != 0 && minorCardCount != 0) {
            retriever.receiveCardCountCallback(majorCardCount + minorCardCount);
        } else {
            final Pair<LiveData<Integer>, LiveData<Integer>> counts = repository.queryCardCount();
            if (counts.first != null) {
                counts.first.observe((AppCompatActivity) context, new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer integer) {
                        if(integer != null) {
                            majorCardCount = integer;
                            if (minorCardCount != 0) {
                                retriever.receiveCardCountCallback(majorCardCount + minorCardCount);
                            }
                        }
                    }
                });
            }
            if (counts.second != null) {
                counts.second.observe((AppCompatActivity) context, new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer integer) {
                        if(integer != null) {
                            minorCardCount = integer;
                            if (majorCardCount != 0) {
                                retriever.receiveCardCountCallback(majorCardCount + minorCardCount);
                            }
                        }
                    }
                });
            }
        }
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
