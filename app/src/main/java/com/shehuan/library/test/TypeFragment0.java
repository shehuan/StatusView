package com.shehuan.library.test;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.shehuan.statusview.StatusView;
import com.shehuan.statusview.StatusViewBuilder;
import com.shehuan.library.test.base.LazyLoadFragment;

import butterknife.BindView;

public class TypeFragment0 extends LazyLoadFragment {
    private static final String ARG_PARAM = "type";

    private String mType;

    private StatusView statusView;

    @BindView(R.id.content)
    TextView content;

    public TypeFragment0() {
        // Required empty public constructor
    }

    public static TypeFragment0 newInstance(String type) {
        TypeFragment0 fragment = new TypeFragment0();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.fragment_type0;
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
        statusView = StatusView.init(this, R.id.content);
        statusView.config(new StatusViewBuilder.Builder()
                .setOnEmptyRetryClickListener(new View.OnClickListener() {
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
                }).build());
        statusView.showLoadingView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                statusView.showEmptyView();
            }
        }, 1500);
    }
}
