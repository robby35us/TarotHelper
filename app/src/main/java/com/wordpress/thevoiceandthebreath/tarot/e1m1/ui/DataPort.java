package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.CardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MeaningSet;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.TarotCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MajorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MinorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadCard.LoadCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.database.Repository;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;

import java.util.List;

public class DataPort implements LoadCard.RequestDAI {

    private static DataPort dataPort;

    private Repository repository;
    private LoadCard.Params params;
    private LoadCard.ResultDAI resultDAI;

    public static DataPort getInstance(Context context) {
        if (dataPort == null)
            dataPort = new DataPort(context);
        return dataPort;
    }

    private DataPort(Context context) {
        repository = Repository.getRepository(context);
    }

    @Override
    public void requestCard(LoadCard.Params params, LoadCard.ResultDAI resultDAI) {
        this.params = params;
        this.resultDAI = resultDAI;
        if (params.key.getArcana() == Arcana.MAJOR)
            requestMajorCard();
        else
            requestMinorCard();
    }

    private void requestMajorCard() {
        LiveData<List<MajorCardWithMeanings>> majorCard
                = repository.queryMajorCard((MajorCardKey) params.key);
        majorCard.observe((AppCompatActivity) params.context, majorObserver);
    }

    private void requestMinorCard() {
        LiveData<List<MinorCardWithMeanings>> minorCard
                = repository.queryMinorCard((MinorCardKey) params.key);
        minorCard.observe((AppCompatActivity) params.context, minorObserver);
    }


    private Observer<List<MajorCardWithMeanings>> majorObserver = new Observer<List<MajorCardWithMeanings>>() {
        @Override
        public void onChanged(@Nullable List<MajorCardWithMeanings> inCardList) {
            TarotCard outCard = new MajorArcanaCard((MajorCardKey) params.key);
            if (inCardList != null) {
                transferMeanings(outCard, inCardList.get(0));
            }
            resultDAI.handleResult(outCard);
        }
    };

    private Observer<List<MinorCardWithMeanings>> minorObserver = new Observer<List<MinorCardWithMeanings>>() {
        @Override
        public void onChanged(@Nullable List<MinorCardWithMeanings> inCardList) {
            TarotCard outCard = new MinorArcanaCard((MinorCardKey) params.key);
            if (inCardList != null)
                transferMeanings(outCard, inCardList.get(0));
            resultDAI.handleResult(outCard);
        }
    };

    private void transferMeanings(TarotCard outCard, CardWithMeanings inCard) {
        Pair<MeaningSet, MeaningSet> meanings = extractMeanings(inCard);
        outCard.setUprightMeanings(meanings.first);
        outCard.setReversedMeanings(meanings.second);
    }

    private Pair<MeaningSet, MeaningSet> extractMeanings(CardWithMeanings cardWithMeanings) {
        MeaningData uprightMeaning = extractUprightMeanings(cardWithMeanings);
        MeaningData reversedMeaning = extractReversedMeanings(cardWithMeanings);
        return new Pair<>(uprightMeaning.getMeaningSet(), reversedMeaning.getMeaningSet());
    }

    private MeaningData extractUprightMeanings(CardWithMeanings inCard) {
        if (inCard != null) {
            if (inCard.arcana != Arcana.MAJOR)
                return ((MajorCardWithMeanings) inCard).uprightMeanings.get(0);
            else
                return ((MinorCardWithMeanings) inCard).uprightMeanings.get(0);
        }
        return null;
    }

    private MeaningData extractReversedMeanings(CardWithMeanings inCard) {
        if (inCard != null) {
            if (inCard.arcana != Arcana.MAJOR)
                return ((MajorCardWithMeanings) inCard).reversedMeanings.get(0);
            else
                return ((MinorCardWithMeanings) inCard).reversedMeanings.get(0);
        }
        return null;
    }
}
