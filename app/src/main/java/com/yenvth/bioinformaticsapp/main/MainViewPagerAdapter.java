package com.yenvth.bioinformaticsapp.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mListFragment;

    public MainViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> mListFragment) {
        super(fm);
        this.mListFragment = mListFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return this.mListFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Predict";
            case 1:
                return "History";
            default:
                return "";
        }
    }
}
