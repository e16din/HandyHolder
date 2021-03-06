package com.e16din.handyholder.settings;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.e16din.handyholder.HandyHolder;
import com.e16din.handyholder.R;
import com.e16din.handyholder.Utils;
import com.e16din.handyholder.listeners.holder.HolderListener;
import com.e16din.ripplemaster.RippleMaster;

import java.util.List;

public class BaseBox<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL> {

    private static final int NO_STUB_LAYOUT = 0;

    public static final int WRONG_VALUE = -1;

    // free it on init() and getHolder() methods to avoid memory leaks.
    public HOLDER mHolder;

    public ADAPTER mAdapter;//free it on inflate finished

    public MODEL mItem;//free it on inflate finished

    /**
     * Set getHolder to return it on init() and getHolder() methods.
     * <p/>
     * Free it on init() and getHolder() methods to avoid memory leaks.
     */
    public void setHolder(HOLDER holder) {
        mHolder = holder;
    }


    public FrameLayout vRoot;//is itemView
    public ViewGroup vContainer;//in the itemView(vRoot)


    public List<HolderListener<ADAPTER, HOLDER, MODEL>> mListeners;


    @LayoutRes public int mLayoutId;

    public Drawable mRippleSelectorDrawable;

    public Point mEmptyStubSize = new Point(0, 64);//dp, 0 is MATCH_PARENT

    @LayoutRes public int mStubId = NO_STUB_LAYOUT;

    @ColorInt
    public int mRippleColor = WRONG_VALUE;

    public boolean mInflated = false;

    public boolean mAsyncInflating = false;

    public boolean mRippleEffect = true;

    public boolean mRecyclable = false;


    public void freeAdapter() {
        mAdapter = null;
    }

    public void freeHolder() {
        mHolder = null;
    }

    public void freeItem() {
        mItem = null;
    }

    public void removeHolderListener(HolderListener listener) {
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
            mAdapter.onBindViewHolder(mHolder, position);
            freeItem();
        }

        if (mListeners != null) {
            for (HolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
                listener.onAsyncInflateFinished(mAdapter, holder, position);
            }
        }
        if (holder instanceof HandyHolder) {
            ((HandyHolder) holder).onAsyncInflateFinished(mAdapter, position);
        }

        freeHolder();
        freeAdapter();
    }

    public boolean inflate(final HOLDER holder, @NonNull LayoutInflater inflater) {
        if (vRoot == null) {
            throw new IllegalStateException("You must set vRoot before use it, please see the HandyHolder.create() methods");
        }

        if (mAsyncInflating) {
            final boolean hasStubId = mStubId != NO_STUB_LAYOUT;

            final View vStub = inflater.inflate(hasStubId ? mStubId : R.layout.layout_item_empty_stub, vRoot, false);

            if (!hasStubId) {
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        Utils.dpToPx(mEmptyStubSize.x),
                        Utils.dpToPx(mEmptyStubSize.y));

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
        if (!mInflated) {
            mItem = item;
            return;
        }// wait for async inflater

        if (mListeners != null) {
            for (HolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
                listener.beforeBind(mAdapter, holder, item, position);
            }
        }
        if (holder instanceof HandyHolder) {
            ((HandyHolder) holder).beforeBind(mAdapter, item, position);
        }

        onBind(holder, item, position);
        if (holder instanceof HandyHolder) {
            ((HandyHolder) holder).onBind(item, position);
        }

        if (mListeners != null) {
            for (HolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
                listener.afterBind(mAdapter, holder, item, position);
            }
        }
        if (holder instanceof HandyHolder) {
            ((HandyHolder) holder).afterBind(mAdapter, item, position);
        }

        freeAdapter();
    }

    public void onBind(HOLDER holder, MODEL item, int position) {
        if (mListeners != null) {
            for (HolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
                listener.onBind(item, position);
            }
        }

        if (mRippleEffect && mRippleSelectorDrawable == null) {
            final int rippleColor = mRippleColor != WRONG_VALUE
                    ? mRippleColor
                    : ContextCompat.getColor(holder.itemView.getContext(), R.color.handyRippleColor);

            RippleMaster.setRippleForeground(vRoot, rippleColor);
        }

        if (!mRippleEffect) {
            vRoot.setForeground(null);
        }
    }

    public void onInit(HOLDER holder, View v) {
        if (holder instanceof HandyHolder) {
            ((HandyHolder) holder).onInit(v);
        }

        if (mListeners != null) {
            for (HolderListener<ADAPTER, HOLDER, MODEL> listener : mListeners) {
                listener.onInit(holder, v);
            }
        }
    }
}