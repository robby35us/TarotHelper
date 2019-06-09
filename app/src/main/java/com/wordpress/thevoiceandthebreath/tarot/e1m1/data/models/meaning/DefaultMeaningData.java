package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MajorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MinorCardData;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = MajorCardData.class, parentColumns = {"id"}, childColumns = {"majorCardId"}),
                       @ForeignKey(entity = MinorCardData.class, parentColumns = {"id"}, childColumns = {"minorCardId"})},
        indices = {@Index(value = {"majorCardId"}, name = "MajorCardIndex"),
                   @Index(value = {"minorCardId"}, name = "MinorCardIndex")})
public class DefaultMeaningData {

    @PrimaryKey
    @NonNull
    public String id;
    public int majorCardId;
    public int minorCardId;
    public boolean reversed;

    public String core;

    public String keyword1;
    public String keyword2;
    public String keyword3;
    public String keyword4;

    public DefaultMeaningData() {
        id = UUID.randomUUID().toString();
    }

    public String[] generateKeywordsList() {
        return new String[]{keyword1, keyword2, keyword3, keyword4};
    }

    public void populateKeywords(String[] keywords) {
        keyword1 = keywords[0];
        keyword2 = keywords[1];
        keyword3 = keywords[2];
        keyword4 = keywords[3];
    }
}
