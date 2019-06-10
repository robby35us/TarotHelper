package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.keyset;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

public class MinorCardKey extends CardKey {
    private Suit suit;
    private Rank rank;

    public MinorCardKey(Suit suit, Rank rank) {
        super(Arcana.MINOR);
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public CardKey copyKey() {
        return new MinorCardKey(suit, rank);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == MinorCardKey.class)
            return this.suit == ((MinorCardKey) obj).getSuit() &&
                   this.rank == ((MinorCardKey) obj).getRank();
        return false;
    }
}
