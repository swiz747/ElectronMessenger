package com.tritiumlabs.arthur.servertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragments.Chats;
import fragments.FriendsList;

/**
 * Created by Arthur on 6/8/2016.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter
{

    public SectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        {
            return new FriendsList();
        }
        else if(position == 1)
        {
            return new Chats();
        }
        //TODO Holy shit this fucker caused issues, check 3rd tab before ripping hair out.
        else
        {
            return new Chats();
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Friends List";
            case 1:
                return "Chat";
            case 2:
                return "Something Else";
        }
        return null;
    }
}
