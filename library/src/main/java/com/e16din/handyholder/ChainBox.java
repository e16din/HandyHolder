package com.e16din.handyholder;


import android.graphics.Point;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.e16din.handyholder.listeners.click.OnClickListener;
import com.e16din.handyholder.listeners.click.OnViewsClickListener;
import com.e16din.handyholder.listeners.holder.StrongHolderListener;

import java.util.ArrayList;
import java.util.List;

public class ChainBox<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL>
        extends ClickBox<ADAPTER, HOLDER, MODEL> {

    public ChainBox<ADAPTER, HOLDER, MODEL> holderListener(StrongHolderListener<ADAPTER, HOLDER, MODEL> listener) {

        if (mListeners == null) {
            mListeners = new ArrayList<>();
        }

        mListeners.add(listener);

        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> emptyStubSize(int width, int height) {
        mEmptyStubSize = new Point(width, height);
        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> stubId(@LayoutRes int stubId) {
        mStubId = stubId;
        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> layoutId(@LayoutRes int layoutId) {
        mLayoutId = layoutId;
        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> isRecyclable(boolean recyclable) {
        mRecyclable = recyclable;
        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> onClick(OnClickListener<MODEL> listener) {
        setClickListener(listener);
        return this;
    }

    public void setViewsClickListener(List<Integer> views, OnViewsClickListener<MODEL> listener) {
        setClickableViewsList(views);
        setViewsClickListener(listener);
    }

    public void setViewsClickListener(int[] views, OnViewsClickListener<MODEL> listener) {
        setClickableViewsArray(views);
        setViewsClickListener(listener);
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> onViewsClick(List<Integer> views, OnViewsClickListener<MODEL> listener) {
        setViewsClickListener(views, listener);
        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> onViewsClick(int[] views, OnViewsClickListener<MODEL> listener) {
        setViewsClickListener(views, listener);
        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> asyncInflating(boolean asyncInflating) {
        mAsyncInflating = asyncInflating;
        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> rippleEffect(boolean rippleEffect) {
        mRippleEffect = rippleEffect;
        return this;
    }

    public ChainBox<ADAPTER, HOLDER, MODEL> rippleColor(@ColorInt int color) {
        mRippleColor = color;
        return this;
    }

    /**
     * Set adapter, to use on holder callbacks, free it on inflate finished.
     */
    public ChainBox<ADAPTER, HOLDER, MODEL> adapter(ADAPTER adapter) {
        mAdapter = adapter;
        return this;
    }

    /**
     * Set holder, to use on holder callbacks, free it on inflate finished.
     */
    public ChainBox<ADAPTER, HOLDER, MODEL> holder(HOLDER holder) {
        mHolder = holder;
        return this;
    }

    /**
     * Init and free view holder.
     *
     * @return Inited view holder.
     */
    public HOLDER init() {
        HOLDER result = mHolder;
        if (!mAsyncInflating) {
            freeHolder();
        }// else wait for async inflater
        init(result);
        return result;
    }

    /**
     * @return getHolder.
     */
    public HOLDER getHolder() {
        return mHolder;
    }
}
