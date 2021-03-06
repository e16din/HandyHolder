package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public interface BaseHolderListener
        <ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL> {

    void onInit(HOLDER h, View v);

    void beforeBind(ADAPTER adapter, HOLDER holder, MODEL item, int position);

    void onBind(MODEL item, int position);

    void afterBind(ADAPTER adapter, HOLDER holder, MODEL item, int position);

    void onAsyncInflateFinished(ADAPTER adapter, HOLDER holder, int position);
}