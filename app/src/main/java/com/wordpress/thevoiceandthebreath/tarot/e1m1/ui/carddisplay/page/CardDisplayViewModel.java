package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MajorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.MinorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadCard.LoadCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadCard.LoadCardUseCase;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest.LoadImageInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest.LoadImageOutputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.CardModel;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest.LoadImageInteractor;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.DataPort;

public class CardDisplayViewModel extends ViewModel
                                  implements LoadCard.OutPort,
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
        CardKey key = createKeyFromIndex(index);
        new LoadCardUseCase().execute(new LoadCard.Params(context, key),
                                this, DataPort.getInstance(context));
    }

    private CardKey createKeyFromIndex(int index){
        if(index < Arcana.MAJOR_ARCANA_SIZE) {
            return new MajorCardKey(Number.values()[index]);
        } else {
            int minorArcanaIndex = index - Arcana.MAJOR_ARCANA_SIZE;
            Suit suit = Suit.values()[minorArcanaIndex / Rank.NUM_RANKS];
            Rank rank = Rank.values()[minorArcanaIndex % Rank.NUM_RANKS];
            return new MinorCardKey(suit, rank);}
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