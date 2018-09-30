package com.shehuan.statusview.test;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.shehuan.statusview.StatusView;
import com.shehuan.statusview.StatusViewBuilder;
import com.shehuan.statusview.test.base.LazyLoadFragment;

import butterknife.BindView;


public class TypeFragment1 extends LazyLoadFragment {
    private static final String ARG_PARAM = "type";

    private String mType;

    @BindView(R.id.content)
    TextView content;

    @BindView(R.id.status_view)
    StatusView statusView;


    public TypeFragment1() {
        // Required empty public constructor
    }

    public static TypeFragment1 newInstance(String type) {
        TypeFragment1 fragment = new TypeFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.fragment_type1;
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mType = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    protected void initView() {
        content.setText(mType);
    }


    @Override
    protected void loadData() {
        statusView.config(new StatusViewBuilder.Builder()
                .setOnErrorRetryClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusView.showLoadingView();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                statusView.showContentView();
                            }
                        }, 1500);
                    }
                })
                .build());
        statusView.showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                statusView.showErrorView();
            }
        }, 1500);
    }
}
