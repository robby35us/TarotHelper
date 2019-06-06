package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex;

import android.content.Context;

public interface FindCardByIndexInputPort {
    void execute(Params params, FindCardByIndexOutputPort outputPort);

    class Params{
        private int index;
        private Context context;

        public Params(Context context, int index) {
            this.index = index;
            this.context = context;
        }

        public int getIndex() {
            return index;
        }

        public Context getContext() { return context; }
    }
}
