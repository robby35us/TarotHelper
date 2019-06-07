package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.CardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex.FindCardByIndexInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex.FindCardByIndexOutputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex.FindCardByIndexUseCase;

public class CardDisplayViewModel extends ViewModel implements FindCardByIndexOutputPort {
    private MutableLiveData<CardWithMeanings> card;

    public CardDisplayViewModel() {
        card = new MutableLiveData<>();
    }

    MutableLiveData<CardWithMeanings> getCard(Context context, int index) {
        if(ifLiveDataNotSet())
            retrieveCardData(context, index);
        return card;
    }

    private boolean ifLiveDataNotSet() {
        return card.getValue() == null;
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
        card.setValue(cwm);
    }

    @Override
    public void setCard(MinorCardWithMeanings minorCardWithMeanings) {
        CardWithMeanings cwm = new CardWithMeanings();
        cwm.setCard(minorCardWithMeanings.minorCard);
        cwm.setUpright(minorCardWithMeanings.uprightMeanings.get(0));
        cwm.setReversed(minorCardWithMeanings.reversedMeanings.get(0));
        card.setValue(cwm);
    }
}