package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex;
;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MinorCardWithMeanings;

public interface FindCardByIndexOutputPort {
    void setCard(MajorCardWithMeanings majorCardWithMeanings);

    void setCard(MinorCardWithMeanings minorCardWithMeanings);
}
