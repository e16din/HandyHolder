package com.e16din.handyholder.listeners;

import android.support.annotation.IdRes;

public interface OnViewsClickListener<MODEL> {
    void onClick(@IdRes int childViewId, MODEL item, int position);
}