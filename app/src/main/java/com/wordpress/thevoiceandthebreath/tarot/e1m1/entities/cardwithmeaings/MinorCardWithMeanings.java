package com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCard;

import java.util.ArrayList;
import java.util.List;

public class MinorCardWithMeanings {
    @Embedded
    public MinorCard minorCard = null;

    @Relation(parentColumn = "uprightMeaningId",
            entityColumn = "id")
    public List<Meaning> uprightMeanings = new ArrayList<>();

    @Relation(parentColumn = "reversedMeaningId",
            entityColumn = "id")
    public List<Meaning> reversedMeanings = new ArrayList<>();
}
