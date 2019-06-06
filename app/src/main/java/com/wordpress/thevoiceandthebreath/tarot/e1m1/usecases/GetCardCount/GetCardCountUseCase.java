package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.GetCardCount;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.callback.CardCountRetriever;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardfactory.CardFactory;

public class GetCardCountUseCase implements GetCardCountInputPort, CardCountRetriever {
    private GetCardCountOutputPort outputPort;

    @Override
    public void execute(Params params, GetCardCountOutputPort outputPort) {
        this.outputPort = outputPort;
        CardFactory.getFactory(params.getContext()).retrieveCardCount(this, params.getContext());
    }

    @Override
    public void receiveCardCountCallback(int cardCount) {
        outputPort.updateCardCount(cardCount);
    }
}
