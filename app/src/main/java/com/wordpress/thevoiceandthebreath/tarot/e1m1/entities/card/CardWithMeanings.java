package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class CardWithMeanings {
    public Card card = null;

    public Meaning upright = null;

    public Meaning reversed = null;
}
