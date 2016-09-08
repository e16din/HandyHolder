package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;

import com.e16din.handyholder.HandyHolder;

public abstract class HolderListener<MODEL> implements BaseHolderListener<MODEL> {

    public void beforeBind(RecyclerView.Adapter adapter, HandyHolder<MODEL> holder, MODEL item, int position) {
    }

    public void afterBind(RecyclerView.Adapter adapter, HandyHolder<MODEL> holder, MODEL item, int position) {
    }

    public void onAsyncInflateFinished(RecyclerView.Adapter adapter, HandyHolder<MODEL> holder, int position) {
    }

}