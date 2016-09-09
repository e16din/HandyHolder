package com.e16din.handyholder.wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class SimpleHandy<MODEL> extends Handy<RecyclerView.Adapter, RecyclerView.ViewHolder, MODEL> {

    public SimpleHandy(RecyclerView.Adapter adapter, ViewGroup vParent) {
        super(adapter, vParent);
    }

    public SimpleHandy(RecyclerView.Adapter adapter, ViewGroup vParent, int layoutId) {
        super(adapter, vParent, layoutId);
    }
}