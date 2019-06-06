package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Mode;

/*
 * Card.java
 * The base class for representing a Tarot card.
 */

public abstract class Card {
    public static int nextID = 0;

    @PrimaryKey @NonNull int id;
    private String cardName;
    // private instance variables
    // private Mode mode; // what type of card (Ace, Pip, Court, Major Arcana)
    private String uprightMeaningId; // holds the various meanings and keywords for this card
    private String reversedMeaningId;
    private String fileName;// the filename for the card image asset

    /*
     * Public constructor
     * Takes three arguments:
     *  - meaning: tracks all the meanings and keywords for this card
     *  - mode: what type of card(ace, pip, court, etc). Used for sorting/tagging
     *  - fileName: the name of the card image asset associated with this card
     */
    public Card(String uprightMeaningId, String reversedMeaningId, Mode mode, String fileName) {
        this();
        this.uprightMeaningId = uprightMeaningId;
        this.reversedMeaningId = reversedMeaningId;
     //   this.mode = mode;
        this.fileName = fileName;
        cardName = "";
    }

    public Card() {
        id = nextID;
        nextID++;
    }

    /*
     * Abstract method toString. Provides a display ready name of the card
     * Args: none
     * returns: a string that is the full name of the card
     */
    @NonNull
    @Override
    public abstract String toString();
    abstract public String getTitle1();
    abstract public String getTitle2();

    // package-private getters

    // public getters
    public String getUprightMeaningId() {
        return uprightMeaningId;
    }

    public String getReversedMeaningId() {
        return reversedMeaningId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public void setUprightMeaningId(String uprightMeaningId) {
        this.uprightMeaningId = uprightMeaningId;
    }

    public void setReversedMeaningId(String reversedMeaningId) {
        this.reversedMeaningId = reversedMeaningId;
    }

    @NonNull
    public String getCardName() {
        return cardName;
    }

    public void setCardName(@NonNull String cardName) {
        this.cardName = cardName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
