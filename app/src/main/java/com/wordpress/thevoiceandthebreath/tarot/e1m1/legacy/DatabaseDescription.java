package com.wordpress.thevoiceandthebreath.tarot.e1m1.legacy;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseDescription {

    public static final String Authority = "com.wordpress.thevoiceandthebreath.tarot.e1m1.data";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + Authority);

    public static final class Meaning implements BaseColumns {
        public static final String TABLE_NAME = "meaning";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // column names for contacts tables's columns
        public static final String COLUMN_CORE = "core";
        public static final String COLUMN_KEYWORD_1 = "keyword1";
        public static final String COLUMN_KEYWORD_2 = "keyword2";
        public static final String COLUMN_KEYWORD_3 = "keyword3";
        public static final String COLUMN_KEYWORD_4 = "keyword4";

        public static Uri buildContactUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class Card implements BaseColumns {
        public static final String TABLE_NAME = "card";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // column names for contacts tables's columns
        public static final String COLUMN_IMAGE_FILENAME = "filename";
        public static final String COLUMN_UPRIGHT_MEANING = "upright";
        public static final String COLUMN_REVERSED_MEANING = "reversed";
        public static Uri buildContactUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
