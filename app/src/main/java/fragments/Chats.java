package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.tritiumlabs.arthur.servertest.ChatAdapter;
import com.tritiumlabs.arthur.servertest.ChatMessage;
import com.tritiumlabs.arthur.servertest.CommonMethods;
import com.tritiumlabs.arthur.servertest.MainActivity;
import com.tritiumlabs.arthur.servertest.R;

import java.util.ArrayList;
import java.util.Random;

public class Chats extends Fragment implements View.OnClickListener {

    private EditText msg_edittext;
    private String user1 = "arthur", user2 = "";
    private Random random;
    public static ArrayList<ChatMessage> chatlist;
    public static ChatAdapter chatAdapter;
    ListView msgListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_layout, container, false);
        random = new Random();
        //TODO: fix this deprecation -AB
        //((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("DIILDO");
        msg_edittext = (EditText) view.findViewById(R.id.messageEditText);
        msgListView = (ListView) view.findViewById(R.id.msgListView);
        ImageButton sendButton = (ImageButton) view
                .findViewById(R.id.sendMessageButton);
        sendButton.setOnClickListener(this);

        // ----Set autoscroll of listview when a new message arrives----//
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msgListView.setStackFromBottom(true);

        chatlist = new ArrayList<ChatMessage>();
        chatAdapter = new ChatAdapter(getActivity(), chatlist);
        msgListView.setAdapter(chatAdapter);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    public void sendTextMessage(View v) {
        String message = msg_edittext.getEditableText().toString();
        if (!message.equalsIgnoreCase("")) {
            final ChatMessage chatMessage = new ChatMessage();

            chatMessage.setBody(message);
            chatMessage.setSentTime(CommonMethods.getCurrentDateTime());
            chatMessage.setChatID(1);
            chatMessage.setSender(user1);
            chatMessage.setReceiver(user2);
            chatMessage.setIsMine(true);

            msg_edittext.setText("");
            chatAdapter.add(chatMessage);
            chatAdapter.notifyDataSetChanged();
            MainActivity activity = ((MainActivity) getActivity());
            activity.getmService().xmpp.sendMessage(chatMessage);
        }
    }

    public void setUser2(String user2)
    {
        this.user2 = user2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendMessageButton:
            {

                sendTextMessage(v);
            }

        }
    }

}