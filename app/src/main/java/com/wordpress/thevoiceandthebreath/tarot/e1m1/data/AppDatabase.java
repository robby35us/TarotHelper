package com.wordpress.thevoiceandthebreath.tarot.e1m1.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.meaning.Meaning;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.dao.MajorCardDao;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.dao.MeaningDao;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.dao.MinorCardDao;

@Database(entities = {MajorCard.class, MinorCard.class, Meaning.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MajorCardDao getMajorCardDao();
    public abstract MinorCardDao getMinorCardDao();
    public abstract MeaningDao getMeaningDao();
}
