package com.tritiumlabs.arthur.servertest;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocalDBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    //TODO: Change Name to be relevant -AB
    private static final String DATABASE_NAME = "RoundAbout";
    //  table names
    private static final String TABLE_MESSAGES = "messages";
    private static final String TABLE_SETTINGS = "settings";
    // messages table columns
    private static final String KEY_MSG_ID = "messages_id";
    private static final String KEY_MSG_CHAT_ID = "messages_chat_id";
    private static final String KEY_MSG_SENDER = "messages_sender";
    private static final String KEY_MSG_RECIEVER = "messages_reciever";
    private static final String KEY_MSG_BODY = "messages_body";
    private static final String KEY_MSG_SENTTIME = "messages_sent";
    private static final String KEY_MSG_RECVTIME = "messages_recv";
    // settings table columns
    private static final String KEY_SET_ID = "settings_id";
    private static final String KEY_SET_layout = "settings_layout";






    public LocalDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create message table
        String CREATE_MESSAGES_TABLE = "CREATE TABLE " + TABLE_MESSAGES + "("
                + KEY_MSG_ID + " INTEGER PRIMARY KEY," + KEY_MSG_CHAT_ID + " INTEGER,"
                + KEY_MSG_SENDER + " VARCHAR," + KEY_MSG_BODY + " TEXT,"
                + KEY_MSG_SENTTIME + " DATETIME,"+ KEY_MSG_RECVTIME + " DATETIME"+ ")";

        //create setting table
        /**String CREATE_SETTINGS_TABLE = "CREATE TABLE " + TABLE_SETTINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SH_ADDR + " TEXT" + ")";
         */
        db.execSQL(CREATE_MESSAGES_TABLE);
        //db.execSQL(CREATE_SETTINGS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //TODO: i dont know if i like this, if we ever upgrade we will wipe all user data -AB
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        // Creating tables again
        onCreate(db);
    }

    //add new Message -AB
    public void addMessage(ChatMessage msg)
    {
        Log.e("LocalDBHandler", "adding message to DB");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MSG_CHAT_ID, msg.getChatID());
        values.put(KEY_MSG_SENDER, msg.getSender());
        values.put(KEY_MSG_RECIEVER, msg.getReceiver());
        values.put(KEY_MSG_BODY, msg.getBody());
        values.put(KEY_MSG_SENTTIME, msg.getSentTime());
        values.put(KEY_MSG_RECVTIME, msg.getRecvTime());

        // Inserting Row
        db.insert(TABLE_MESSAGES, null, values);
        db.close(); // Closing database connection
    }
    // TODO: should we have deletable messages? -AB
    public void deleteMessage(ChatMessage msg)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MESSAGES + " WHERE " + KEY_MSG_ID + " = " + msg.getMsgID());
        db.close(); // Closing database connection
    }

    public  List<ChatMessage> getChatMessages(long chat_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ChatMessage> messages = new ArrayList<ChatMessage>();

        String selectQuery = "SELECT * FROM " + TABLE_MESSAGES + " WHERE "
                + KEY_MSG_CHAT_ID + " = " + chat_id;

        Log.e("LocalDBHandler", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                do {
                    ChatMessage msg = new ChatMessage();
                    msg.setMsgID(c.getInt((c.getColumnIndex(KEY_MSG_ID))));
                    msg.setChatID(c.getInt((c.getColumnIndex(KEY_MSG_CHAT_ID))));
                    msg.setBody((c.getString(c.getColumnIndex(KEY_MSG_BODY))));
                    msg.setSentTime(c.getString(c.getColumnIndex(KEY_MSG_SENTTIME)));
                    msg.setRecvTime(c.getString(c.getColumnIndex(KEY_MSG_RECVTIME)));

                    // adding to ChatMessage ArrayList -AB
                    messages.add(msg);
                } while (c.moveToNext());
            }
        }



        return messages;
    }



}