package com.wordpress.thevoiceandthebreath.tarot.e1m1.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MajorCardWithMeanings;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.Meaning;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCard;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.entities.card.MinorCardWithMeanings;

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
    public void insertMeanings(Meaning[] meanings) {
        new InsertMeanings(meanings, database).execute();
    }

    public void insertMajorCards(MajorCard[] cards) {
        new InsertMajorCards(cards, database).execute();
    }

    public void insertMinorCards(MinorCard[] cards) {
        new InsertMinorCards(cards, database).execute();
    }


    // aggregate query statements
    public Pair<LiveData<Integer>, LiveData<Integer>> queryCardCount() {
        LiveData<Integer> majorCardCount = database.getMajorCardDao().getCardCount();
        LiveData<Integer> minorCardCount = database.getMinorCardDao().getCardCount();
        return new Pair<>(majorCardCount, minorCardCount);
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
        private Meaning[] meanings;
        private AppDatabase database;

        InsertMeanings(Meaning[] meanings, AppDatabase database) {
            this.meanings = meanings;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMeaningDao().insertAll(meanings);
            return null;
        }
    }

    private static class InsertMajorCards extends AsyncTask<Void, Void, Void> {
        private MajorCard[] majorCards;
        private AppDatabase database;

        InsertMajorCards(MajorCard[] cards, AppDatabase database) {
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
        private MinorCard[] minorCards;
        private AppDatabase database;

        InsertMinorCards(MinorCard[] cards, AppDatabase database) {
            this.minorCards = cards;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMinorCardDao().insertAll(minorCards);
            return null;
        }
    }

    // currently unused single item queries
    /*
    public LiveData<List<MinorCard>> queryMinorCard(int id) {
        return database.getMinorCardDao().getCardById(id);
    }

    public LiveData<List<MajorCard>> queryMajorCard(int id) {
        return database.getMajorCardDao().getCardById(id);
    }

    public LiveData<List<Meaning>> queryMeaning(String id) {
        return database.getMeaningDao().getMeaningById(id);
    }*/
}


