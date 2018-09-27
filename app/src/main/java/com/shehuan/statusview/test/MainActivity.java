package com.shehuan.statusview.test;

import android.content.Intent;

import com.shehuan.statusview.StatusView;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private StatusView statusView;

    @OnClick(R.id.tv_start)
    public void start() {
        startActivity(new Intent(this, TabActivity.class));
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        statusView = StatusView.init(this, R.id.tv_start);
        statusView.setLoadingView(R.layout.loading);
        statusView.showLoadingView();
//        statusView.showContentView();
    }
}
