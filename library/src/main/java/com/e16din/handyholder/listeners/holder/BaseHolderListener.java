package com.e16din.handyholder.listeners.holder;

import android.view.View;

import com.e16din.handyholder.HandyHolder;


public interface BaseHolderListener<MODEL> {
    void onInit(HandyHolder<MODEL> h, View v);

    void onBind(MODEL item, int position);
}