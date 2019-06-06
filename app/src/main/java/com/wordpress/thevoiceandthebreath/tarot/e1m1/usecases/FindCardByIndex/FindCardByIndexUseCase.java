package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.CardBuffer;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.callback.CardRetriever;

public class FindCardByIndexUseCase implements FindCardByIndexInputPort, CardRetriever {
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
