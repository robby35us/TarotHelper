package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.FindCardByIndex;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.callback.CardRetriever;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardfactory.CardFactory;

public class FindCardByIndexUseCase implements FindCardByIndexInputPort, CardRetriever {
    private FindCardByIndexOutputPort outputPort;
    private int index;

    @Override
    public void execute(Params params, FindCardByIndexOutputPort outputPort) {
        this.outputPort = outputPort;
        index = params.getIndex();
        CardFactory.getFactory(params.getContext()).retrieveCardByIndex(index, this);
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
