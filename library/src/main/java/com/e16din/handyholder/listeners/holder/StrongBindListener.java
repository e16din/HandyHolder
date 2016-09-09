package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class StrongBindListener
        <ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL>
        extends StrongHolderListener<ADAPTER, HOLDER, MODEL> {

    @Override
    public void onInit(HOLDER h, View v) {
    }
}
