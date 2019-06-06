package com.wordpress.thevoiceandthebreath.tarot.e1m1.legacy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.legacy.DatabaseDescription;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TarotHelper.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_MEANING_TABLE =
                "CREATE TABLE " + DatabaseDescription.Meaning.TABLE_NAME + "(" +
                        DatabaseDescription.Meaning._ID + " integer primary key, " +
                        DatabaseDescription.Meaning.COLUMN_CORE + " TEXT, " +
                        DatabaseDescription.Meaning.COLUMN_KEYWORD_1 + " TEXT, " +
                        DatabaseDescription.Meaning.COLUMN_KEYWORD_2 + " TEXT, " +
                        DatabaseDescription.Meaning.COLUMN_KEYWORD_3 + " TEXT, " +
                        DatabaseDescription.Meaning.COLUMN_KEYWORD_4 + " TEXT);";
        db.execSQL(CREATE_MEANING_TABLE);

        final String CREATE_CARD_TABLE =
                "CREATE TABLE " + DatabaseDescription.Card.TABLE_NAME + "(" +
                        DatabaseDescription.Card._ID + " integer primary key, " +
                        DatabaseDescription.Card.COLUMN_UPRIGHT_MEANING + " integer, " +
                        DatabaseDescription.Card.COLUMN_REVERSED_MEANING + " integer, " +
                        DatabaseDescription.Card.COLUMN_IMAGE_FILENAME + " TEXT);";
        db.execSQL(CREATE_CARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
