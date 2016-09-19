package com.e16din.handyholder.wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.AlreadyBox;
import com.e16din.handyholder.R;
import com.e16din.handyholder.holder.HandyHolder;
import com.e16din.handyholder.holder.StrongHandyHolder;
import com.e16din.handyholder.listeners.holder.StrongHolderListener;

import java.util.List;

@SuppressWarnings("unused")//remove it to see warnings
public abstract class StrongHandy<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL> {

    private static final int NO_STUB_LAYOUT = 0;


    protected AlreadyBox<ADAPTER, HOLDER, MODEL> mCommonBox;


    public StrongHandy(ADAPTER adapter, ViewGroup vParent, int layoutId) {
        final LayoutInflater inflater = LayoutInflater.from(HandyHolder.getContext());
        final ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        final HOLDER holder = newHolder(itemView);

        if (holder instanceof StrongHandyHolder) {
            mCommonBox = ((StrongHandyHolder) holder).set();
        } else {
            mCommonBox = new AlreadyBox<>();
            mCommonBox.layoutId(layoutId);
        }

        mCommonBox.adapter(adapter);
        mCommonBox.vRoot = (FrameLayout) itemView;
    }

    public StrongHandy(ADAPTER adapter, ViewGroup vParent) {
        this(adapter, vParent, 0);
    }

    public AlreadyBox set() {
        return mCommonBox;
    }

    public abstract HOLDER newHolder(ViewGroup vRoot);


    public List<StrongHolderListener<ADAPTER, HOLDER, MODEL>> getListeners() {
        return mCommonBox.mListeners;
    }

    public void removeHolderListener(StrongHolderListener listener) {
        mCommonBox.removeHolderListener(listener);
    }

    public void clearHolderListeners() {
        mCommonBox.clearHolderListeners();
    }
}