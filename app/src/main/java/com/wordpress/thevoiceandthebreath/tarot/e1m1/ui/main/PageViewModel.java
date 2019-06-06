package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.CardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex.FindCardByIndexInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex.FindCardByIndexOutputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex.FindCardByIndexUseCase;

import java.util.ArrayList;
import java.util.List;

public class PageViewModel extends ViewModel implements FindCardByIndexOutputPort {
    private List<MutableLiveData<CardWithMeanings>> cards;

    public PageViewModel () {
        cards = new ArrayList<>();
    }

    MutableLiveData<CardWithMeanings> getCard(Context context, int index) {
        addObjectsToCardsUpToIndex(index);
        if(ifLiveDataAtIndexNotSet(index))
            retrieveCardData(context, index);
        return cards.get( index);
    }

    private void addObjectsToCardsUpToIndex(int index){
        while(cards.size() <= index)
            cards.add(new MutableLiveData<CardWithMeanings>());
    }

    private boolean ifLiveDataAtIndexNotSet(int index) {
        return cards.get(index).getValue() == null;
    }

    private void retrieveCardData(Context context, int index) {
        new FindCardByIndexUseCase().execute(new FindCardByIndexInputPort.Params(context, index), this);
    }

    @Override
    public void setCard(MajorCardWithMeanings majorCardWithMeanings) {
        CardWithMeanings cwm = new CardWithMeanings();
        cwm.setCard(majorCardWithMeanings.majorCard);
        cwm.setUpright(majorCardWithMeanings.uprightMeanings.get(0));
        cwm.setReversed(majorCardWithMeanings.reversedMeanings.get(0));

        addObjectsToCardsUpToIndex(cwm.getCard().getId());
        cards.get(cwm.getCard().getId()).setValue(cwm);
    }

    public void setCard(MinorCardWithMeanings minorCardWithMeanings) {
        CardWithMeanings cwm = new CardWithMeanings();
        cwm.setCard(minorCardWithMeanings.minorCard);
        cwm.setUpright(minorCardWithMeanings.uprightMeanings.get(0));
        cwm.setReversed(minorCardWithMeanings.reversedMeanings.get(0));


        addObjectsToCardsUpToIndex(cwm.getCard().getId());
        cards.get(cwm.getCard().getId()).setValue(cwm);
    }
}