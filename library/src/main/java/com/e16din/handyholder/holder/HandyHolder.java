package com.e16din.handyholder.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    public HandyHolder(View itemView, int layoutId) {
        super(itemView, layoutId);
    }
}