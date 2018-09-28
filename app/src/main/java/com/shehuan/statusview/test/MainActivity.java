package com.shehuan.statusview.test;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.shehuan.statusview.StatusView;
import com.shehuan.statusview.StatusViewBuilder;
import com.shehuan.statusview.ViewHolder;

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
        statusView.config(new StatusViewBuilder.Builder()
                .setLoadingText("加载中...")
                .setOnErrorRetryClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        statusView.showLoadingView();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                statusView.showContentView();
                            }
                        }, 2000);
                    }
                })
                .build());

        statusView.showLoadingView();
        statusView.setOnErrorViewConvertListener(new StatusView.OnConvertListener() {
            @Override
            public void onConvert(ViewHolder viewHolder) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                statusView.showErrorView();
            }
        }, 2000);
    }
}
