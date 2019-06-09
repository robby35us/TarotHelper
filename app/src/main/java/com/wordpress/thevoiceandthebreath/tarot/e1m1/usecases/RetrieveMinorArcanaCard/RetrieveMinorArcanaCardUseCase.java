package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMinorArcanaCard;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.TarotDeck;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.CardModel;

public class RetrieveMinorArcanaCardUseCase implements RetrieveMinorArcanaCardInPort, RetrieveMinorArcanaCardResultDAI {

    private RetrieveMinorArcanaCardOutPort outPort;

    @Override
    public void execute(Params params,
                        RetrieveMinorArcanaCardOutPort outPort,
                        RetrieveMinorArcanaCardRequestDAI requestDAI) {
        TarotDeck deck = TarotDeck.getInstance();
        MinorArcanaCard card = deck.getMinorCard(params.suit, params.rank);
        if (card != null)
            outPort.processResult(new CardModel(card));
        else {
            this.outPort = outPort;
            requestDAI.requestMinorCard(
                    new RetrieveMinorArcanaCardRequestDAI
                            .Params(params.context, params.rank, params.suit), this);
        }
    }

    @Override
    public void handleResult(MinorArcanaCard card) {
        TarotDeck.getInstance().addCard(card);
        outPort.processResult(new CardModel(card));
    }
}
