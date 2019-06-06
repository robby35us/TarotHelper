package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.Card;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;

public class CardWithMeanings {
    private Card card = null;
    private Meaning upright = null;
    private Meaning reversed = null;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Meaning getUpright() {
        return upright;
    }

    public void setUpright(Meaning upright) {
        this.upright = upright;
    }

    public Meaning getReversed() {
        return reversed;
    }

    public void setReversed(Meaning reversed) {
        this.reversed = reversed;
    }
}
