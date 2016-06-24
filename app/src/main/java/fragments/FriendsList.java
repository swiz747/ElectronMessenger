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

public class FriendsList extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friendslist_layout, container, false);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {

    }



}