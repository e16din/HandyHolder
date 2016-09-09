package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class SimpleListener<MODEL>
        extends HolderListener<RecyclerView.Adapter, RecyclerView.ViewHolder, MODEL> {
    @Override
    public void onInit(RecyclerView.ViewHolder h, View v) {
    }

    @Override
    public void onBind(MODEL item, int position) {
    }
}
