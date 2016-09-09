package com.e16din.handyholder.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.ChainBox;
import com.e16din.handyholder.R;
import com.e16din.handyholder.listeners.holder.StrongHolderListener;

import java.util.List;

@SuppressWarnings("unused")//remove it to see warnings
public abstract class StrongHandy<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL> {

    private static final int NO_STUB_LAYOUT = 0;

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }

    private HOLDER mHolder;

    protected ChainBox<ADAPTER, HOLDER, MODEL> mCommonBox = new ChainBox<>();


    public StrongHandy(ADAPTER adapter, ViewGroup vParent) {
        final LayoutInflater inflater = LayoutInflater.from(sContext);
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        mHolder = newHolder(itemView);
        mCommonBox.vRoot = (FrameLayout) itemView;
        mCommonBox.mAdapter = adapter;
    }

    public StrongHandy(ADAPTER adapter, ViewGroup vParent, int layoutId) {
        this(adapter, vParent);
        mCommonBox.layoutId(layoutId);
    }

    public ChainBox set() {
        return mCommonBox;
    }

    public abstract HOLDER newHolder(ViewGroup vRoot);


    public void bindItem(MODEL item, int position) {
        mCommonBox.bindItem(mHolder, item, position);
    }

    public void freeAdapter() {
        mCommonBox.freeAdapter();
    }

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