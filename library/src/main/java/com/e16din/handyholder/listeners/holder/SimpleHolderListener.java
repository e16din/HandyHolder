package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e16din.handyholder.holder.StrongHandyHolder;

public abstract class SimpleHolderListener<MODEL>
        extends StrongHolderListener<RecyclerView.Adapter, StrongHandyHolder, MODEL> {

    @Override
    public void onInit(StrongHandyHolder h, View v) {
    }

    @Override
    public void onBind(MODEL item, int position) {
    }
}
