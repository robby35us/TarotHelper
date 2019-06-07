package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.boundries.ui.LoadImageInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.boundries.ui.LoadImageOutputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.CardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.boundries.ui.FindCardByIndexInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.boundries.ui.FindCardByIndexOutputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.interactors.FindCardByIndexInteractor;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.interactors.LoadImageInteractor;

public class CardDisplayViewModel extends ViewModel
        implements FindCardByIndexOutputPort, LoadImageOutputPort {
    private MutableLiveData<CardWithMeanings> card;
    private MutableLiveData<Drawable> image;

    public CardDisplayViewModel() {
        card = new MutableLiveData<>();
    }

    MutableLiveData<CardWithMeanings> getCard(Context context, int index) {
        if(ifLiveDataNotSet())
            retrieveCardData(context, index);
        return card;
    }

    LiveData<Drawable> loadImage(AssetManager assets, String filename) {
        image = new MutableLiveData<>();
        LoadImageInputPort.Params params
                = new LoadImageInputPort.Params(assets, filename);
        new LoadImageInteractor().execute(params, this);
        return image;
    }

    private boolean ifLiveDataNotSet() {
        return card.getValue() == null;
    }

    private void retrieveCardData(Context context, int index) {
        FindCardByIndexInputPort.Params params
                = new FindCardByIndexInputPort.Params(context, index);
        new FindCardByIndexInteractor().execute(params, this);
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

    @Override
    public void acceptDrawable(Drawable drawable) {
        image.setValue(drawable);
    }
}