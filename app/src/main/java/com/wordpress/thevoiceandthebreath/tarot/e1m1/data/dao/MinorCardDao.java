package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCardWithMeanings;

import java.util.List;

@Dao
public interface MinorCardDao {
    @Insert
    void insertAll(MinorCard... cards);

    // Returns a double left join with the Meaning table to include the upright and reversed meanings
    @Transaction
    @Query("Select * FROM MinorCard Where id >= :lowerIndex AND id <= :upperIndex")
    LiveData<List<MinorCardWithMeanings>> getCardRangeById(int lowerIndex, int upperIndex);

    @Query("Select count(*) FROM MinorCard")
    LiveData<Integer> getCardCount();
}
