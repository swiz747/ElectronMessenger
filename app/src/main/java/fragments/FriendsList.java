package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import com.tritiumlabs.arthur.servertest.ChatAdapter;
import com.tritiumlabs.arthur.servertest.ChatMessage;
import com.tritiumlabs.arthur.servertest.CommonMethods;
import com.tritiumlabs.arthur.servertest.FriendslistAdapter;
import com.tritiumlabs.arthur.servertest.MainActivity;
import com.tritiumlabs.arthur.servertest.R;

import org.jivesoftware.smack.roster.Roster;



public class FriendsList extends Fragment {



    public static FriendslistAdapter friendslistAdapter;
    ListView lstView_Friends;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friendslist_layout, container, false);
        lstView_Friends = (ListView) view.findViewById(R.id.lstView_Friends);


        // ----Set autoscroll of listview when a new message arrives----//
        lstView_Friends.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        lstView_Friends.setStackFromBottom(true);



        MainActivity activity = ((MainActivity) getActivity());
        String friendString = activity.getmService().xmpp.getRoster();
        friendslistAdapter = new FriendslistAdapter(getActivity(), friendString);
        lstView_Friends.setAdapter(friendslistAdapter);
        friendslistAdapter.notifyDataSetChanged();


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {

    }



}