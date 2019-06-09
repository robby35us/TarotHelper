package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MeaningSet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
public class MeaningData{

    @PrimaryKey
    @NonNull
    public String id;
    public String core;

    public String keyword1;
    public String keyword2;
    public String keyword3;
    public String keyword4;

    public MeaningData() {
        id = UUID.randomUUID().toString();
    }

    public List<String> generateKeywordsList() {
        List<String> keywords = new ArrayList<>();
        keywords.add(keyword1);
        keywords.add(keyword2);
        keywords.add(keyword3);
        keywords.add(keyword4);
        return keywords;
    }

    public void populateKeywords(List<String> keywords) {
        keyword1 = keywords.get(0);
        keyword2 = keywords.get(1);
        keyword3 = keywords.get(2);
        keyword4 = keywords.get(3);
    }

    public MeaningSet getMeaningSet() {
        return new MeaningSet(core, generateKeywordsList());
    }
}