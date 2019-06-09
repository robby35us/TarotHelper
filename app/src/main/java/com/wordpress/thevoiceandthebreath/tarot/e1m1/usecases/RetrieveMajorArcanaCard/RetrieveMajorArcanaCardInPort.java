package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMajorArcanaCard;

import android.content.Context;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;

public interface RetrieveMajorArcanaCardInPort {
    void execute(Params params, RetrieveMajorArcanaCardOutPort outPort,
                 RetrieveMajorArcanaCardRequestDAI requestDai);

    class Params{
        public Context context;
        public Number number;

        public Params (Context context, Number number) {
            this.context = context;
            this.number = number;
        }
    }
}
