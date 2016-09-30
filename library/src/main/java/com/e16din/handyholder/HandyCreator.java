package com.e16din.handyholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class HandyCreator<ADAPTER extends RecyclerView.Adapter, MODEL> {

    public HandyHolder<ADAPTER, MODEL> create(ADAPTER adapter, ViewGroup vParent, int layoutId) {
        final LayoutInflater inflater = LayoutInflater.from(HandyHolder.getContext());
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        final HandyHolder<ADAPTER, MODEL> holder = new HandyHolder<>(itemView);
        onCreate(adapter, layoutId, (FrameLayout) itemView, holder);

        return holder;
    }

    protected void onCreate(ADAPTER adapter, int layoutId, FrameLayout itemView,
                            HandyHolder<ADAPTER, MODEL> holder) {
        holder.mCommonBox.vRoot = itemView;
        holder.mCommonBox
                .adapter(adapter)
                .layoutId(layoutId);
    }

    public HandyHolder<ADAPTER, MODEL> create(ADAPTER adapter, ViewGroup vParent) {
        return create(adapter, vParent, 0);
    }

    public HandyHolder<ADAPTER, MODEL> create(ViewGroup vParent, int layoutId) {
        return create(null, vParent, layoutId);
    }

    public HandyHolder<ADAPTER, MODEL> create(ViewGroup vParent) {
        return create(vParent, 0);
    }
}
