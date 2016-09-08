package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public interface BaseHolderListener<HOLDER extends RecyclerView.ViewHolder, MODEL> {
    void onInit(HOLDER h, View v);

    void onBind(MODEL item, int position);
}