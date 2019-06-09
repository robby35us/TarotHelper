package com.wordpress.thevoiceandthebreath.tarot.e1m1.usecases.RetrieveMajorArcanaCard;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.TarotDeck;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model.CardModel;

public class RetrieveMajorArcanaCardUseCase implements RetrieveMajorArcanaCardInPort, RetrieveMajorArcanaCardResultDAI{

    private RetrieveMajorArcanaCardOutPort outPort;

    @Override
    public void execute(RetrieveMajorArcanaCardInPort.Params params,
                        RetrieveMajorArcanaCardOutPort outPort,
                        RetrieveMajorArcanaCardRequestDAI requestDAI) {
        TarotDeck deck = TarotDeck.getInstance();
        MajorArcanaCard card = deck.getMajorCard(params.number);
        if (card != null)
            outPort.processResult(new CardModel(card));
        else {
            this.outPort = outPort;
            requestDAI.requestMajorCard(
                    new RetrieveMajorArcanaCardRequestDAI.Params(params.context, params.number),
                    this);
        }
    }

    @Override
    public void handleResult(MajorArcanaCard card) {
        TarotDeck.getInstance().addCard(card);
        outPort.processResult(new CardModel(card));
    }
}
