package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Mode;

public abstract class TarotCard {
    private MeaningSet uprightMeanings;
    private MeaningSet reversedMeanings;

    public void setUprightMeanings(MeaningSet uprightMeanings){
        this.uprightMeanings = uprightMeanings;
    }

    public void setReversedMeanings(MeaningSet reversedMeanings) {
        this.reversedMeanings = reversedMeanings;
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
