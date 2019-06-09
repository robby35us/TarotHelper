package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard;

import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

public interface RetrieveMinorArcanaCardInPort {
    void execute(Params params, RetrieveMinorArcanaCardOutPort outPort,
                 RetrieveMinorArcanaCardRequestDAI requestDai);

    class Params{
        public Context context;
        public Suit suit;
        public Rank rank;

        public Params( Context context, Suit suit, Rank rank) {
            this.context = context;
            this.suit = suit;
            this.rank = rank;
        }
    }
}
