package com.wordpress.thevoiceandthebreath.tarot.e1m1.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Number;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Rank;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.definitions.Suit;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.DefaultMeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MajorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.meaning.MeaningData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.MinorCardData;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.data.models.card.withmeanings.MinorCardWithMeanings;

import java.util.List;

public class Repository {
    private static final String DB_NAME = "db_tarot_helper";

    private static Repository repository;

    private AppDatabase database;

    public static Repository getRepository(Context context) {
        if (repository == null) {
            repository = new Repository(context);
        }
        return repository;
    }

    private Repository(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
        }
    }


    // Insert statements
    public void insertMeanings(MeaningData[] meanings) {
        new InsertMeanings(meanings, database).execute();
    }

    public void insertMajorCards(MajorCardData[] cards) {
        new InsertMajorCards(cards, database).execute();
    }

    public void insertMinorCards(MinorCardData[] cards) {
        new InsertMinorCards(cards, database).execute();
    }

    public LiveData<List<MajorCardWithMeanings>> queryMajorCard(Number number) {
        return database.getMajorCardDao().getCard(number);
    }

    public LiveData<List<MinorCardWithMeanings>> queryMinorCard(Suit suit, Rank rank) {
        return database.getMinorCardDao().getCard(suit,rank);
    }

    // query statements
    public LiveData<List<MajorCardWithMeanings>> queryMajorCards(int lowerIndex, int upperIndex) {
        return database.getMajorCardDao().getCardRangeById(lowerIndex, upperIndex);
    }

    public LiveData<List<MinorCardWithMeanings>> queryMinorCards(int lowerIndex, int upperIndex) {
        return database.getMinorCardDao().getCardRangeById(lowerIndex, upperIndex);
    }


    // wrapper classes for performing asynchronous insert operations
    private static class InsertMeanings extends AsyncTask<Void, Void, Void> {
        private MeaningData[] meanings;
        private AppDatabase database;

        InsertMeanings(MeaningData[] meanings, AppDatabase database) {
            this.meanings = meanings;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMeaningDao().insertAll(meanings);
            return null;
        }
    }

    private static class InsertDefaultMeanings extends AsyncTask<Void, Void, Void> {
        private DefaultMeaningData[] defaultMeanings;
        private AppDatabase database;

        InsertDefaultMeanings(DefaultMeaningData[] defaultMeanings, AppDatabase database) {
            this.defaultMeanings = defaultMeanings;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getDefaultMeaningDao().insertAll(defaultMeanings);
            return null;
        }
    }

    private static class InsertMajorCards extends AsyncTask<Void, Void, Void> {
        private MajorCardData[] majorCards;
        private AppDatabase database;

        InsertMajorCards(MajorCardData[] cards, AppDatabase database) {
            this.majorCards = cards;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMajorCardDao().insertAll(majorCards);
            return null;
        }
    }

    private static class InsertMinorCards extends AsyncTask<Void, Void, Void> {
        private MinorCardData[] minorCards;
        private AppDatabase database;

        InsertMinorCards(MinorCardData[] cards, AppDatabase database) {
            this.minorCards = cards;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMinorCardDao().insertAll(minorCards);
            return null;
        }
    }
}

