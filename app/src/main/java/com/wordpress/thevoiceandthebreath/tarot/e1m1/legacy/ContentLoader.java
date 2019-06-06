package com.wordpress.thevoiceandthebreath.tarot.e1m1.legacy;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.legacy.DatabaseDescription;

public class ContentLoader {
    public static void loadContent(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseDescription.Meaning.COLUMN_CORE, "The moment before the first step is taken");
        contentValues.put(DatabaseDescription.Meaning.COLUMN_KEYWORD_1, "Beginnings");
        contentValues.put(DatabaseDescription.Meaning.COLUMN_KEYWORD_2, "Innocence");
        contentValues.put(DatabaseDescription.Meaning.COLUMN_KEYWORD_3, "Clear Conscience");
        contentValues.put(DatabaseDescription.Meaning.COLUMN_KEYWORD_4, "Setting Out");
        String upright_id = context.getContentResolver().insert(DatabaseDescription.Meaning.CONTENT_URI, contentValues).getLastPathSegment();

        contentValues.clear();
        contentValues.put(DatabaseDescription.Meaning.COLUMN_CORE, "Need for planning before an action");
        contentValues.put(DatabaseDescription.Meaning.COLUMN_KEYWORD_1, "Vanity");
        contentValues.put(DatabaseDescription.Meaning.COLUMN_KEYWORD_2, "Foolishness");
        contentValues.put(DatabaseDescription.Meaning.COLUMN_KEYWORD_3, "Indiscretion");
        contentValues.put(DatabaseDescription.Meaning.COLUMN_KEYWORD_4, "Inanity");
        String reversed_id = context.getContentResolver().insert(DatabaseDescription.Meaning.CONTENT_URI, contentValues).getLastPathSegment();

        contentValues.clear();
        contentValues.put(DatabaseDescription.Card.COLUMN_IMAGE_FILENAME, "the_fool.jpg");
        contentValues.put(DatabaseDescription.Card.COLUMN_UPRIGHT_MEANING, Integer.parseInt(upright_id));
        contentValues.put(DatabaseDescription.Card.COLUMN_REVERSED_MEANING, Integer.parseInt(reversed_id));
        Uri cardUri = context.getContentResolver().insert(DatabaseDescription.Card.CONTENT_URI, contentValues);
    }
}
