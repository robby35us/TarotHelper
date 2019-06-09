package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardset.MinorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Mode;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

public class MinorArcanaCard extends TarotCard {
    private Suit suit;
    private Rank rank;

    public MinorArcanaCard(MinorCardKey key) {
        super(key);
        this.suit = key.getSuit();
        this.rank = key.getRank();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public Arcana getArcana() {
        return Arcana.MINOR;
    }

    @Override
    public Mode getMode() {
        return Mode.getModeFromRank(rank);
    }

    @Override
    public String getTitle() {
        return rank.toString() + " of " + suit.toString();
    }

}
