package com.e16din.handyholder.listeners.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BindListener<MODEL>
        extends StrongBindListener<RecyclerView.Adapter, RecyclerView.ViewHolder, MODEL> {
}
