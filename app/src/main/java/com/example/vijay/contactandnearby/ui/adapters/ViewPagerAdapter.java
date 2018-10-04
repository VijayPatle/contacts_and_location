package com.example.vijay.contactandnearby.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vijay.contactandnearby.ui.view.fragments.ContactFragment;
import com.example.vijay.contactandnearby.ui.view.fragments.NearByFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new ContactFragment();
        }
        else if (position == 1)
        {
            fragment = new NearByFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Contacts";
        }
        else if (position == 1)
        {
            title = "NearBy";
        }

        return title;
    }
}
