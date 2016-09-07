package com.e16din.handyholder.listeners;

import com.e16din.handyholder.HandyHolder;

public abstract class HandyListener<MODEL> implements BaseHandyListener<MODEL> {

    public void onPreBind(HandyHolder<MODEL> h, MODEL item, int position) {
    }

}