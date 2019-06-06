package com.wordpress.thevoiceandthebreath.tarot.e1m1.legacy;
;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import com.wordpress.thevoiceandthebreath.tarot.e1m1.R;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.legacy.DatabaseDescription;
import com.wordpress.thevoiceandthebreath.tarot.e1m1.legacy.DatabaseHelper;

public class TarotHelperContentProvider extends ContentProvider {
    private DatabaseHelper dbHelper;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int ONE_MEANING = 1;
    private static final int MEANINGS = 2;
    private static final int ONE_CARD = 3;
    private static final int CARDS = 4;

    static {
        uriMatcher.addURI(DatabaseDescription.Authority, DatabaseDescription.Meaning.TABLE_NAME + "/#", ONE_MEANING);
        uriMatcher.addURI(DatabaseDescription.Authority, DatabaseDescription.Meaning.TABLE_NAME, MEANINGS);
        uriMatcher.addURI(DatabaseDescription.Authority, DatabaseDescription.Card.TABLE_NAME + "/#", ONE_CARD);
        uriMatcher.addURI(DatabaseDescription.Authority, DatabaseDescription.Card.TABLE_NAME, CARDS);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,  String sortOrder) {
        return null;
    }

    @Override
    public String getType( Uri uri) {
        return null;
    }

    @Override
    public Uri insert( Uri uri, ContentValues values) {
        Uri newUri = null;
        long rowId;
        switch (uriMatcher.match(uri)) {

            case MEANINGS:
                rowId = dbHelper.getWritableDatabase().insert(
                        DatabaseDescription.Meaning.TABLE_NAME, null, values);
                if(rowId > 0) {
                    newUri = DatabaseDescription.Meaning.buildContactUri(rowId);
                    getContext().getContentResolver().notifyChange(uri, null);
                } else
                    throw new SQLException(
                            getContext().getString(R.string.app_name) + uri);
                break;
            case CARDS:
                rowId = dbHelper.getWritableDatabase().insert(
                        DatabaseDescription.Card.TABLE_NAME, null, values);
                if(rowId > 0) {
                    newUri = DatabaseDescription.Card.buildContactUri(rowId);
                    getContext().getContentResolver().notifyChange(uri, null);
                } else
                    throw new SQLException(
                            getContext().getString(R.string.app_name) + uri);
                break;
            default:
                throw new UnsupportedOperationException(
                        getContext().getString(R.string.app_name) + uri);
        }
        return newUri;
    }

    @Override
    public int delete(Uri uri,  String selection, String[] selectionArgs) {
        int numberOfRowsDeleted;// 1 if update successful; 0 otherwise
        String id;

        switch (uriMatcher.match(uri)) {
            case ONE_MEANING:
                id = uri.getLastPathSegment();

                numberOfRowsDeleted = dbHelper.getWritableDatabase().delete(
                        DatabaseDescription.Meaning.TABLE_NAME,
                        DatabaseDescription.Meaning._ID + "=" + id, selectionArgs);
                break;
            case ONE_CARD:
                id = uri.getLastPathSegment();

                numberOfRowsDeleted = dbHelper.getWritableDatabase().delete(
                        DatabaseDescription.Card.TABLE_NAME,
                        DatabaseDescription.Card._ID + "=" + id, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(
                        getContext().getString(R.string.app_name) + uri);
        }

        if(numberOfRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsDeleted;
    }

    @Override
    public int update( Uri uri, ContentValues values,  String selection,  String[] selectionArgs) {
        int numberOfRowsUpdated; // 1 if update successful; 0 otherwise
        String id;

        switch (uriMatcher.match(uri)) {
            case ONE_MEANING:
                id = uri.getLastPathSegment();

                numberOfRowsUpdated = dbHelper.getWritableDatabase().update(
                        DatabaseDescription.Meaning.TABLE_NAME, values,
                        DatabaseDescription.Meaning._ID + "=" + id, selectionArgs);
                break;
            case ONE_CARD:
                id = uri.getLastPathSegment();

                numberOfRowsUpdated = dbHelper.getWritableDatabase().update(
                        DatabaseDescription.Card.TABLE_NAME, values,
                        DatabaseDescription.Card._ID + "=" + id, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException(
                        getContext().getString(R.string.app_name) + uri);
        }

        if(numberOfRowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsUpdated;
    }
}
