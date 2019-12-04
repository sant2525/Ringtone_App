package com.devloper.ringtone_app.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.devloper.ringtone_app.fragment.CategoryRingTab;
import com.devloper.ringtone_app.fragment.FeaturedRingTab;
import com.devloper.ringtone_app.fragment.PopularRingTab;


public class RingtoneTabsPagerAdapter extends FragmentPagerAdapter {



    private static final int FRAGMENT_COUNT = 3;


    public RingtoneTabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FeaturedRingTab();
            case 1:
                return new CategoryRingTab();
            case 2:
                return new PopularRingTab();

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
                return "Home";
            case 1:
                return "Categories";
            case 2:
                return "Popular";

        }
        return null;
    }

}
