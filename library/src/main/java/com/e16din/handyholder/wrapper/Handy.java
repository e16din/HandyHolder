package com.e16din.handyholder.wrapper;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.ClickBox;
import com.e16din.handyholder.R;
import com.e16din.handyholder.listeners.click.OnClickListener;
import com.e16din.handyholder.listeners.click.OnViewsClickListener;
import com.e16din.handyholder.listeners.holder.HolderListener;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")//remove it to see warnings
public abstract class Handy<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL> {

    private static final int NO_STUB_LAYOUT = 0;

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }

    private HOLDER mHolder;

    private ClickBox<ADAPTER, HOLDER, MODEL> mCommonBox = new ClickBox<>();


    public Handy(ADAPTER adapter, ViewGroup vParent) {
        final LayoutInflater inflater = LayoutInflater.from(sContext);
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        mHolder = newHolder(itemView);
        mCommonBox.vRoot = (FrameLayout) itemView;
        mCommonBox.mAdapter = adapter;
    }

    public Handy(ADAPTER adapter, ViewGroup vParent, int layoutId) {
        this(adapter, vParent);
        layoutId(layoutId);
    }


    public Handy<ADAPTER, HOLDER, MODEL> holderListener(HolderListener<ADAPTER, HOLDER, MODEL> listener) {
        if (mCommonBox.mListeners == null) {
            mCommonBox.mListeners = new ArrayList<>();
        }

        mCommonBox.mListeners.add(listener);

        return this;
    }

    public Handy<ADAPTER, HOLDER, MODEL> emptyStubSize(int width, int height) {
        mCommonBox.mEmptyStubSize = new Point(width, height);
        return this;
    }

    public Handy<ADAPTER, HOLDER, MODEL> stubId(@LayoutRes int stubId) {
        mCommonBox.mStubId = stubId;
        return this;
    }

    public Handy<ADAPTER, HOLDER, MODEL> layoutId(@LayoutRes int layoutId) {
        mCommonBox.mLayoutId = layoutId;
        return this;
    }

    public Handy<ADAPTER, HOLDER, MODEL> isRecyclable(boolean recyclable) {
        mHolder.setIsRecyclable(recyclable);
        return this;
    }

    public Handy<ADAPTER, HOLDER, MODEL> clickListener(OnClickListener<MODEL> listener) {
        mCommonBox.setClickListener(listener);
        return this;
    }

    public void setViewsClickListener(List<Integer> views, OnViewsClickListener<MODEL> listener) {
        mCommonBox.setClickableViewsList(views);
        mCommonBox.setViewsClickListener(listener);
    }

    public void setViewsClickListener(int[] views, OnViewsClickListener<MODEL> listener) {
        mCommonBox.setClickableViewsArray(views);
        mCommonBox.setViewsClickListener(listener);
    }

    public Handy<ADAPTER, HOLDER, MODEL> viewsClickListener(List<Integer> views, OnViewsClickListener<MODEL> listener) {
        setViewsClickListener(views, listener);
        return this;
    }

    public Handy<ADAPTER, HOLDER, MODEL> viewsClickListener(int[] views, OnViewsClickListener<MODEL> listener) {
        setViewsClickListener(views, listener);
        return this;
    }

    public Handy<ADAPTER, HOLDER, MODEL> asyncInflating(boolean asyncInflating) {
        mCommonBox.mAsyncInflating = asyncInflating;
        return this;
    }

    public Handy<ADAPTER, HOLDER, MODEL> rippleEffect(boolean rippleEffect) {
        mCommonBox.mRippleEffect = rippleEffect;
        return this;
    }

    public abstract HOLDER newHolder(ViewGroup vRoot);

    public HOLDER init() {
        mCommonBox.init(mHolder);
        return mHolder;
    }

    public void bindItem(MODEL item, int position) {
        mCommonBox.bindItem(mHolder, item, position);
    }

    public void freeAdapter() {
        mCommonBox.freeAdapter();
    }

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