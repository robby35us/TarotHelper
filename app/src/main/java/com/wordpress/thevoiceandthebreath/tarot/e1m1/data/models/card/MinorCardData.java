package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardset.MinorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;


/*
 * MinorCardData.java
 * Represents a Minor Arcana Tarot card. Minor cards differ from
 * Major Arcana cards in that they have a Rank and a Suit;
 */
@Entity(foreignKeys =
        {@ForeignKey(entity = MeaningData.class, parentColumns = "id", childColumns = "uprightMeaningId"),
                @ForeignKey(entity = MeaningData.class, parentColumns = "id", childColumns = "reversedMeaningId")},
        indices = {@Index(name = "minor_card_upright_ids", value = "uprightMeaningId"),
                   @Index(name = "minor_card_reversed_ids", value = "reversedMeaningId")})
public class MinorCardData extends CardData {
    public Suit suit;
    public Rank rank;

    /*
     * package-private constructor.
     * Takes three arguments:
     *  - rank: an enum that tracks whether the card is an Ace, Two, Three, etc.
     *  - suit: an enum that tracks whether the suit of the card
     *  - meaning: holds all the various meanings and keywords assigned to the card
     */
    public MinorCardData(MinorCardKey key, String uprightId, String reversedId) {
        super(uprightId, reversedId, key.getRank().toString().toLowerCase() + "_" +
                                              key.getSuit().toString().toLowerCase() + ".jpg");
        this.rank = key.getRank();
        this.suit = key.getSuit();
    }

    public MinorCardData() {
    }
}
