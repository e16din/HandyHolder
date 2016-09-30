package com.e16din.handyholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.listeners.holder.HolderListener;
import com.e16din.handyholder.settings.AlreadyBox;

import java.util.List;

@SuppressWarnings("unused")//remove it to see warnings
public abstract class HandyWrapper<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL> {

    private static final int NO_STUB_LAYOUT = 0;


    protected AlreadyBox<ADAPTER, HOLDER, MODEL> mCommonBox;


    public HandyWrapper(ADAPTER adapter, ViewGroup vParent, int layoutId) {
        final LayoutInflater inflater = LayoutInflater.from(HandyHolder.getContext());
        final ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        final HOLDER holder = newHolder(itemView);

        if (holder instanceof HandyHolder) {
            mCommonBox = ((HandyHolder) holder).set();
        } else {
            mCommonBox = new AlreadyBox<>();
            mCommonBox.layoutId(layoutId);
        }

        mCommonBox.adapter(adapter);
        mCommonBox.vRoot = (FrameLayout) itemView;
    }

    public HandyWrapper(ADAPTER adapter, ViewGroup vParent) {
        this(adapter, vParent, 0);
    }

    public AlreadyBox set() {
        return mCommonBox;
    }

    public abstract HOLDER newHolder(ViewGroup vRoot);


    public List<HolderListener<ADAPTER, HOLDER, MODEL>> getListeners() {
        return mCommonBox.mListeners;
    }

    public void removeHolderListener(HolderListener listener) {
        mCommonBox.removeHolderListener(listener);
    }

    public void clearHolderListeners() {
        mCommonBox.clearHolderListeners();
    }
}