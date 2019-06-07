package com.wordpress.thevoiceandthebreath.tarot.e1m1.interactors;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.boundries.ui.FindCardByIndexInputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.boundries.ui.FindCardByIndexOutputPort;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.CardBuffer;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.interactors.callback.CardRetriever;

public class FindCardByIndexInteractor implements FindCardByIndexInputPort, CardRetriever {
    private FindCardByIndexOutputPort outputPort;

    @Override
    public void execute(Params params, FindCardByIndexOutputPort outputPort) {
        this.outputPort = outputPort;
        CardBuffer buffer = CardBuffer.getCardBuffer(params.getContext());
        buffer.retrieveCardByIndex(params.getIndex(), this, params.getContext());
    }

    @Override
    public void receiveCard(MajorCardWithMeanings cardWithMeanings)   {
        outputPort.setCard(cardWithMeanings);
    }

    @Override
    public void receiveCard(MinorCardWithMeanings cardWithMeanings) {
        outputPort.setCard(cardWithMeanings);
    }
}
