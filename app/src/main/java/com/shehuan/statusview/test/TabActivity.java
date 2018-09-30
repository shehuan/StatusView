package com.shehuan.statusview.test;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.shehuan.statusview.StatusView;
import com.shehuan.statusview.StatusViewConvertListener;
import com.shehuan.statusview.ViewHolder;
import com.shehuan.statusview.test.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TabActivity extends BaseActivity {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    private StatusView statusView;

    private PagerAdapter pagerAdapter;

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
        mFragments.add(TypeFragment0.newInstance("Tab0-content"));
        mFragments.add(TypeFragment1.newInstance("Tab1-content"));

        mTitles = new ArrayList<>();
        mTitles.add("Tab0");
        mTitles.add("Tab1");
    }

    @Override
    protected void initView() {
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.setData(mFragments, mTitles);

        statusView = StatusView.init(this);
        statusView.setLoadingView(R.layout.loading);
        statusView.setErrorView(R.layout.error);
        statusView.setOnErrorViewConvertListener(new StatusViewConvertListener() {
            @Override
            public void onConvert(ViewHolder viewHolder) {
                viewHolder.setOnClickListener(R.id.tv_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusView.showLoadingView();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                statusView.showContentView();
                                mViewPager.setAdapter(pagerAdapter);
                                mViewPager.setOffscreenPageLimit(mTitles.size());
                                mTabLayout.setupWithViewPager(mViewPager);
                            }
                        }, 1500);
                    }
                });
            }
        });
        statusView.showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                statusView.showErrorView();
            }
        }, 1500);
    }
}
