package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MeaningSet;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMajorArcanaCard.RetrieveMajorArcanaCardRequestDAI;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMajorArcanaCard.RetrieveMajorArcanaCardResultDAI;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard.RetrieveMinorArcanaCardRequestDAI;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard.RetrieveMinorArcanaCardResultDAI;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.database.Repository;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.MinorCardWithMeanings;

import java.util.List;

public class DataPort implements RetrieveMajorArcanaCardRequestDAI,
                                 RetrieveMinorArcanaCardRequestDAI {

    private static DataPort dataPort;

    private Repository repository;

    public static DataPort getInstance(Context context) {
        if(dataPort == null)
            dataPort = new DataPort(context);
        return dataPort;
    }

    private DataPort(Context context) {
        repository = Repository.getRepository(context);
    }

    @Override
    public void requestMajorCard(final RetrieveMajorArcanaCardRequestDAI.Params params,
                                 final RetrieveMajorArcanaCardResultDAI resultDAI) {
        LiveData<List<MajorCardWithMeanings>> cardWithMeanings
                = repository.queryMajorCard(params.number);
        cardWithMeanings.observe((AppCompatActivity) params.context, new Observer<List<MajorCardWithMeanings>>() {
            @Override
            public void onChanged(@Nullable List<MajorCardWithMeanings> majorCardWithMeanings){
                MajorArcanaCard outCard = new MajorArcanaCard(params.number) ;

                MeaningData inUpright = majorCardWithMeanings.get(0).uprightMeanings.get(0);
                outCard.setUprightMeanings(new MeaningSet(inUpright.core, inUpright.generateKeywordsList()));

                MeaningData inReversed = majorCardWithMeanings.get(0).reversedMeanings.get(0);
                outCard.setReversedMeanings( new MeaningSet(inReversed.core, inReversed.generateKeywordsList()));

                resultDAI.handleResult(outCard);
            }
        });

    }

    @Override
    public void requestMinorCard(final RetrieveMinorArcanaCardRequestDAI.Params params,
                                 final RetrieveMinorArcanaCardResultDAI resultDAI) {
        LiveData<List<MinorCardWithMeanings>> cardWithMeanings
                = repository.queryMinorCard(params.suit, params.rank);
        cardWithMeanings.observe((AppCompatActivity) params.context, new Observer<List<MinorCardWithMeanings>>() {
            @Override
            public void onChanged(@Nullable List<MinorCardWithMeanings> minorCardWithMeanings){
                MinorArcanaCard outCard = new MinorArcanaCard(params.suit, params.rank) ;

                MeaningData inUpright = minorCardWithMeanings.get(0).uprightMeanings.get(0);
                outCard.setUprightMeanings(new MeaningSet(inUpright.core, inUpright.generateKeywordsList()));

                MeaningData inReversed = minorCardWithMeanings.get(0).reversedMeanings.get(0);
                outCard.setReversedMeanings( new MeaningSet(inReversed.core, inReversed.generateKeywordsList()));

                resultDAI.handleResult(outCard);
            }
        });
    }
}
