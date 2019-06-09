package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardset;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;

public abstract class CardKey {
    private Arcana arcana;

    public CardKey(Arcana arcana) {
        this.arcana = arcana;
    }

    public Arcana getArcana() {
        return arcana;
    }
}
