package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.GetCardCount;

import android.content.Context;

public interface GetCardCountInputPort {
    void execute(Params params, GetCardCountOutputPort outputPort);

    class Params{
        private Context context;

        public Params(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return context;
        }
    }
}
