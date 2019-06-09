package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadCard;

import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.TarotCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.CardModel;

public interface LoadCard {

    class Params{
        public Context context;
        public CardKey key;

        public Params (Context context, CardKey key) {
            this.context = context;
            this.key = key;
        }
    }

    interface InPort {
        void execute(Params params, OutPort outPort,
                     RequestDAI requestDai);
    }

    interface OutPort {
        void processResult(CardModel card);
    }

    interface RequestDAI {
        void requestCard(Params params, ResultDAI resultDAI);
    }

    interface ResultDAI {
        void handleResult(TarotCard card);
    }

}
