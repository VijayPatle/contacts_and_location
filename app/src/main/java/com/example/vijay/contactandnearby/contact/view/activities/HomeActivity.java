package com.example.vijay.contactandnearby.contact.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vijay.contactandnearby.R;
import com.example.vijay.contactandnearby.contact.adapters.ViewPagerAdapter;

public class HomeActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private   ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAdapterAndBar();
    }

    private void setAdapterAndBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}