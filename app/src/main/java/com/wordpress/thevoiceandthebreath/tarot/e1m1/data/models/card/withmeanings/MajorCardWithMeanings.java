package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MajorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;

import java.util.ArrayList;
import java.util.List;

public class MajorCardWithMeanings extends CardWithMeanings {

    public MajorCardWithMeanings () {
        arcana = Arcana.MAJOR;
    }

    @Embedded
    public MajorCardData majorCard = null;

    @Relation(parentColumn = "uprightMeaningId",
            entityColumn = "id")
    public List<MeaningData> uprightMeanings = new ArrayList<>();

    @Relation(parentColumn = "reversedMeaningId",
            entityColumn = "id")
    public List<MeaningData> reversedMeanings = new ArrayList<>();
}
