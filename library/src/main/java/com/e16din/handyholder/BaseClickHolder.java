package com.e16din.handyholder;

import android.view.View;
import android.widget.FrameLayout;

import com.e16din.handyholder.listeners.OnClickListener;
import com.e16din.handyholder.listeners.OnViewsClickListener;

import java.util.List;

public abstract class BaseClickHolder<MODEL> extends BaseViewHolder<MODEL> {

    private OnClickListener<MODEL> mClickListener;
    private OnViewsClickListener<MODEL> mViewsClickListener;

    private List<Integer> mClickableViewsList;
    private int[] mClickableViewsArray;


    public BaseClickHolder(View itemView) {
        super(itemView);
        vRoot = (FrameLayout) itemView;
    }

    @Override
    public void onBind(MODEL item, int position) {
        updateClickListeners(item);
    }

    public void updateClickListeners(final MODEL item) {
        vRoot.setOnClickListener(null);

        if (mClickListener != null) {
            vRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onClick(item, getAdapterPosition());
                }
            });
        }

        if (mViewsClickListener != null) {
            if (mClickableViewsList != null) {
                for (final int viewId : mClickableViewsList) {
                    updateItemChildViewClickListener(item, viewId);
                }
            }

            if (mClickableViewsArray != null) {
                for (final int viewId : mClickableViewsArray) {
                    updateItemChildViewClickListener(item, viewId);
                }
            }
        }
    }

    private void updateItemChildViewClickListener(final MODEL item, final int viewId) {
        final View view = vRoot.findViewById(viewId);
        if (view == null) return;//wait for async inflating

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewsClickListener.onClick(viewId, item, getAdapterPosition());
            }
        });
    }

    public OnClickListener<MODEL> getClickListener() {
        return mClickListener;
    }


    public OnViewsClickListener<MODEL> getViewsClickListener() {
        return mViewsClickListener;
    }


    public List<Integer> getClickableViewsList() {
        return mClickableViewsList;
    }

    public int[] getClickableViewsArray() {
        return mClickableViewsArray;
    }

    public void setClickListener(OnClickListener<MODEL> clickListener) {
        mClickListener = clickListener;
    }

    public void setViewsClickListener(OnViewsClickListener<MODEL> viewsClickListener) {
        mViewsClickListener = viewsClickListener;
    }

    public void setClickableViewsArray(int[] clickableViewsArray) {
        mClickableViewsArray = clickableViewsArray;
    }

    public void setClickableViewsList(List<Integer> clickableViewsList) {
        mClickableViewsList = clickableViewsList;
    }
}