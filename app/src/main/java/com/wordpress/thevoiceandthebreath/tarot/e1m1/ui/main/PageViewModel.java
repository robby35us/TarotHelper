package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.CardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCardWithMeanings;
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

    public MutableLiveData<CardWithMeanings> getCard(Context context, int index) {
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
        cwm.card = majorCardWithMeanings.majorCard;
        cwm.upright = majorCardWithMeanings.uprightMeanings.get(0);
        cwm.reversed = majorCardWithMeanings.reversedMeanings.get(0);

        addObjectsToCardsUpToIndex(cwm.card.getId());
        cards.get(cwm.card.getId()).setValue(cwm);
    }

    public void setCard(MinorCardWithMeanings minorCardWithMeanings) {
        CardWithMeanings cwm = new CardWithMeanings();
        cwm.card = minorCardWithMeanings.minorCard;
        cwm.upright = minorCardWithMeanings.uprightMeanings.get(0);
        cwm.reversed = minorCardWithMeanings.reversedMeanings.get(0);


        addObjectsToCardsUpToIndex(cwm.card.getId());
        cards.get(cwm.card.getId()).setValue(cwm);
    }
}