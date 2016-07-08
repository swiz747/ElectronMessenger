package com.tritiumlabs.arthur.servertest;
/**
 * Created by Kyle on 7/8/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    //Constants for db name and version
    private static final String DATABASE_NAME = "chats.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_CHATS = "chats";
    public static final String CHAT_ID = "_id";
    public static final String CHAT_SENDER = "chatSender";
    public static final String CHAT_RECEIVER = "chatReceiver";
    public static final String CHAT_TEXT = "chatText";
    public static final String CHAT_CREATED = "chatCreated";

    public static final String[] ALL_COLUMNS =
            {CHAT_ID, CHAT_TEXT, CHAT_CREATED};

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CHATS + " (" +
                    CHAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CHAT_SENDER + "TEXT, " +
                    CHAT_RECEIVER + "TEXT, " +
                    CHAT_TEXT + " TEXT, " +
                    CHAT_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHATS);
        onCreate(db);
    }
}