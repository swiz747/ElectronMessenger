package com.tritiumlabs.arthur.servertest;

/**
 * Created by Arthur on 6/7/2016.
 */

import java.util.Random;

public class ChatMessage {

    public String body, sender, receiver, senderName;
    public String Date, Time;
    public String msgid;
    public boolean isMine;// Did I send the message.

    public ChatMessage(String Sender, String Receiver, String messageString,
                       String ID, boolean isMINE) {
        body = messageString;
        isMine = isMINE;
        sender = Sender;
        msgid = ID;
        receiver = Receiver;
        senderName = sender;
    }
    /**
     * Probably shouldnt be a random integer due to possible conflicts
     * -AB
     * */
    //TODO: probably something, just keep this in mind -AB
    public void setMsgID() {

        msgid += "-" + String.format("%02d", new Random().nextInt(100));
        ;
    }
}