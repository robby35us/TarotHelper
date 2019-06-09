package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.accessobjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.DefaultMeaningData;

import java.util.List;

@Dao
public interface DefaultMeaningDao {

    @Insert
    void insertAll(DefaultMeaningData... meanings);

    @Query("SELECT * FROM DefaultMeaningData WHERE majorCardId == :cardId OR minorCardID == :cardId")
    LiveData<List<DefaultMeaningData>> getDefaultMeanings(int cardId);
}
