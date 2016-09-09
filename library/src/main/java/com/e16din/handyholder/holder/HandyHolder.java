package com.e16din.handyholder.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e16din.handyholder.R;

public class HandyHolder<MODEL> extends StrongHandyHolder<RecyclerView.Adapter, MODEL> {

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }


    public HandyHolder(View itemView) {
        super(itemView);
    }

    public static <MODEL> HandyHolder<MODEL> create(RecyclerView.Adapter adapter, ViewGroup vParent, int layoutId) {
        final LayoutInflater inflater = LayoutInflater.from(HandyHolder.getContext());
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.layout_root, vParent, false);
        final HandyHolder<MODEL> holder = new HandyHolder<>(itemView);
        holder.setAdapter(adapter);
        holder.mCommonBox.layoutId(layoutId);

        return holder;
    }

    public static <MODEL> HandyHolder<MODEL> create(RecyclerView.Adapter adapter, ViewGroup vParent) {
        return HandyHolder.create(adapter, vParent, 0);
    }

    public static <MODEL> HandyHolder<MODEL> create(ViewGroup vParent, int layoutId) {
        return HandyHolder.create(null, vParent, layoutId);
    }

    public static <MODEL> HandyHolder<MODEL> create(ViewGroup vParent) {
        return HandyHolder.create(vParent, 0);
    }
}