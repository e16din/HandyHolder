package com.e16din.handyholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.e16din.handyholder.listeners.holder.BaseHolderListener;


public abstract class BaseViewHolder<MODEL> extends RecyclerView.ViewHolder implements BaseHolderListener<MODEL> {

    public FrameLayout vRoot;//is itemView

    public BaseViewHolder(View itemView) {
        super(itemView);
    }
}