package com.tritiumlabs.arthur.servertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ChatsProvider extends ContentProvider{

    private static final String AUTHORITY = "com.tritiumlabs.arthur.servertest.ChatsProvider";
    private static final String BASE_PATH = "chats";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );

    // Constant to identify the requested operation
    private static final int CHATS = 1;
    private static final int CHATS_ID = 2;

    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    public static final String CONTENT_ITEM_TYPE = "Chat";

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, CHATS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH +  "/#", CHATS_ID);
    }

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {

        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        if (uriMatcher.match(uri) == CHATS_ID) {
            selection = DBOpenHelper.CHAT_ID + "=" + uri.getLastPathSegment();
        }

        return database.query(DBOpenHelper.TABLE_CHATS, DBOpenHelper.ALL_COLUMNS,
                selection, null, null, null,
                DBOpenHelper.CHAT_CREATED + " DESC");
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert(DBOpenHelper.TABLE_CHATS,
                null, values);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete(DBOpenHelper.TABLE_CHATS, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update(DBOpenHelper.TABLE_CHATS,
                values, selection, selectionArgs);
    }
}
