package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;

public abstract class StrongHolderListener
        <ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL>
        implements BaseHolderListener<ADAPTER, HOLDER, MODEL> {

    @Override
    public void beforeBind(ADAPTER adapter, HOLDER holder, MODEL item, int position) {
    }

    @Override
    public void afterBind(ADAPTER adapter, HOLDER holder, MODEL item, int position) {
    }

    @Override
    public void onAsyncInflateFinished(ADAPTER adapter, HOLDER holder, int position) {
    }
}