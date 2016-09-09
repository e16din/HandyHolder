package com.e16din.handyholder.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SimpleHolder<MODEL> extends HandyHolder<RecyclerView.Adapter, MODEL> {

    public SimpleHolder(View itemView) {
        super(itemView);
    }
}