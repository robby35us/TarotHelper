package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.accessobjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MinorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.MinorCardWithMeanings;

import java.util.List;

@Dao
public interface MinorCardDao {
    @Insert
    void insertAll(MinorCardData... cards);

    @Transaction
    @Query("SELECT * FROM MinorCardData WHERE suit == :suit AND rank == :rank")
    LiveData<List<MinorCardWithMeanings>> getCard(Suit suit, Rank rank);

    // Returns a double left join with the CoreMeaning table to include the upright and reversed meanings
    @Transaction
    @Query("Select * FROM MinorCardData Where id >= :lowerIndex AND id <= :upperIndex")
    LiveData<List<MinorCardWithMeanings>> getCardRangeById(int lowerIndex, int upperIndex);
}
