package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tritiumlabs.arthur.servertest.R;


public class FriendAdd extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.friendadd_layout, container, false);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {

    }




}