package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class CardData {
    private static int nextID = 0;

    @PrimaryKey
    public int id;
    // private instance variables
    // private Mode mode; // what type of card (Ace, Pip, Court, Major Arcana)
    public String uprightMeaningId; // holds the various meanings and keywords for this card
    public String reversedMeaningId;
    public String fileName;// the filename for the card image asset

    /*
     * Public constructor
     * Takes three arguments:
     *  - meaning: tracks all the meanings and keywords for this card
     *  - mode: what type of card(ace, pip, court, etc). Used for sorting/tagging
     *  - fileName: the name of the card image asset associated with this card
     */
    public CardData(String uprightMeaningId, String reversedMeaningId, String fileName) {
        this();
        this.uprightMeaningId = uprightMeaningId;
        this.reversedMeaningId = reversedMeaningId;
        this.fileName = fileName;
    }

    public CardData() {
        id = nextID;
        nextID++;
    }
}
