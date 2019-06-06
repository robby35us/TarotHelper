package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.Meaning;

import java.util.List;

@Dao
public interface MeaningDao {
    @Insert
    void insert(Meaning meaning);

    @Transaction
    @Insert
    void insertAll(Meaning... meanings);

    @Query("Select * FROM Meaning WHERE id = :id")
    LiveData<List<Meaning>> getMeaningById(String id);
}
