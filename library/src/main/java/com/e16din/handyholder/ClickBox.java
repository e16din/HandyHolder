package com.e16din.handyholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.e16din.handyholder.listeners.click.OnClickListener;
import com.e16din.handyholder.listeners.click.OnViewsClickListener;

import java.util.List;

public class ClickBox<ADAPTER extends RecyclerView.Adapter, HOLDER extends RecyclerView.ViewHolder, MODEL>
        extends BaseBox<ADAPTER, HOLDER, MODEL> {

    private OnClickListener<MODEL> mClickListener;
    private OnViewsClickListener<MODEL> mViewsClickListener;

    private List<Integer> mClickableViewsList;
    private int[] mClickableViewsArray;


    public void updateClickListeners(final MODEL item, final int position) {
        vRoot.setOnClickListener(null);

        if (mClickListener != null) {
            vRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onClick(item, position);
                }
            });
        }

        if (mViewsClickListener != null) {
            if (mClickableViewsList != null) {
                for (final int viewId : mClickableViewsList) {
                    updateItemChildViewClickListener(item, viewId, position);
                }
            }

            if (mClickableViewsArray != null) {
                for (final int viewId : mClickableViewsArray) {
                    updateItemChildViewClickListener(item, viewId, position);
                }
            }
        }
    }

    private void updateItemChildViewClickListener(final MODEL item, final int viewId, final int position) {
        final View view = vRoot.findViewById(viewId);
        if (view == null) return;//wait for async inflating

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewsClickListener.onClick(viewId, item, position);
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

    @Override
    public void onBind(HOLDER holder, MODEL item, int position) {
        super.onBind(holder, item, position);
        updateClickListeners(item, position);
    }
}