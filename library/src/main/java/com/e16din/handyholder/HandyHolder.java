package com.e16din.handyholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.settings.AlreadyBox;
import com.e16din.handyholder.listeners.click.OnClickListener;
import com.e16din.handyholder.listeners.click.OnViewsClickListener;
import com.e16din.handyholder.listeners.holder.HolderListener;

import java.util.List;

@SuppressWarnings("unused")//remove it to see warnings
public class HandyHolder<ADAPTER extends RecyclerView.Adapter, MODEL> extends RecyclerView.ViewHolder {

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }

    // create

    public static <ADAPTER extends RecyclerView.Adapter, MODEL> HandyHolder<ADAPTER, MODEL> create(
            ADAPTER adapter, ViewGroup vParent, int layoutId) {
        final LayoutInflater inflater = LayoutInflater.from(HandyHolder.getContext());
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        final HandyHolder<ADAPTER, MODEL> holder = new HandyHolder<>(itemView);
        onCreate(adapter, layoutId, (FrameLayout) itemView, holder);

        return holder;
    }

    private static <ADAPTER extends RecyclerView.Adapter, MODEL> void onCreate(
            ADAPTER adapter, int layoutId, FrameLayout itemView, HandyHolder<ADAPTER, MODEL> holder) {
        holder.mCommonBox.vRoot = itemView;
        holder.mCommonBox
                .adapter(adapter)
                .layoutId(layoutId);
    }

    public static <ADAPTER extends RecyclerView.Adapter, MODEL> HandyHolder<ADAPTER, MODEL> create(
            ADAPTER adapter, ViewGroup vParent) {
        return create(adapter, vParent, 0);
    }

    public static <ADAPTER extends RecyclerView.Adapter, MODEL> HandyHolder<ADAPTER, MODEL> create(
            ViewGroup vParent, int layoutId) {
        return create(null, vParent, layoutId);
    }

    public static <ADAPTER extends RecyclerView.Adapter, MODEL> HandyHolder<ADAPTER, MODEL> create(
            ViewGroup vParent) {
        return create(vParent, 0);
    }

    //

    private AlreadyBox<ADAPTER, HandyHolder<ADAPTER, MODEL>, MODEL> mCommonBox = new AlreadyBox<>();

    public HandyHolder(View itemView) {
        super(itemView);
        mCommonBox.setHolder(this);
    }

    public HandyHolder(View itemView, int layoutId) {
        this(itemView);
        mCommonBox.layoutId(layoutId);
    }

    public AlreadyBox set() {
        return mCommonBox;
    }

    public void bindItem(MODEL item, int position) {
        mCommonBox.bindItem(this, item, position);
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


    public void onInit(View v) {
    }

    public void beforeBind(ADAPTER adapter, MODEL item, int position) {
    }

    public void onBind(MODEL item, int position) {
    }

    public void afterBind(ADAPTER adapter, MODEL item, int position) {
    }

    public void onAsyncInflateFinished(ADAPTER adapter, int position) {
    }
}