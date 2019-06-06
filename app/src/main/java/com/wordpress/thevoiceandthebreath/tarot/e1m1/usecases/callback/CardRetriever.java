package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.callback;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCardWithMeanings;

public interface CardRetriever {
    void receiveCard(MajorCardWithMeanings cardWithMeanings);

    void receiveCard(MinorCardWithMeanings minorCardWithMeanings);
}
