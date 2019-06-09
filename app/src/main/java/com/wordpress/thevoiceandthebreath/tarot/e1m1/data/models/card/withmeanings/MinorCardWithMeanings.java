package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MinorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Arcana;

import java.util.List;

public class MinorCardWithMeanings extends CardWithMeanings{

    public MinorCardWithMeanings() {
        arcana = Arcana.MINOR;
    }

    @Embedded
    public MinorCardData minorCard = null;

    @Relation(parentColumn = "uprightMeaningId",
            entityColumn = "id")
    public List<MeaningData> uprightMeanings;

    @Relation(parentColumn = "reversedMeaningId",
            entityColumn = "id")
    public List<MeaningData> reversedMeanings;
}
