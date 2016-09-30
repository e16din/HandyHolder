package com.e16din.handyholder.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.R;

public class HandyCreator<ADAPTER extends RecyclerView.Adapter, MODEL> {

    public HandyHolder<ADAPTER, MODEL> createStrong(ADAPTER adapter, ViewGroup vParent, int layoutId) {
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

    public HandyHolder<ADAPTER, MODEL> createStrong(ADAPTER adapter, ViewGroup vParent) {
        return createStrong(adapter, vParent, 0);
    }

    public HandyHolder<ADAPTER, MODEL> createStrong(ViewGroup vParent, int layoutId) {
        return createStrong(null, vParent, layoutId);
    }

    public HandyHolder<ADAPTER, MODEL> createStrong(ViewGroup vParent) {
        return createStrong(vParent, 0);
    }
}
