package com.wordpress.thevoiceandthebreath.tarot.e1m1.ui.model;

import android.arch.persistence.room.Entity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MeaningSet;

import java.util.ArrayList;
import java.util.List;

/* CoreMeaning.java
 * Contains the core meaning for a card in a single orientation(either upright or reversed)
 * A core-meaning is a short summary or gist of what a given card represents
 * Keywords or single words or short phrases that capture an aspect of the cards meaning.
 * Both core-meanings and keywords have both a default and a regular version;
 * These are one and the same out of the box, but a user is able to assign their own versions
 * of these by calling the update<blank> methods. Afterwords the get<blank> methods will return
 * the newly assigned version until restoreToDefault<blank> is called for the given meaning type.
 */
@Entity
public class MeaningModel extends BaseObservable {
    // the core-meanings, both the default and currently-in-use versions,
    @Bindable
    private String core;

    // the core-meanings, both the default and currently-in-use versions
    @Bindable
    private String keyword1;
    @Bindable
    private String keyword2;
    @Bindable
    private String keyword3;
    @Bindable
    private String keyword4;

    public MeaningModel(MeaningSet meaning) {
        core = meaning.getCoreMeaning();
        populateKeywords(meaning.getKeywords());
    }

    public void setKeywords(List<String> newKeywords) {
        populateKeywords(newKeywords);
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core){
        this.core = core;
    }

    public List<String> getKeywords() {
        return generateKeywordsList();
    }

    private List<String> generateKeywordsList() {
        List<String> keywords = new ArrayList<>();
        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);
        return keywords;
    }

    private void populateKeywords(List<String> keywords) {
        keyword1 = keywords.get(0);
        keyword2 = keywords.get(1);
        keyword3 = keywords.get(2);
        keyword4 = keywords.get(3);
    }
}