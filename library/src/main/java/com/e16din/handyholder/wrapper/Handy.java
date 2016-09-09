package com.e16din.handyholder.wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class Handy<MODEL> extends StrongHandy<RecyclerView.Adapter, RecyclerView.ViewHolder, MODEL> {

    public Handy(RecyclerView.Adapter adapter, ViewGroup vParent) {
        super(adapter, vParent);
    }

    public Handy(RecyclerView.Adapter adapter, ViewGroup vParent, int layoutId) {
        super(adapter, vParent, layoutId);
    }
}