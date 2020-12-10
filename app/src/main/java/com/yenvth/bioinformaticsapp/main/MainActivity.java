package com.yenvth.bioinformaticsapp.main;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yenvth.bioinformaticsapp.R;
import com.yenvth.bioinformaticsapp.history.HistoryFragment;
import com.yenvth.bioinformaticsapp.predict.PredictFragment;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    MainViewPagerAdapter mainViewPagerAdapter;
    ArrayList<Fragment> mListFragment;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
        action();
    }

    private void init() {

        mViewPager = findViewById(R.id.mViewPager);
        mTabLayout = findViewById(R.id.mTabLayout);
        mListFragment = new ArrayList<>();
    }

    private void action(){
        mListFragment.add(new PredictFragment());
        mListFragment.add(new HistoryFragment());

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), mListFragment);
        mViewPager.setAdapter(mainViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
    }


}