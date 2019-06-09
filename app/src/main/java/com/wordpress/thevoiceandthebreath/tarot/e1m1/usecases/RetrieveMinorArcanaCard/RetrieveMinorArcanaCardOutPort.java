package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.CardModel;

public interface RetrieveMinorArcanaCardOutPort {
    void processResult(CardModel card);
}
