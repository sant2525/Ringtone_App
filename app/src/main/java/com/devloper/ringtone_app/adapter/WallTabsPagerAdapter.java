package com.devloper.ringtone_app.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.devloper.ringtone_app.fragment.FeaturedRingTab;

public class WallTabsPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = WallTabsPagerAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 3;

    public WallTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FeaturedRingTab();
            case 1:
                return new FeaturedRingTab();
            case 2:
                return new FeaturedRingTab();

        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Featured";
            case 1:
                return "Categories";
            case 2:
                return "Premium";

        }
        return null;
    }
}

