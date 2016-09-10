package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e16din.handyholder.holder.StrongHandyHolder;

public abstract class StrongBindListener
        <ADAPTER extends RecyclerView.Adapter, HOLDER extends StrongHandyHolder, MODEL>
        extends StrongHolderListener<ADAPTER, HOLDER, MODEL> {

    @Override
    public void onInit(HOLDER h, View v) {
    }
}
