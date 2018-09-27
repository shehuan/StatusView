package com.shehuan.statusview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class StatusView extends FrameLayout {
    private Context context;

    private View currentView;

    private View contentView;

    private View loadingView;
    private View emptyView;
    private View errorView;

    private @LayoutRes
    int loadingLayoutRes;
    private @LayoutRes
    int emptyLayoutRes;
    private @LayoutRes
    int errorLayoutRes;

    public StatusView(@NonNull Context context) {
        this(context, null);
    }

    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StatusView, 0, 0);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.StatusView_sv_loading_view) {
                loadingLayoutRes = ta.getResourceId(attr, R.layout.sv_loading);
            } else if (attr == R.styleable.StatusView_sv_empty_view) {
                emptyLayoutRes = ta.getResourceId(attr, R.layout.sv_empty);
            } else if (attr == R.styleable.StatusView_sv_error_view) {
                errorLayoutRes = ta.getResourceId(attr, R.layout.sv_error);
            }
        }
        ta.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 1) {
            View view = getChildAt(0);
            setContentView(view);
        }
    }

    public static StatusView init(Activity activity) {
        View contentView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        return init(contentView);
    }

    public static StatusView init(Activity activity, @IdRes int layoutId) {
        View rootView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        View contentView = rootView.findViewById(layoutId);
        return init(contentView);
    }

    public static StatusView init(Fragment fragment, @IdRes int layoutId) {
        View rootView = fragment.getView();
        View contentView = null;
        if (rootView != null) {
            contentView = rootView.findViewById(layoutId);
        }
        return init(contentView);
    }

    private static StatusView init(View contentView) {
        if (contentView == null) {
            throw new RuntimeException("contentView can not be null!");
        }
        ViewGroup.LayoutParams lp = contentView.getLayoutParams();
        ViewGroup parent = (ViewGroup) contentView.getParent();
        int index = parent.indexOfChild(contentView);
        parent.removeView(contentView);
        StatusView statusView = new StatusView(contentView.getContext());
        statusView.addView(contentView);
        statusView.setContentView(contentView);
        parent.addView(statusView, index, lp);
        return statusView;
    }

    private void setContentView(View contentView) {
        this.contentView = currentView = contentView;
    }

    public void setLoadingView(@LayoutRes int loadingLayoutRes) {
        this.loadingLayoutRes = loadingLayoutRes;
    }

    public void setLoadingView(View loadingView) {
        this.loadingView = loadingView;
    }

    public void setEmptyView(@LayoutRes int emptyLayoutRes) {
        this.emptyLayoutRes = emptyLayoutRes;
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public void setErrorView(@LayoutRes int errorLayoutRes) {
        this.errorLayoutRes = errorLayoutRes;
    }

    public void setErrorView(View errorView) {
        this.errorView = errorView;
    }

    public void showContentView() {
        switchStatusView(contentView);
    }

    public void showLoadingView() {
        if (loadingView == null) {
            loadingView = inflate(loadingLayoutRes);
        }
        switchStatusView(loadingView);
    }

    public void showEmptyView() {
        if (emptyView == null) {
            emptyView = inflate(emptyLayoutRes);
        }
        switchStatusView(emptyView);
    }

    public void showErrorView() {
        if (errorView == null) {
            errorView = inflate(errorLayoutRes);
        }
        switchStatusView(errorView);
    }

    public ViewHolder setOnLoadingViewConvertListener(OnConvertListener listener) {
        ViewHolder viewHolder = null;
        if (listener != null && loadingView != null) {
            viewHolder = ViewHolder.create(loadingView);
            listener.onConvert(viewHolder);
        }
        return viewHolder;
    }

    public ViewHolder setOnEmptyViewConvertListener(OnConvertListener listener) {
        ViewHolder viewHolder = null;
        if (listener != null && emptyView != null) {
            viewHolder = ViewHolder.create(emptyView);
            listener.onConvert(viewHolder);
        }
        return viewHolder;
    }

    public ViewHolder setOnErrorViewConvertListener(OnConvertListener listener) {
        ViewHolder viewHolder = null;
        if (listener != null && errorView != null) {
            viewHolder = ViewHolder.create(errorView);
            listener.onConvert(viewHolder);
        }
        return viewHolder;
    }

    private void switchStatusView(View statusView) {
        if (statusView == currentView) {
            return;
        }
        removeView(currentView);
        currentView = statusView;
        addView(currentView);
    }

    private View inflate(int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    public interface OnConvertListener {
        void onConvert(ViewHolder viewHolder);
    }
}
