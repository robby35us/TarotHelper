package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCardWithMeanings;

import java.util.List;

@Dao
public interface MajorCardDao {
    @Insert
    void insert(MajorCard card);

    @Insert
    void insertAll(MajorCard... cards);

    @Query("Select * FROM MajorCard WHERE id IS :id")
    LiveData<List<MajorCard>> getCardById(int id);

    @Transaction
    @Query("Select * FROM MajorCard WHERE id >= :lowerIndex AND id <= :upperIndex")
    LiveData<List<MajorCardWithMeanings>> getCardRangeById(int lowerIndex, int upperIndex);

    @Query("Select count(*) FROM MajorCard")
    LiveData<Integer> getCardCount();
}
