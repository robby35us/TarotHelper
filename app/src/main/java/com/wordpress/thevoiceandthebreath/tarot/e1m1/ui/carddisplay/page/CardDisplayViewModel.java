package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.carddisplay.page;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadCard.LoadCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadCard.LoadCardUseCase;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest.LoadImageInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadImageAssest.LoadImageOutputPort;
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

    MutableLiveData<CardModel> getCard(Context context, CardKey key) {
        if(ifLiveDataNotSet())
            retrieveCardData(context, key);
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

    private void retrieveCardData(Context context, CardKey key) {
        new LoadCardUseCase().execute(new LoadCard.Params(context, key),
                                this, DataPort.getInstance(context));
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