package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.tritiumlabs.arthur.servertest.FriendslistAdapter;
import com.tritiumlabs.arthur.servertest.MainActivity;
import com.tritiumlabs.arthur.servertest.MyService;
import com.tritiumlabs.arthur.servertest.R;





public class FriendsList extends Fragment {


    String friendString = "";
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




        friendString = MyService.xmpp.getRoster();
        friendslistAdapter = new FriendslistAdapter(getActivity(), friendString);
        lstView_Friends.setAdapter(friendslistAdapter);
        friendslistAdapter.notifyDataSetChanged();
        Log.d("Friendslist","Trust me the friendslist was created and in theory the layout was inflated");



        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {

    }




}