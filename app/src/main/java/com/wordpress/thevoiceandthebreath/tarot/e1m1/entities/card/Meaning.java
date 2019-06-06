package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import java.util.UUID;

/* Meaning.java
 * Contains the core meaning for a card in a single orientation(either upright or reversed)
 * A core-meaning is a short summary or gist of what a given card represents
 * Keywords or single words or short phrases that capture an aspect of the cards meaning.
 * Both core-meanings and keywords have both a default and a regular version;
 * These are one and the same out of the box, but a user is able to assign their own versions
 * of these by calling the update<blank> methods. Afterwords the get<blank> methods will return
 * the newly assigned version until restoreToDefault<blank> is called for the given meaning type.
 */
@Entity
public class Meaning extends BaseObservable {

    @PrimaryKey
    @NonNull public String id;
    // the core-meanings, both the default and currently-in-use versions,
    @Bindable
    public String core;
    public String defaultCore;

    // the core-meanings, both the default and currently-in-use versions
    @Bindable
    public String keyword1;
    @Bindable
    public String keyword2;
    @Bindable
    public String keyword3;
    @Bindable
    public String keyword4;

    public String defaultKeyword1;
    public String defaultKeyword2;
    public String defaultKeyword3;
    public String defaultKeyword4;

    /* the default constructor
     * takes a string which represents the core-meaning,
     * and a String array of keywords/phrases
     */

    public Meaning(){
        id = UUID.randomUUID().toString();
    }

    public Meaning (String core, String[] keywords) {
        this();
        this.core = core;
        defaultCore = core;
        populateKeywords(keywords);
        populateDefaultKeywords(keywords);
    }

    /*
     *
     * sets a new core meaning to replace the default-core assigned to the card out of the box
     * this will be the new meaning returned by getCore() until restoreToDefaultCore() is called
     */
    public void setCore(String newCore) {
        core = newCore;
    }

    /*
     * sets the core-meaning back to default, out of the box-settings.
     * the old values will not be saved
     */
    public void restoreToDefaultCore() {
        core = defaultCore;
    }

    /*
     * sets a new set of keywords to replace the default set of keywords assigned out-of-the-box
     * this will be the new set of keywords returned by updateKeywords until
     * restoreToDefaultKeywords is called.
     */
    public void updateKeywords(String[] newKeywords) {
        populateKeywords(newKeywords);
    }

    /*
     * Sets the set of keywords back to the out-of-the-box settings,
     * the previous values will not be saved
     */
    public void restoreToDefaultKeywords() {
        populateKeywords(generateDefaultKeywordsList());
    }


    /*
     * returns the last core-meaning assigned from either setDefaultCore() or updateCore()
     */
    public String getCore() {
        return core;
    }

    /*
     * returns the last set of keywords assigned from either
     * setDefaultKeywords() or updateKeywords()
     */
    public String[] getKeywords() {
        return generateKeywordsList();
    }

    private String[] generateKeywordsList() {
        return new String[]{keyword1,keyword2,keyword3,keyword4};
    }

    private String[] generateDefaultKeywordsList() {
        return new String[]{defaultKeyword1, defaultKeyword2, defaultKeyword3, defaultKeyword4};
    }

    private void populateKeywords(String[] keywords) {
        keyword1 = keywords[0];
        keyword2 = keywords[1];
        keyword3 = keywords[2];
        keyword4 = keywords[3];
    }

    private void populateDefaultKeywords(String[] keywords) {
        defaultKeyword1 = keywords[0];
        defaultKeyword2 = keywords[1];
        defaultKeyword3 = keywords[2];
        defaultKeyword4 = keywords[3];
    }
}