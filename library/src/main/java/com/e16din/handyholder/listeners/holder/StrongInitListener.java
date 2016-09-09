package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;

public abstract class StrongInitListener
        <ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL>
        extends StrongHolderListener<ADAPTER, HOLDER, MODEL> {

    @Override
    public void onBind(MODEL item, int position) {
    }
}
