package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.LoadCard;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.TarotDeck;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.TarotCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.CardModel;

public class LoadCardUseCase implements LoadCard.InPort, LoadCard.ResultDAI {

    private LoadCard.OutPort outPort;

    @Override
    public void execute(LoadCard.Params params,
                        LoadCard.OutPort outPort,
                        LoadCard.RequestDAI requestDAI) {
        TarotDeck deck = TarotDeck.getInstance();
        TarotCard card = deck.getCard(params.key);
        if (card != null)
            outPort.processResult(new CardModel(card));
        else {
            this.outPort = outPort;
            requestDAI.requestCard(params, this);
        }
    }

    @Override
    public void handleResult(TarotCard card) {
        TarotDeck.getInstance().addCard(card);
        outPort.processResult(new CardModel(card));
    }
}
