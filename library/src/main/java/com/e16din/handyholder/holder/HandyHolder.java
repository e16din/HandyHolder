package com.e16din.handyholder.holder;

import android.graphics.Point;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.ClickBox;
import com.e16din.handyholder.R;
import com.e16din.handyholder.listeners.click.OnClickListener;
import com.e16din.handyholder.listeners.click.OnViewsClickListener;
import com.e16din.handyholder.listeners.holder.HolderListener;
import com.e16din.handyholder.wrapper.Handy;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")//remove it to see warnings
public class HandyHolder<ADAPTER extends RecyclerView.Adapter, MODEL> extends RecyclerView.ViewHolder {


    public static <ADAPTER extends RecyclerView.Adapter, MODEL> HandyHolder<ADAPTER, MODEL> create(ADAPTER adapter,
                                                                                                   ViewGroup vParent) {
        final LayoutInflater inflater = LayoutInflater.from(Handy.getContext());
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        final HandyHolder<ADAPTER, MODEL> holder = new HandyHolder<>(itemView);
        holder.setAdapter(adapter);

        return holder;
    }

    public static <ADAPTER extends RecyclerView.Adapter, MODEL> HandyHolder<ADAPTER, MODEL> create(ADAPTER adapter,
                                                                                                   ViewGroup vParent,
                                                                                                   int layoutId) {
        return HandyHolder.<ADAPTER, MODEL>create(adapter, vParent)
                .layoutId(layoutId);
    }

    public static <MODEL> HandyHolder<RecyclerView.Adapter, MODEL> create(ViewGroup vParent) {
        final LayoutInflater inflater = LayoutInflater.from(Handy.getContext());
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        return new SimpleHolder<>(itemView);
    }

    public static <MODEL> HandyHolder<RecyclerView.Adapter, MODEL> create(ViewGroup vParent, int layoutId) {
        return HandyHolder.<MODEL>create(vParent)
                .layoutId(layoutId);
    }


    private ClickBox<ADAPTER, HandyHolder<ADAPTER, MODEL>, MODEL> mCommonBox = new ClickBox<>();


    public HandyHolder(View itemView) {
        super(itemView);
        mCommonBox.vRoot = (FrameLayout) itemView;
    }

    public HandyHolder<ADAPTER, MODEL> holderListener(HolderListener<ADAPTER,
            HandyHolder<ADAPTER, MODEL>, MODEL> listener) {

        if (mCommonBox.mListeners == null) {
            mCommonBox.mListeners = new ArrayList<>();
        }

        mCommonBox.mListeners.add(listener);

        return this;
    }

    public HandyHolder<ADAPTER, MODEL> emptyStubSize(int width, int height) {
        mCommonBox.mEmptyStubSize = new Point(width, height);
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> stubId(@LayoutRes int stubId) {
        mCommonBox.mStubId = stubId;
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> layoutId(@LayoutRes int layoutId) {
        mCommonBox.mLayoutId = layoutId;
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> isRecyclable(boolean recyclable) {
        setIsRecyclable(recyclable);
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> clickListener(OnClickListener<MODEL> listener) {
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

    public HandyHolder<ADAPTER, MODEL> viewsClickListener(List<Integer> views, OnViewsClickListener<MODEL> listener) {
        setViewsClickListener(views, listener);
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> viewsClickListener(int[] views, OnViewsClickListener<MODEL> listener) {
        setViewsClickListener(views, listener);
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> asyncInflating(boolean asyncInflating) {
        mCommonBox.mAsyncInflating = asyncInflating;
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> rippleEffect(boolean rippleEffect) {
        mCommonBox.mRippleEffect = rippleEffect;
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> adapter(ADAPTER adapter) {
        mCommonBox.mAdapter = adapter;
        return this;
    }

    public HandyHolder<ADAPTER, MODEL> init() {
        mCommonBox.init(this);
        return this;
    }

    public void bindItem(MODEL item, int position) {
        mCommonBox.bindItem(this, item, position);
    }

    public void onInit(View v) {
        mCommonBox.onInit(this, v);
    }

    public void freeAdapter() {
        mCommonBox.freeAdapter();
    }

    public List<HolderListener<ADAPTER, HandyHolder<ADAPTER, MODEL>, MODEL>> getListeners() {
        return mCommonBox.mListeners;
    }

    public void removeHolderListener(HolderListener listener) {
        mCommonBox.removeHolderListener(listener);
    }

    public void clearHolderListeners() {
        mCommonBox.clearHolderListeners();
    }

    public void setAdapter(ADAPTER adapter) {
        mCommonBox.mAdapter = adapter;
    }

    public boolean isInflated() {
        return mCommonBox.mInflated;
    }

    public void setRootView(FrameLayout vRoot) {
        mCommonBox.vRoot = vRoot;
    }

    public void setContainerView(ViewGroup vContainer) {
        mCommonBox.vContainer = vContainer;
    }

    public FrameLayout getRootView() {
        return mCommonBox.vRoot;
    }

    public ViewGroup getContainerView() {
        return mCommonBox.vContainer;
    }

    public OnClickListener<MODEL> getClickListener() {
        return mCommonBox.getClickListener();
    }

    public OnViewsClickListener<MODEL> getViewsClickListener() {
        return mCommonBox.getViewsClickListener();
    }
}