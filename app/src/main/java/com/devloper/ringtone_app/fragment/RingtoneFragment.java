package com.devloper.ringtone_app.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.devloper.ringtone_app.R;
import com.devloper.ringtone_app.adapter.RingtoneTabsPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class RingtoneFragment extends Fragment {

    private static final String TAG = RingtoneFragment.class.getSimpleName();

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public RingtoneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        setRetainInstance(true);

        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);

        viewPager.setAdapter(new RingtoneTabsPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        return view;
    }



}

