package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.accessobjects.DefaultMeaningDao;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.DefaultMeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MajorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MinorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.accessobjects.MajorCardDao;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.accessobjects.MeaningDao;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.accessobjects.MinorCardDao;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.typeconverters.NameTypeConverter;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.typeconverters.NumberTypeConverter;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.typeconverters.RankTypeConverter;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.typeconverters.SuitTypeConverter;

@Database(entities = {MajorCardData.class, MinorCardData.class, MeaningData.class, DefaultMeaningData.class}, version = 1, exportSchema = false)
@TypeConverters({NameTypeConverter.class, NumberTypeConverter.class, RankTypeConverter.class, SuitTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract MajorCardDao getMajorCardDao();
    public abstract MinorCardDao getMinorCardDao();
    public abstract MeaningDao getMeaningDao();
    public abstract DefaultMeaningDao getDefaultMeaningDao();
}
