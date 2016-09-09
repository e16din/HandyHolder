package com.e16din.handyholder;

import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.holder.HandyHolder;
import com.e16din.handyholder.listeners.holder.StrongHolderListener;

import java.util.List;

public class BaseBox<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL> {

    private static final int NO_STUB_LAYOUT = 0;


    public FrameLayout vRoot;//is itemView
    public ViewGroup vContainer;//in the itemView(vRoot)


    public List<StrongHolderListener<ADAPTER, HOLDER, MODEL>> mListeners;

    public ADAPTER mAdapter;//free it on inflate finished

    @LayoutRes public int mLayoutId;

    public Drawable mSelectorDrawable;

    public Point mEmptyStubSize = new Point(0, 64);//dp, 0 is MATCH_PARENT

    @LayoutRes public int mStubId = NO_STUB_LAYOUT;

    public boolean mInflated = false;

    public boolean mAsyncInflating = false;

    public boolean mRippleEffect = true;

    public boolean mRecyclable = false;


    public void freeAdapter() {
        mAdapter = null;
    }

    public void removeHolderListener(StrongHolderListener listener) {
        if (mListeners != null) {
            mListeners.remove(listener);
        }
    }

    public void clearHolderListeners() {
        if (mListeners != null) {
            mListeners.clear();
        }
    }

    public void init(HOLDER holder) {
        if (mLayoutId == 0) {
            throw new IllegalArgumentException("mLayoutId may not be 0, use setLayoutId() method before init.");
        }

        final LayoutInflater inflater = LayoutInflater.from(HandyHolder.getContext());

        if (inflate(holder, inflater)) {
            onInit(holder, vRoot);
        }
    }

    public void onAsyncInflateFinished(HOLDER holder, ViewGroup vContainer, ViewGroup vParent, View vStub) {
        vParent.addView(vContainer);
        this.vContainer = vContainer;
        vParent.removeView(vStub);
        mInflated = true;

        onInit(holder, vParent);

        final int position = holder.getAdapterPosition();

        if (position >= 0) {
            mAdapter.notifyItemChanged(position);// -> bindItem()
        }

        for (StrongHolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
            listener.onAsyncInflateFinished(mAdapter, holder, position);
        }

        freeAdapter();
    }

    public boolean inflate(final HOLDER holder, @NonNull LayoutInflater inflater) {
        if (mAsyncInflating) {
            final boolean hasStubId = mStubId != NO_STUB_LAYOUT;

            final View vStub = inflater.inflate(hasStubId ? mStubId : R.layout.layout_item_empty_stub, vRoot, false);

            if (!hasStubId) {
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        Utils.dpToPx(HandyHolder.getContext(), mEmptyStubSize.x),
                        Utils.dpToPx(HandyHolder.getContext(), mEmptyStubSize.y));

                vStub.setLayoutParams(params);
            }

            vRoot.addView(vStub);

            final AsyncLayoutInflater asyncInflater = new AsyncLayoutInflater(HandyHolder.getContext());
            asyncInflater.inflate(mLayoutId, vRoot,
                    new AsyncLayoutInflater.OnInflateFinishedListener() {
                        @Override
                        public void onInflateFinished(View view, int resId, ViewGroup vParent) {
                            onAsyncInflateFinished(holder, (ViewGroup) view, vParent, vStub);
                        }
                    });

        } else {
            vContainer = (ViewGroup) inflater.inflate(mLayoutId, vRoot, false);
            vRoot.addView(vContainer);
            mInflated = true;
        }

        return true;
    }

    public void bindItem(HOLDER holder, MODEL item, int position) {
        if (mListeners != null) {

            for (StrongHolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
                listener.beforeBind(mAdapter, holder, item, position);
            }
        }

        if (mInflated) {
            onBind(holder, item, position);
        } // else and wait for async inflater

        if (mListeners != null) {
            for (StrongHolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
                listener.afterBind(mAdapter, holder, item, position);
            }

            if (!mAsyncInflating) {
                freeAdapter();
            }// else wait for async inflater
        }
    }

    public void onBind(HOLDER holder, MODEL item, int position) {
        if (mListeners == null) return;

        for (StrongHolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
            listener.onBind(item, position);
        }

        if (mRippleEffect && mSelectorDrawable == null) {
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
            TypedArray ta = HandyHolder.getContext().obtainStyledAttributes(attrs);
            mSelectorDrawable = ta.getDrawable(0);
            ta.recycle();
        }

        vRoot.setForeground(mRippleEffect ? mSelectorDrawable : null);
    }

    public void onInit(HOLDER h, View v) {
        if (mListeners == null) return;

        for (StrongHolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
            listener.onInit(h, v);
        }
    }
}