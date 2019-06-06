package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.cardwithmeaings.MajorCardWithMeanings;

import java.util.List;

@Dao
public interface MajorCardDao {

    @Insert
    void insertAll(MajorCard... cards);

    // Returns a double left join with the Meaning table to include the upright and reversed meanings
    @Transaction
    @Query("Select * FROM MajorCard WHERE id >= :lowerIndex AND id <= :upperIndex")
    LiveData<List<MajorCardWithMeanings>> getCardRangeById(int lowerIndex, int upperIndex);

    @Query("Select count(*) FROM MajorCard")
    LiveData<Integer> getCardCount();
}
