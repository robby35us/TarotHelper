package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities;

import android.support.annotation.Nullable;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorArcanaCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.TarotCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;

public class TarotDeck {
    public static final int DECK_SIZE = Arcana.MAJOR_ARCANA_SIZE + Arcana.MINOR_ARCANA_SIZE;

    private static TarotDeck deck;
    private TarotCard[] cards;

    public static TarotDeck getInstance() {
        if(deck == null) {
            deck = new TarotDeck();
        }
        return deck;
    }

    private TarotDeck() {
        cards = new TarotCard[DECK_SIZE];
    }

    public void addCard(TarotCard card) {
        if(card.getArcana() == Arcana.MAJOR)
            putMajorCard((MajorArcanaCard) card);
        else
            putMinorCard((MinorArcanaCard) card);
    }

    @Nullable
    public MajorArcanaCard getMajorCard(Number number) {
        return (MajorArcanaCard) cards[number.ordinal()];
    }

    @Nullable
    public MinorArcanaCard getMinorCard(Suit suit, Rank rank) {
        int minorCardStart = Arcana.MAJOR_ARCANA_SIZE;
        int suitOffset = suit.ordinal() * Rank.NUM_RANKS;
        int rankOffset = rank.ordinal();
        return (MinorArcanaCard) cards[minorCardStart + suitOffset + rankOffset];
    }

    private void putMajorCard(MajorArcanaCard card) {
        cards[card.getNumber().ordinal()] = card;
    }

    private void putMinorCard(MinorArcanaCard card) {
        cards[computeMinorCardIndex(card)] = card;
    }

    private int computeMinorCardIndex(MinorArcanaCard card) {
        int minorCardStart = Arcana.MAJOR_ARCANA_SIZE;
        int suitOffset = card.getSuit().ordinal() * Suit.NUM_SUITS;
        int rankOffset = card.getRank().ordinal();
        return minorCardStart + suitOffset + rankOffset;
    }
}
