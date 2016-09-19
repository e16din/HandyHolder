package com.e16din.handyholder.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.AlreadyBox;
import com.e16din.handyholder.R;
import com.e16din.handyholder.listeners.click.OnClickListener;
import com.e16din.handyholder.listeners.click.OnViewsClickListener;
import com.e16din.handyholder.listeners.holder.StrongHolderListener;

import java.util.List;

@SuppressWarnings("unused")//remove it to see warnings
public class StrongHandyHolder<ADAPTER extends RecyclerView.Adapter, MODEL> extends RecyclerView.ViewHolder {


    public static <ADAPTER extends RecyclerView.Adapter, MODEL>
    StrongHandyHolder<ADAPTER, MODEL> createStrong(ADAPTER adapter, ViewGroup vParent, int layoutId) {

        final LayoutInflater inflater = LayoutInflater.from(HandyHolder.getContext());
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        final StrongHandyHolder<ADAPTER, MODEL> holder = new StrongHandyHolder<>(itemView);
        onCreate(adapter, layoutId, (FrameLayout) itemView, holder);

        return holder;
    }

    protected static <ADAPTER extends RecyclerView.Adapter, MODEL> void onCreate(
            ADAPTER adapter, int layoutId, FrameLayout itemView, StrongHandyHolder<ADAPTER, MODEL> holder) {
        holder.mCommonBox.vRoot = itemView;
        holder.mCommonBox
                .adapter(adapter)
                .layoutId(layoutId);
    }

    public static <ADAPTER extends RecyclerView.Adapter, MODEL>
    StrongHandyHolder<ADAPTER, MODEL> createStrong(ADAPTER adapter, ViewGroup vParent) {

        return StrongHandyHolder.createStrong(adapter, vParent, 0);
    }

    public static <MODEL> StrongHandyHolder<RecyclerView.Adapter, MODEL> createStrong(ViewGroup vParent, int layoutId) {
        return StrongHandyHolder.createStrong(null, vParent, layoutId);
    }

    public static <MODEL> StrongHandyHolder<RecyclerView.Adapter, MODEL> createStrong(ViewGroup vParent) {
        return StrongHandyHolder.createStrong(vParent, 0);
    }


    protected AlreadyBox<ADAPTER, StrongHandyHolder<ADAPTER, MODEL>, MODEL> mCommonBox = new AlreadyBox<>();


    public StrongHandyHolder(View itemView) {
        super(itemView);
        mCommonBox.setHolder(this);
    }

    public StrongHandyHolder(View itemView, int layoutId) {
        this(itemView);
        mCommonBox.layoutId(layoutId);
    }

    public AlreadyBox set() {
        return mCommonBox;
    }

    public void bindItem(MODEL item, int position) {
        mCommonBox.bindItem(this, item, position);
    }

    public List<StrongHolderListener<ADAPTER, StrongHandyHolder<ADAPTER, MODEL>, MODEL>> getListeners() {
        return mCommonBox.mListeners;
    }

    public void removeHolderListener(StrongHolderListener listener) {
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