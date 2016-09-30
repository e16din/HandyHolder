package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e16din.handyholder.HandyHolder;

public abstract class SimpleHolderListener<MODEL>
        extends HolderListener<RecyclerView.Adapter, HandyHolder, MODEL> {

    @Override
    public void onInit(HandyHolder h, View v) {
    }

    @Override
    public void onBind(MODEL item, int position) {
    }
}
