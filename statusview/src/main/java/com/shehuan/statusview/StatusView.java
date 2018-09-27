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
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class StatusView extends FrameLayout {
    private Context context;

    private View currentView;

    private View contentView;

    private @LayoutRes
    int loadingLayoutId = R.layout.sv_loading_layout;
    private @LayoutRes
    int emptyLayoutId = R.layout.sv_empty_layout;
    private @LayoutRes
    int errorLayoutId = R.layout.sv_error_layout;

    private SparseArray<View> viewArray = new SparseArray<>();
    private SparseArray<OnConvertListener> listenerArray = new SparseArray<>();

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
                loadingLayoutId = ta.getResourceId(attr, loadingLayoutId);
            } else if (attr == R.styleable.StatusView_sv_empty_view) {
                emptyLayoutId = ta.getResourceId(attr, emptyLayoutId);
            } else if (attr == R.styleable.StatusView_sv_error_view) {
                errorLayoutId = ta.getResourceId(attr, errorLayoutId);
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

    public static StatusView init(Activity activity, @IdRes int viewId) {
        View rootView = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        View contentView = rootView.findViewById(viewId);
        return init(contentView);
    }

    public static StatusView init(Fragment fragment, @IdRes int viewId) {
        View rootView = fragment.getView();
        View contentView = null;
        if (rootView != null) {
            contentView = rootView.findViewById(viewId);
        }
        return init(contentView);
    }

    private static StatusView init(View contentView) {
        if (contentView == null) {
            throw new RuntimeException("status view can not be null!");
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
        this.loadingLayoutId = loadingLayoutRes;
    }

    public void setEmptyView(@LayoutRes int emptyLayoutRes) {
        this.emptyLayoutId = emptyLayoutRes;
    }

    public void setErrorView(@LayoutRes int errorLayoutRes) {
        this.errorLayoutId = errorLayoutRes;
    }

    public void showContentView() {
        switchStatusView(contentView);
    }

    public void showLoadingView() {
        switchStatusView(loadingLayoutId);
        callConvertListener(loadingLayoutId);
    }

    public void showEmptyView() {
        switchStatusView(emptyLayoutId);
        callConvertListener(emptyLayoutId);
    }

    public void showErrorView() {
        switchStatusView(errorLayoutId);
        callConvertListener(errorLayoutId);
    }

    public void setOnLoadingViewConvertListener(OnConvertListener listener) {
        listenerArray.put(loadingLayoutId, listener);
    }

    public void setOnEmptyViewConvertListener(OnConvertListener listener) {
        listenerArray.put(emptyLayoutId, listener);
    }

    public void setOnErrorViewConvertListener(OnConvertListener listener) {
        listenerArray.put(errorLayoutId, listener);
    }

    public void configLoadingView() {

    }

    public void configEmptyView() {

    }

    public void configErrorView() {

    }

    private void callConvertListener(@LayoutRes int layoutId) {
        ViewHolder viewHolder;
        OnConvertListener listener = listenerArray.get(layoutId);
        View statusView = viewArray.get(layoutId);
        if (listener != null && statusView != null) {
            viewHolder = ViewHolder.create(statusView);
            listener.onConvert(viewHolder);
        }
    }

    private void switchStatusView(View statusView) {
        if (statusView == currentView) {
            return;
        }
        removeView(currentView);
        currentView = statusView;
        addView(currentView);
    }

    private void switchStatusView(@LayoutRes int layoutId) {
        View statusView = generateStatusView(layoutId);
        switchStatusView(statusView);
    }

    private View generateStatusView(@LayoutRes int layoutId) {
        View statusView = viewArray.get(layoutId);
        if (statusView == null) {
            statusView = inflate(layoutId);
            viewArray.put(layoutId, statusView);
        }
        return statusView;
    }

    private View inflate(int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    public interface OnConvertListener {
        void onConvert(ViewHolder viewHolder);
    }
}
