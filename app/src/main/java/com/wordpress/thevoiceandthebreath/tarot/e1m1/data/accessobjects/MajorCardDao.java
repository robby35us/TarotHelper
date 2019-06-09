package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.accessobjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MajorCardData;

import java.util.List;

@Dao
public interface MajorCardDao {

    @Insert
    void insertAll(MajorCardData... cards);

    @Transaction
    @Query("SELECT * FROM MajorCardData WHERE number == :number")
    LiveData<List<MajorCardWithMeanings>> getCard(Number number);

    // Returns a double left join with the CoreMeaning table to include the upright and reversed meanings
    @Transaction
    @Query("Select * FROM MajorCardData WHERE id >= :lowerIndex AND id <= :upperIndex")
    LiveData<List<MajorCardWithMeanings>> getCardRangeById(int lowerIndex, int upperIndex);
}
