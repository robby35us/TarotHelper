package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest.LoadImageInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest.LoadImageOutputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMajorArcanaCard.RetrieveMajorArcanaCardInPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMajorArcanaCard.RetrieveMajorArcanaCardOutPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMajorArcanaCard.RetrieveMajorArcanaCardUseCase;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard.RetrieveMinorArcanaCardInPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard.RetrieveMinorArcanaCardOutPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard.RetrieveMinorArcanaCardUseCase;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.CardModel;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest.LoadImageInteractor;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.DataPort;

public class CardDisplayViewModel extends ViewModel
                                  implements RetrieveMajorArcanaCardOutPort,
                                             RetrieveMinorArcanaCardOutPort,
                                             LoadImageOutputPort {
    private MutableLiveData<CardModel> card;
    private MutableLiveData<Drawable> image;

    public CardDisplayViewModel() {
        card = new MutableLiveData<>();
    }

    MutableLiveData<CardModel> getCard(Context context, int index) {
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
        if(index < Arcana.MAJOR_ARCANA_SIZE)
            requestMajorArcanaCard(context, index);
        else
            requestMinorArcanaCard(context, index);
    }

    private void requestMajorArcanaCard(Context context, int index){
        RetrieveMajorArcanaCardInPort.Params params
                = new RetrieveMajorArcanaCardInPort.Params(context, Number.values()[index]);
        new RetrieveMajorArcanaCardUseCase().execute(params, this, DataPort.getInstance(context));
    }

    private void requestMinorArcanaCard(Context context, int index) {
        int minorArcanaIndex = index - Arcana.MAJOR_ARCANA_SIZE;
        Suit suit = Suit.values()[minorArcanaIndex / Rank.NUM_RANKS];
        Rank rank = Rank.values()[minorArcanaIndex % Rank.NUM_RANKS];
        RetrieveMinorArcanaCardInPort.Params params
                = new RetrieveMinorArcanaCardInPort.Params(context, suit, rank);
        new RetrieveMinorArcanaCardUseCase().execute(params, this, DataPort.getInstance(context));
    }

    @Override
    public void processResult(CardModel cardModel) {
        card.setValue(cardModel);
    }

    @Override
    public void acceptDrawable(Drawable drawable) {
        image.setValue(drawable);
    }
}