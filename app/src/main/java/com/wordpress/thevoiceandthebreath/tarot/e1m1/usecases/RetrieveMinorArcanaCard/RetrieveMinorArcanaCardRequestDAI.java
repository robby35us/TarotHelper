package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard;

import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

public interface RetrieveMinorArcanaCardRequestDAI {
    void requestMinorCard(Params params, RetrieveMinorArcanaCardResultDAI resultDAI);

    class Params {
        public Context context;
        public Rank rank;
        public Suit suit;

        public Params(Context context, Rank rank, Suit suit) {
            this.context = context;
            this.rank = rank;
            this.suit = suit;
        }
    }
}
