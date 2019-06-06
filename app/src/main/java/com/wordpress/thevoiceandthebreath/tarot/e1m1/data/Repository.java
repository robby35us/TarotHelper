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
    private String DB_NAME = "db_tarothelper";

    private Context context;
    private AppDatabase database;
    private static Repository repository;

    public static Repository getRepository(Context context) {
        if(repository == null) {
            repository = new Repository(context);
        } else {
            repository.context = context;
        }
        return repository;
    }

   private Repository(Context context) {
        if(database == null) {
            database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
        }
    }



    public void insertMinorCard(MinorCard card) {
        new InsertMinorCard(card, database).execute();
    }

    public LiveData<List<MinorCard>> queryMinorCard(int id) {
        return database.getMinorCardDao().getCardById(id);
    }

    public LiveData<List<MinorCardWithMeanings>> queryMinorCards(int lowerIndex, int upperIndex) {
        return database.getMinorCardDao().getCardRangeById(lowerIndex, upperIndex);
    }

    public LiveData<List<MajorCardWithMeanings>> queryMajorCards(int lowerIndex, int upperIndex) {
        return database.getMajorCardDao().getCardRangeById(lowerIndex, upperIndex);
    }

    public void insertMajorCard(MajorCard card) {
        new InsertMajorCard(card, database).execute();
    }

    public LiveData<List<MajorCard>> queryMajorCard(int id) {
        return database.getMajorCardDao().getCardById(id);
    }


    public String insertMeaning(Meaning meaning) {
        new InsertMeaning(meaning, database).execute();
        return meaning.id;
    }

    public LiveData<List<Meaning>> queryMeaning(String id) {
        return database.getMeaningDao().getMeaningById(id);
    }

    public Pair<LiveData<Integer>, LiveData<Integer>> queryCardCount() {
        LiveData<Integer> majorCardCount = database.getMajorCardDao().getCardCount();
        LiveData<Integer> minorCardCount = database.getMinorCardDao().getCardCount();
        return new Pair<>(majorCardCount, minorCardCount);
    }

    static class InsertMajorCard extends AsyncTask<Void, Void, Void> {
        private MajorCard card;
        private AppDatabase database;

        InsertMajorCard(MajorCard card, AppDatabase database) {
            this.card = card;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMajorCardDao().insert(card);
            return null;
        }
    }

    static class InsertMinorCard extends AsyncTask<Void, Void, Void> {
        private MinorCard card;
        private AppDatabase database;

        InsertMinorCard(MinorCard card, AppDatabase database) {
            this.card = card;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMinorCardDao().insert(card);
            return null;
        }
    }

   static class InsertAllCards extends AsyncTask<Void, Void, Void> {
        private MajorCard[] cards;
        private AppDatabase database;

        InsertAllCards(MajorCard[] cards, AppDatabase database) {
            this.cards = cards;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMajorCardDao().insertAll(cards);
            return null;
        }
    }
    static class InsertMeaning extends AsyncTask<Void, Void, Void> {
        private Meaning meaning;
        private AppDatabase database;

        InsertMeaning(Meaning meaning, AppDatabase database) {
            this.meaning = meaning;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMeaningDao().insert(meaning);
            return null;
        }
    }

    static class InsertAllMeanings extends AsyncTask<Void, Void, Void> {
        private Meaning[] meanings;
        private AppDatabase database;

        InsertAllMeanings(Meaning[] meanings, AppDatabase database) {
            this.meanings = meanings;
            this.database = database;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.getMeaningDao().insertAll(meanings);
            return null;
        }
    }
}


