package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.Meaning;

@Dao
public interface MeaningDao {
    @Insert
    void insertAll(Meaning... meanings);
}
