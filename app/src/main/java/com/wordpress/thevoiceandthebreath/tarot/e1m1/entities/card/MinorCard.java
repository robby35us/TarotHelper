package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;


/*
 * MinorCard.java
 * Represents a Minor Arcana Tarot card. Minor cards differ from
 * Major Arcana cards in that they have a Rank and a Suit;
 */
@Entity(foreignKeys =
        {@ForeignKey(entity = Meaning.class, parentColumns = "id", childColumns = "uprightMeaningId"),
                @ForeignKey(entity = Meaning.class, parentColumns = "id", childColumns = "reversedMeaningId")},
        indices = {@Index(name = "minor_card_upright_ids", value = "uprightMeaningId"),
                   @Index(name = "minor_card_reversed_ids", value = "reversedMeaningId")})
@TypeConverters({Rank.class, Suit.class})
public class MinorCard extends Card {
    // private instance variables
    private Rank mRank; // (Ace, three, Page, etc.)
    private Suit mSuit; // (Wands, Cups, etc.)

    /*
     * package-private constructor.
     * Takes three arguments:
     *  - rank: an enum that tracks whether the card is an Ace, Two, Three, etc.
     *  - suit: an enum that tracks whether the suit of the card
     *  - meaning: holds all the various meanings and keywords assigned to the card
     */
    public MinorCard(Suit suit, Rank rank, String uprightId, String reversedId) {
        super(uprightId, reversedId, rank.getMode(), rank.toString().toLowerCase() + "_" +
                                                suit.toString().toLowerCase() + ".jpg");
        mRank = rank;
        mSuit = suit;
        setCardName(toString());
    }

    public MinorCard() {
    }
    /*
     * Abstract method toString. Provides a display ready name of the card
     * Args: none
     * returns: a string that is the full name of the card
     *  - For Minor Arcana, this is of the form "<rank> of <suit>"
     */
    @NonNull
    @Override
    public String toString() {
        return mRank.toString() + " of " + mSuit.toString();
    }

    public String getTitle1() {
        return mRank.toString() + " of ";
    }

    public String getTitle2() {
        return mSuit.toString();
    }


    // package private getters
    public Rank getRank() {
        return mRank;
    }

    public void setRank(Rank rank) {
        mRank = rank;
    }

    public Suit getSuit() {
        return mSuit;
    }

    public void setSuit(Suit suit) {
        mSuit = suit;
    }
}
