package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.accessobjects;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;

@Dao
public interface MeaningDao {
    @Insert
    void insertAll(MeaningData... meanings);

}
