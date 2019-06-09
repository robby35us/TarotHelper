package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings;

import android.arch.persistence.room.Ignore;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;

public abstract class CardWithMeanings {

    @Ignore
    public Arcana arcana;
}
