package com.wordpress.thevoiceandthebreath.tarot.e1m1.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.interactors.callback.CardRetriever;

import java.util.List;

public class CardBuffer {

    private static final int NUM_CARDS_TO_BUFFER = 5 ;

    private static CardBuffer cardBuffer;

    private Repository repository;
    private SparseArray<MajorCardWithMeanings> majorCache;
    private SparseArray<MinorCardWithMeanings> minorCache;
    private boolean[] marked;

    public static CardBuffer getCardBuffer(Context context) {
        if(cardBuffer == null)
            cardBuffer = new CardBuffer(context);
        return cardBuffer;
    }

    private CardBuffer(Context context) {
        repository = Repository.getRepository(context);
        majorCache = new SparseArray<>();
        minorCache = new SparseArray<>();
        marked = new boolean[Arcana.MAJOR_ARCANA_SIZE + Arcana.MINOR_ARCANA_SIZE];
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
        int max = Arcana.MAJOR_ARCANA_SIZE + Arcana.MINOR_ARCANA_SIZE - 1;
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
}
