package com.shehuan.statusview.test;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TabActivity extends BaseActivity {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected int initLayoutResID() {
        return R.layout.activity_tab;
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(TypeFragment.newInstance("tab-1-content"));
        mFragments.add(TypeFragment.newInstance("tab-2-content"));
        mFragments.add(TypeFragment.newInstance("tab-3-content"));

        mTitles = new ArrayList<>();
        mTitles.add("tab-1");
        mTitles.add("tab-2");
        mTitles.add("tab-3");
    }

    @Override
    protected void initView() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.setData(mFragments, mTitles);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(mTitles.size());
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
