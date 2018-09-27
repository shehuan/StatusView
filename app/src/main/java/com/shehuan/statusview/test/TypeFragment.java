package com.shehuan.statusview.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;


public class TypeFragment extends BaseFragment {
    private static final String ARG_PARAM = "type";

    private String mType;
//
//    @BindView(R.id.status_view)
//    StatusView statusView;

    @BindView(R.id.content)
    TextView mContent;

    @BindView(R.id.root)
    RelativeLayout root;


    public TypeFragment() {
        // Required empty public constructor
    }

    public static TypeFragment newInstance(String type) {
        TypeFragment fragment = new TypeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            mType = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    protected void initView() {
        mContent.setText(mType);

//        statusView.setEmptyView(R.layout.empty);
//        statusView.showEmptyView();
//        statusView.showContentView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        StatusView statusView = StatusView.init(this);
//        statusView.setEmptyView(R.layout.empty);
//        statusView.showEmptyView();
    }
}
