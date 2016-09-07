package com.e16din.handyholder;

import android.content.Context;
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

import com.e16din.handyholder.listeners.HandyListener;
import com.e16din.handyholder.listeners.OnClickListener;
import com.e16din.handyholder.listeners.OnViewsClickListener;

import java.util.List;

@SuppressWarnings("unused")//remove it to see warnings
public class HandyHolder<MODEL> extends BaseClickHolder<MODEL> {

    private static final int NO_STUB_LAYOUT = 0;

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }

    public static <MODEL> HandyHolder<MODEL> create(RecyclerView.Adapter adapter, ViewGroup vParent) {
        final LayoutInflater inflater = LayoutInflater.from(sContext);
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        final HandyHolder<MODEL> holder = new HandyHolder<>(itemView);
        holder.setAdapter(adapter);
        return holder;
    }

    public static <MODEL> HandyHolder<MODEL> create(RecyclerView.Adapter adapter, ViewGroup vParent, int layoutId) {
        return HandyHolder.<MODEL>create(adapter, vParent)
                .layoutId(layoutId);
    }


    public ViewGroup vContainer;//in the itemView(vRoot)

    private HandyListener<MODEL> mListener;

    private RecyclerView.Adapter mAdapter;//free it on inflate finished

    private int mLayoutId;

    private Drawable mSelectorDrawable;

    private Point mEmptyStubSize = new Point(0, 64);//dp, 0 is MATCH_PARENT
    @LayoutRes private int mStubId = NO_STUB_LAYOUT;

    private boolean mInflated = false;

    private boolean mAsyncInflating = false;

    private boolean mRippleEffect = true;


    public HandyHolder(View itemView) {
        super(itemView);
    }

    public HandyHolder<MODEL> init() {
        if (mLayoutId == 0) {
            throw new IllegalArgumentException("mLayoutId may not be 0, use layoutId() method before init.");
        }

        final LayoutInflater inflater = LayoutInflater.from(sContext);

        if (inflate(inflater)) {
            onInit(this, vRoot);
        }

        return this;
    }

    public void bindItem(MODEL item, int position) {
        if (mListener != null) {
            mListener.onPreBind(this, item, position);
        }

        if (mInflated) {
            onBind(item, position);
        } // else and wait for async inflater
    }

    public boolean isInflated() {
        return mInflated;
    }

    public void setInflated(boolean inflated) {
        mInflated = inflated;
    }

    public void setLayoutId(int layoutId) {
        mLayoutId = layoutId;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    @Override
    public void onInit(HandyHolder<MODEL> h, View v) {
        if (mListener == null) return;

        mListener.onInit(h, v);
    }

    @Override
    public void onBind(MODEL item, int position) {
        super.onBind(item, position);

        if (mListener == null) return;

        mListener.onBind(item, position);

        if (mRippleEffect && mSelectorDrawable == null) {
            int[] attrs = new int[]{android.R.attr.selectableItemBackground};
            TypedArray ta = sContext.obtainStyledAttributes(attrs);
            mSelectorDrawable = ta.getDrawable(0);
            ta.recycle();
        }

        vRoot.setForeground(mRippleEffect ? mSelectorDrawable : null);
    }

    protected boolean inflate(@NonNull LayoutInflater inflater) {
        if (mAsyncInflating) {
            final boolean hasStubId = mStubId != NO_STUB_LAYOUT;

            final View vStub = inflater.inflate(hasStubId ? mStubId : R.layout.layout_item_empty_stub,
                    vRoot, false);

            if (!hasStubId) {
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                        Utils.dpToPx(sContext, mEmptyStubSize.x),
                        Utils.dpToPx(sContext, mEmptyStubSize.y));

                vStub.setLayoutParams(params);
            }

            vRoot.addView(vStub);

            final AsyncLayoutInflater asyncInflater = new AsyncLayoutInflater(sContext);
            asyncInflater.inflate(mLayoutId, vRoot, new AsyncLayoutInflater.OnInflateFinishedListener() {
                @Override
                public void onInflateFinished(View view, int resId, ViewGroup vParent) {
                    onAsyncInflateFinished((ViewGroup) view, vParent, vStub);
                }
            });

        } else {
            vContainer = (ViewGroup) inflater.inflate(mLayoutId, vRoot, false);
            vRoot.addView(vContainer);
            mInflated = true;
            mAdapter = null;
        }

        return true;
    }

    public void onAsyncInflateFinished(ViewGroup vContainer, ViewGroup vParent, View vStub) {
        vParent.addView(vContainer);
        this.vContainer = vContainer;
        vParent.removeView(vStub);
        setInflated(true);

        onInit(this, vParent);

        final int position = getAdapterPosition();

        if (position >= 0) {
            mAdapter.notifyItemChanged(position);// -> bindItem()
            mAdapter = null;
        }
    }

    /// setters

    public HandyHolder<MODEL> listener(HandyListener<MODEL> listener) {
        mListener = listener;
        return this;
    }

    public HandyHolder<MODEL> emptyStubSize(int width, int height) {
        mEmptyStubSize = new Point(width, height);
        return this;
    }

    public HandyHolder<MODEL> stubId(@LayoutRes int stubId) {
        mStubId = stubId;
        return this;
    }

    public HandyHolder<MODEL> layoutId(@LayoutRes int layoutId) {
        mLayoutId = layoutId;
        return this;
    }

    public HandyHolder<MODEL> isRecyclable(boolean recyclable) {
        setIsRecyclable(recyclable);
        return this;
    }

    public HandyHolder<MODEL> clickListener(OnClickListener<MODEL> listener) {
        setClickListener(listener);
        return this;
    }

    public HandyHolder<MODEL> viewsClickListener(List<Integer> views, OnViewsClickListener<MODEL> listener) {
        setClickableViewsList(views);
        setViewsClickListener(listener);
        return this;
    }

    public HandyHolder<MODEL> viewsClickListener(int[] views, OnViewsClickListener<MODEL> listener) {
        setClickableViewsArray(views);
        setViewsClickListener(listener);
        return this;
    }

    public HandyHolder<MODEL> asyncInflating(boolean asyncInflating) {
        mAsyncInflating = asyncInflating;
        return this;
    }

    public HandyHolder<MODEL> rippleEffect(boolean rippleEffect) {
        mRippleEffect = rippleEffect;
        return this;
    }

    //todo:
//    public FitViewHolder<MODEL> backgroundSelectorId(int selectorResId) {
//        mSelectorResId = selectorResId;
//        return this;
//    }

    private void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }
}