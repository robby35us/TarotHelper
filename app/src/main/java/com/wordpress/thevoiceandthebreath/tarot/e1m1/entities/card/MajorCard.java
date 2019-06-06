package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Mode;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Name;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;

/*
 * MajorCard.java
 * Represents a Major Arcana tarot card. Major arcana differ from Minor Arcana:
 * in that they don't have a rank and suit, but instead have a name and number;
 */

@Entity(foreignKeys =
        {@ForeignKey(entity = Meaning.class, parentColumns = "id", childColumns = "uprightMeaningId"),
                @ForeignKey(entity = Meaning.class, parentColumns = "id", childColumns = "reversedMeaningId")},
        indices = {@Index(name = "major_card_upright_ids", value = "uprightMeaningId"),
                   @Index(name = "major_card_reversed_ids", value = "reversedMeaningId")})
@TypeConverters({Number.class, Name.class})
public class MajorCard extends Card {
    // private instance variables

    private Number mNumber; // the roman numeral of the card
    private Name mName; // the name portion of the title, without the roman numeral

    /*
     * Package-Private constructor
     * Arguments: 2
     *  - name: an enum representing the name of the card. Also holds the number of the card
     *  - meaning: holds the various meanings and keywords for the given card
     */
    public MajorCard(Number number, String uprightId, String reversedId) {
        super(uprightId, reversedId ,Mode.MAJOR,
                number.getMatchingName().toString().toLowerCase().replace(" ", "_") + ".jpg");
        mNumber = number;
        mName = number.getMatchingName();
        setCardName(toString());
    }

    public MajorCard() {
    }

    /*
     * Abstract method toString. Provides a display ready name of the card
     * Args: none
     * returns: a string that is the full name of the card
     *  - For Major Arcana: of the form "<Roman Numeral>: <Name>"
     */
    @NonNull
    @Override
    public String toString() {
        return mNumber.toString() + ": " + mName.toString();
    }

    public String getTitle1() {
        String nameFirstWord = mName.toString().split(" ")[0];
        if(nameFirstWord.equals("The") || nameFirstWord.equals("Wheel")) {
            return mNumber.toString() + ": " + nameFirstWord + " ";
        } else {
            return mNumber.toString() + ": ";
        }
    }

    public String getTitle2() {
        String[] split = mName.toString().split(" ");
        String nameFirstWord = split[0];
        if(nameFirstWord.equals("The") || nameFirstWord.equals("Wheel")) {
            String result = split [1];
            if(split.length > 2)
                result += " " + split[2];
            return result;
        } else
            return nameFirstWord;
    }

    // public getters
    /*
     * Note: This method does not return a display ready name.
     * Instead, it returns an Enum representing the card name
     */
    public Name getName(){
        return mName;
    }

    public void setNumber(Number mNumber) {
        this.mNumber = mNumber;
    }

    public void setName(Name mName) {
        this.mName = mName;
    }

    // package-private getters
    public Number getNumber() {
        return mNumber;
    }

}
