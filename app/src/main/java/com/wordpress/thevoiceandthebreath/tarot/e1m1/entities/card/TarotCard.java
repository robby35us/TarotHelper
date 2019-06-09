package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset.CardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Mode;

public abstract class TarotCard {
    private CardKey key;
    private MeaningSet uprightMeanings;
    private MeaningSet reversedMeanings;

    public TarotCard(CardKey key) {
        this.key = key;
    }

    public void setUprightMeanings(MeaningSet uprightMeanings){
        this.uprightMeanings = uprightMeanings;
    }

    public void setReversedMeanings(MeaningSet reversedMeanings) {
        this.reversedMeanings = reversedMeanings;
    }

    public CardKey getKey() {
        return key;
    }

    public MeaningSet getUprightMeanings() {
        return uprightMeanings;
    }

    public MeaningSet getReversedMeanings() {
        return reversedMeanings;
    }

    abstract public Arcana getArcana();
    abstract public Mode getMode();

    abstract public String getTitle();
}
