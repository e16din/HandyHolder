package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;

import com.e16din.handyholder.HandyHolder;

public abstract class InitListener
        <ADAPTER extends RecyclerView.Adapter, HOLDER extends HandyHolder, MODEL>
        extends HolderListener<ADAPTER, HOLDER, MODEL> {

    @Override
    public void onBind(MODEL item, int position) {
    }
}
