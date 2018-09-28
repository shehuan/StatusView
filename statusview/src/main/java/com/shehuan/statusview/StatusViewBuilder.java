package com.shehuan.statusview;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.View;

public class StatusViewBuilder {
    // loading 提示信息
    private String loadingText;
    // empty 提示信息
    private String emptyText;
    // error 提示信息
    private String errorText;
    // 提示信息颜色
    private int tipColor;
    // 提示信息字体大小
    private int tipSize;
    // empty 图标
    private int emptyIcon;
    // error 图标
    private int errorIcon;
    // 是否显示 empty 重试重试按钮
    private boolean showEmptyRetry;
    // 是否显示 error 重试重试按钮
    private boolean showErrorRetry;
    // empty 重试按钮文字
    private String emptyRetryText;
    // error 重试按钮文字
    private String errorRetryText;
    // retry 按钮文字颜色
    private int retryColor;
    // retry 按钮字体大小
    private int retrySize;
    // retry 按钮 drawable 背景
    private int retryDrawable;
    // empty 重试按钮点击事件
    private View.OnClickListener emptyRetryClickListener;
    // error 重试按钮点击事件
    private View.OnClickListener errorRetryClickListener;

    public String getLoadingText() {
        return loadingText;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public String getErrorText() {
        return errorText;
    }

    public int getTipColor() {
        return tipColor;
    }

    public int getTipSize() {
        return tipSize;
    }

    public int getEmptyIcon() {
        return emptyIcon;
    }

    public int getErrorIcon() {
        return errorIcon;
    }

    public boolean isShowEmptyRetry() {
        return showEmptyRetry;
    }

    public boolean isShowErrorRetry() {
        return showErrorRetry;
    }

    public String getEmptyRetryText() {
        return emptyRetryText;
    }

    public String getErrorRetryText() {
        return errorRetryText;
    }

    public int getRetryColor() {
        return retryColor;
    }

    public int getRetrySize() {
        return retrySize;
    }

    public int getRetryDrawable() {
        return retryDrawable;
    }

    public View.OnClickListener getEmptyRetryClickListener() {
        return emptyRetryClickListener;
    }

    public View.OnClickListener getErrorRetryClickListener() {
        return errorRetryClickListener;
    }

    public StatusViewBuilder(Builder builder) {
        this.loadingText = builder.loadingText;
        this.emptyText = builder.emptyText;
        this.errorText = builder.errorText;
        this.tipColor = builder.tipColor;
        this.tipSize = builder.tipSize;
        this.emptyIcon = builder.emptyIcon;
        this.errorIcon = builder.errorIcon;
        this.showEmptyRetry = builder.showEmptyRetry;
        this.showErrorRetry = builder.showErrorRetry;
        this.emptyRetryText = builder.emptyRetryText;
        this.errorRetryText = builder.errorRetryText;
        this.retryColor = builder.retryColor;
        this.retrySize = builder.retrySize;
        this.retryDrawable = builder.retryDrawable;
        this.emptyRetryClickListener = builder.emptyRetryClickListener;
        this.errorRetryClickListener = builder.errorRetryClickListener;
    }

    public static class Builder {
        private String loadingText;
        private String emptyText;
        private String errorText;
        private int tipColor;
        private int tipSize;

        private int emptyIcon;
        private int errorIcon;

        private boolean showEmptyRetry = true;
        private boolean showErrorRetry = true;
        private String emptyRetryText;
        private String errorRetryText;
        private int retryColor;
        private int retrySize;
        private int retryDrawable;
        private View.OnClickListener emptyRetryClickListener;
        private View.OnClickListener errorRetryClickListener;

        public Builder setLoadingText(String loadingText) {
            this.loadingText = loadingText;
            return this;
        }

        public Builder setEmptyip(String emptyTip) {
            this.emptyText = emptyTip;
            return this;
        }

        public Builder setErrorText(String errorText) {
            this.errorText = errorText;
            return this;
        }

        public Builder setTipColor(@ColorInt int tipColor) {
            this.tipColor = tipColor;
            return this;
        }

        public Builder setTipSize(int tipSize) {
            this.tipSize = tipSize;
            return this;
        }

        public Builder setEmptyIcon(int emptyIcon) {
            this.emptyIcon = emptyIcon;
            return this;
        }

        public Builder setErrorIcon(int errorIcon) {
            this.errorIcon = errorIcon;
            return this;
        }

        public Builder showEmptyRetry(boolean showEmptyRetry) {
            this.showEmptyRetry = showEmptyRetry;
            return this;
        }

        public Builder showErrorRetry(boolean showErrorRetry) {
            this.showErrorRetry = showErrorRetry;
            return this;
        }

        public Builder setEmptyRetryText(String emptyRetryText) {
            this.emptyRetryText = emptyRetryText;
            return this;
        }

        public Builder setErrorRetryText(String errorRetryText) {
            this.errorRetryText = errorRetryText;
            return this;
        }

        public Builder setRetryColor(@ColorInt int retryColor) {
            this.retryColor = retryColor;
            return this;
        }

        public Builder setRetrySize(int retrySize) {
            this.retrySize = retrySize;
            return this;
        }

        public Builder setRetryDrawable(@DrawableRes int retryDrawable) {
            this.retryDrawable = retryDrawable;
            return this;
        }

        public Builder setOnEmptyRetryClickListener(View.OnClickListener emptyRetryClickListener) {
            this.emptyRetryClickListener = emptyRetryClickListener;
            return this;
        }

        public Builder setOnErrorRetryClickListener(View.OnClickListener errorRetryClickListener) {
            this.errorRetryClickListener = errorRetryClickListener;
            return this;
        }

        public StatusViewBuilder build() {
            return new StatusViewBuilder(this);
        }
    }
}
