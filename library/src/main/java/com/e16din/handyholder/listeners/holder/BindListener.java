package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e16din.handyholder.HandyHolder;

public abstract class BindListener
        <ADAPTER extends RecyclerView.Adapter, HOLDER extends HandyHolder, MODEL>
        extends HolderListener<ADAPTER, HOLDER, MODEL> {

    @Override
    public void onInit(HOLDER h, View v) {
    }
}
