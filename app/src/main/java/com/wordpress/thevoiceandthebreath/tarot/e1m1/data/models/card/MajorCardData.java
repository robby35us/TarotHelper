package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardset.MajorCardKey;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Name;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;

@Entity(foreignKeys =
        {@ForeignKey(entity = MeaningData.class, parentColumns = "id", childColumns = "uprightMeaningId"),
                @ForeignKey(entity = MeaningData.class, parentColumns = "id", childColumns = "reversedMeaningId")},
        indices = {@Index(name = "major_card_upright_ids", value = "uprightMeaningId"),
                   @Index(name = "major_card_reversed_ids", value = "reversedMeaningId")})
public class MajorCardData extends CardData {

    public Number number;
    public Name name;

    public MajorCardData(MajorCardKey key, String uprightId, String reversedId) {
        super(uprightId, reversedId, key.getName()
                                                  .toString()
                                                  .toLowerCase()
                                                  .replace(" ", "_") + ".jpg");
        this.number = key.getNumber();
        this.name = key.getName();
    }

    public MajorCardData() {
    }
}
